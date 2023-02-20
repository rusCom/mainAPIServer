package geo.services;

import geo.GeoApplication;
import org.apache.commons.lang3.exception.ExceptionUtils;
import tools.MainUtils;
import tools.MySQLTools;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class GeoSQLThread {
    private static volatile GeoSQLThread mySQLThreadInstance;

    public static GeoSQLThread getInstance() {
        GeoSQLThread localInstance = mySQLThreadInstance;
        if (localInstance == null) {
            synchronized (GeoSQLThread.class) {
                localInstance = mySQLThreadInstance;
                if (localInstance == null) {
                    mySQLThreadInstance = localInstance = new GeoSQLThread();
                }
            }
        }
        return localInstance;
    }

    Connection mainConnection;
    Set<String> queries;
    boolean isThreadStarted;
    boolean isThreadRun;


    public GeoSQLThread() {
        queries = Collections.newSetFromMap(new ConcurrentHashMap<>());
        isThreadStarted = false;
        isThreadRun = false;
    }

    public void addQuery(String SQL) throws SQLException {
        queries.add(SQL);
        startThread();
    }

    private void startThread() throws SQLException {
        if (!isThreadStarted) {
            if (mainConnection == null) {
                mainConnection = GeoApplication.getInstance().getGeoMySQLConnection();
            }
            isThreadRun = true;
            Thread thread = new Thread(() -> {
                while (isThreadRun) {
                    for (String SQL : queries){
                        try (Statement statement = mainConnection.createStatement()) {
                            queries.remove(SQL);
                            MainUtils.getInstance().printLog("service_geo_sql_thread", "date = \t" + MainUtils.getCurDateTime() + "\nSQL = \t" + SQL);
                            statement.executeUpdate(SQL);
                            MySQLTools.closeStatement(statement);
                        } catch (SQLException exception) {
                            MainUtils.getInstance().printLog("service_geo_sql_thread", "error" + ExceptionUtils.getStackTrace(exception));
                        }
                    } // for (String SQL : queries)
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException exception) {
                        MainUtils.getInstance().printLog("service_geo_sql_thread", "error" + ExceptionUtils.getStackTrace(exception));
                    }
                }
            });

            thread.start();
            isThreadStarted = true;
        }
    }
}
