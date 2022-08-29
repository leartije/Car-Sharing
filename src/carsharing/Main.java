package carsharing;

import carsharing.ui.CarSharing;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        String dbName = args.length == 2 ? args[1] : "stagod";
        new CarSharing(dbName).start();

    }
}