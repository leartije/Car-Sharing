package carsharing.conn;

import carsharing.util.SQLStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectToH2 {

    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:./src/carsharing/db/";

    private Connection connection;

    public ConnectToH2(String dbName) {
        connectToDB(dbName);
        createTable(SQLStatement.CREATE_TABLE_COMPANY);
        createTable(SQLStatement.CREATE_TABLE_CAR);
        createTable(SQLStatement.CREATE_TABLE_CUSTOMER);
    }

    public Connection getConnection() {
        return connection;
    }

    public void connectToDB(String dbName) {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL + dbName);
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTable(String table) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(table);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
