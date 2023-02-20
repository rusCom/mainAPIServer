package tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLTools {
    public static ResultSet onceSelect(Connection connection, String SQL) throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL);
        if (!resultSet.next()){
            return null;
        }
        return resultSet;
    }

    public static void closeStatement(Statement statement){
        if (statement != null){
            try {
                if (!statement.isClosed()){
                    statement.close();
                }
            } catch (SQLException ignored) {
            }
        }
    }

    public static void closeResultSet(ResultSet resultSet){
        if (resultSet != null){
            try {
                if (!resultSet.isClosed()){
                    resultSet.close();
                }

            } catch (SQLException ignored) {
            }
        }
    }

    public static boolean isQueryResult(Connection connection, String SQL){
        Statement statement = null;
        ResultSet resultSet = null;
        boolean result = false;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);
            if (resultSet.next()){
                result = true;
            }
        } catch (SQLException ignored) {
        } finally {
            closeStatement(statement);
            closeResultSet(resultSet);
        }
        return result;
    }

    public static void update(Connection connection, String SQL) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(SQL);
        statement.close();
    }

    public static Boolean insert(Connection connection, String SQL) throws SQLException {
        Statement statement = connection.createStatement();
        int i = statement.executeUpdate(SQL);
        boolean result = false;
        if (i > 0) {
            result = true;
        }
        statement.close();
        return result;
    }
}
