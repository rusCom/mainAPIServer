package tools;

import com.intersys.objects.CacheDatabase;
import com.intersys.objects.CacheException;
import com.intersys.objects.Database;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class CacheConnectionPool {
    private final String address;
    private final String port;
    private final String namespace;
    private final String user;
    private final String password;

    private final int maxPoolSize;
    private int connNum = 0;


    Stack<Database> freePool = new Stack<>();
    Set<Database> occupiedPool = new HashSet<>();

    public CacheConnectionPool(String address, String port, String namespace, String user, String password, int maxPoolSize) {
        this.address = address;
        this.port = port;
        this.namespace = namespace;
        this.user = user;
        this.password = password;
        this.maxPoolSize = maxPoolSize;
    }

    /**
     * Get an available connection
     *
     * @return An available connection
     * @throws CacheException Fail to get an available connection
     */
    public synchronized Database getConnection() throws CacheException {
        Database conn;

        if (isFull()) {
            MainUtils.getInstance().printLog("Cache connections pool is full");
            throw new CacheException("The connection pool is full.");
        }

        conn = getConnectionFromPool();

        // If there is no free connection, create a new one.
        if (conn == null) {
            conn = createNewConnectionForPool();
            MainUtils.getInstance().printLog("Create new connection in Cache connection pool. This connection " + connNum + " from " + maxPoolSize);
        }

        // For Azure Database for MySQL, if there is no action on one connection for some
        // time, the connection is lost. By this, make sure the connection is
        // active. Otherwise reconnect it.
        conn = makeAvailable(conn);
        return conn;
    }

    /**
     * Return a connection to the pool
     *
     * @param conn The connection
     * @throws CacheException When the connection is returned already or it isn't gotten
     *                        from the pool.
     */
    public synchronized void returnConnection(Database conn) throws CacheException {
        if (conn == null) {
            throw new NullPointerException();
        }
        if (!occupiedPool.remove(conn)) {
            throw new CacheException(
                    "The connection is returned already or it isn't for this pool");
        }
        freePool.push(conn);
    }

    /**
     * Verify if the connection is full.
     *
     * @return if the connection is full
     */
    private synchronized boolean isFull() {
        return ((freePool.size() == 0) && (connNum >= maxPoolSize));
    }

    /**
     * Create a connection for the pool
     *
     * @return the new created connection
     * @throws CacheException When fail to create a new connection.
     */
    private Database createNewConnectionForPool() throws CacheException {
        Database conn = createNewConnection();
        connNum++;
        occupiedPool.add(conn);
        return conn;
    }

    /**
     * Crate a new connection
     *
     * @return the new created connection
     * @throws CacheException When fail to create a new connection.
     */
    private Database createNewConnection() throws CacheException {
        Database conn;
        String dataBaseURL = "jdbc:Cache://" + address + ":"
                + port + "/"
                + namespace;
        conn = CacheDatabase.getDatabase(dataBaseURL, user, password);
        return conn;
    }

    /**
     * Get a connection from the pool. If there is no free connection, return
     * null
     *
     * @return the connection.
     */
    private Database getConnectionFromPool() {
        Database conn = null;
        if (freePool.size() > 0) {
            conn = freePool.pop();
            occupiedPool.add(conn);
        }
        return conn;
    }

    /**
     * Make sure the connection is available now. Otherwise, reconnect it.
     *
     * @param conn The connection for verification.
     * @return the available connection.
     * @throws CacheException Fail to get an available connection
     */
    private Database makeAvailable(Database conn) throws CacheException {
        if (isConnectionAvailable(conn)) {
            return conn;
        }

        // If the connection is't available, reconnect it.
        occupiedPool.remove(conn);
        connNum--;
        conn.close();

        conn = createNewConnection();
        occupiedPool.add(conn);
        connNum++;
        return conn;
    }

    /**
     * By running a sql to verify if the connection is available
     *
     * @param conn The connection for verification
     * @return if the connection is available for now.
     */
    private boolean isConnectionAvailable(Database conn) {
        return true;
        /*
        try (Statement st = conn.createStatement()) {
            st.executeQuery(SQL_VERIFYCONN);
            return true;
        } catch (SQLException e) {
            return false;
        }

         */
    }
}
