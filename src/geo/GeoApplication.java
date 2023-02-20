package geo;

import geo.tools.GeoCode;
import org.apache.commons.lang3.exception.ExceptionUtils;
import tools.MainUtils;
import tools.MySQLConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class GeoApplication {
    private static volatile GeoApplication geoApplicationInstance;
    MySQLConnectionPool geoMySQLConnectionPool;

    private GeoCode geoCode;
    private final String remoteORSToken;
    private final String googleKey;

    public static GeoApplication getInstance() {
        GeoApplication localInstance = geoApplicationInstance;
        if (localInstance == null) {
            synchronized (GeoApplication.class) {
                localInstance = geoApplicationInstance;
                if (localInstance == null) {
                    geoApplicationInstance = localInstance = new GeoApplication();
                }
            }
        }
        return localInstance;
    }

    public GeoApplication() {
        MainUtils.getInstance().printLog("main", "crate geoApplication class");

        remoteORSToken = MainUtils.getInstance().getPropertyString("token.geo.ors");
        googleKey = MainUtils.getInstance().getPropertyString("token.geo.google");

        String geoMySQLHost = MainUtils.getInstance().getPropertyString("geo.mysql.host");
        String geoMySQLPort = MainUtils.getInstance().getPropertyString("geo.mysql.port", "3306");
        String geoMySQLDatabase = MainUtils.getInstance().getPropertyString("geo.mysql.database");
        String geoMySQLUsername = MainUtils.getInstance().getPropertyString("geo.mysql.username");
        String geoMySQLPassword = MainUtils.getInstance().getPropertyString("geo.mysql.password");
        Integer geoMySQLPoolSize = MainUtils.getInstance().getPropertyInteger("geo.mysql.poolSize", 20);
        String geoMySQLConnectionURL = "jdbc:mysql://" + geoMySQLHost + ":" + geoMySQLPort + "/" + geoMySQLDatabase;
        MainUtils.getInstance().printLog("main", "Connection to MySQL Database " + geoMySQLConnectionURL);

        geoMySQLConnectionPool = new MySQLConnectionPool(geoMySQLConnectionURL, geoMySQLUsername, geoMySQLPassword, geoMySQLPoolSize);

        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            conn = geoMySQLConnectionPool.getConnection();
            try (Statement statement = conn.createStatement()) {
                statement.executeQuery("show tables");
                MainUtils.getInstance().printLog("main", "Connection to MySQL Database success");
            }
        } catch (Exception exception) {
            MainUtils.getInstance().printLog("sqlException", ExceptionUtils.getStackTrace(exception));
            MainUtils.getInstance().printLog("main", "Connection to MySQL Database exception " + ExceptionUtils.getStackTrace(exception));
        } finally {
            if (conn != null) {
                try {
                    geoMySQLConnectionPool.returnConnection(conn);
                } catch (SQLException exception) {
                    MainUtils.getInstance().printLog("sqlException", ExceptionUtils.getStackTrace(exception));
                    MainUtils.getInstance().printLog("main", "Connection to MySQL Database exception " + ExceptionUtils.getStackTrace(exception));
                }
            }
        }


    }

    public Connection getGeoMySQLConnection() throws SQLException {
        return geoMySQLConnectionPool.getConnection();
    }

    public String getRemoteORSToken() {
        return remoteORSToken;
    }

    public String getGoogleKey() {
        return googleKey;
    }

    public void returnGeoMySQLConnection(Connection connection) {
        try {
            geoMySQLConnectionPool.returnConnection(connection);
        } catch (SQLException exception) {
            MainUtils.getInstance().printLog("sqlException", ExceptionUtils.getStackTrace(exception));
        }
    }

    public GeoCode getGeoCode() {
        if (geoCode == null) {
            geoCode = new GeoCode();
        }
        return geoCode;
    }
}
