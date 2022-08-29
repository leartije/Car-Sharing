package carsharing.util;

public class SQLStatement {


    //create table
    public static final String CREATE_TABLE_COMPANY = "CREATE TABLE IF NOT EXISTS company " +
            "(" +
            "id INTEGER NOT NULL AUTO_INCREMENT, " +
            "name VARCHAR UNIQUE NOT NULL, " +
            "PRIMARY KEY (id)" +
            ")";

    public static final String CREATE_TABLE_CAR = "CREATE TABLE IF NOT EXISTS car " +
            "(" +
            "id INTEGER NOT NULL AUTO_INCREMENT, " +
            "name VARCHAR UNIQUE NOT NULL, " +
            "company_id INTEGER NOT NULL, " +
            "PRIMARY KEY(id), " +
            "FOREIGN KEY (company_id) REFERENCES company (id)" +
            ")";

    public static final String CREATE_TABLE_CUSTOMER = "CREATE TABLE IF NOT EXISTS customer " +
            "(" +
            "id INTEGER NOT NULL AUTO_INCREMENT, " +
            "name VARCHAR UNIQUE NOT NULL, " +
            "rented_car_id INTEGER, " +
            "PRIMARY KEY(id), " +
            "FOREIGN KEY (rented_car_id) REFERENCES car (id)" +
            ")";


    //queries company
    public static final String SELECT_ALL_FROM_COMPANY = "SELECT * FROM company";
    public static final String SELECT_COMPANY_WHERE_ID = "SELECT * FROM company WHERE id=%d%n";
    public static final String INSERT_INTO_COMPANY = "INSERT INTO company (name) VALUES ('%s')%n";


    //queries car
    public static String SELECT_CAR_WHERE_COMPANY_ID = "SELECT * FROM car WHERE company_id=%d%n";
    public static String SELECT_CAR_WHERE_ID = "SELECT * FROM car WHERE id=%d%n";
    public static String INSERT_INTO_CAR = "INSERT INTO car (name, company_id) VALUES ('%s', %d)%n";

    //queries customer
    public static final String SELECT_ALL_FROM_CUSTOMER = "SELECT * FROM customer";
    public static final String SELECT_CUSTOMER_WHERE_ID = "SELECT * FROM customer WHERE id=%d%n";
    public static final String RENTED_CAR = "UPDATE customer SET rented_car_id=%s WHERE id=%d%n";
    public static final String INSERT_INTO_CUSTOMER = "INSERT INTO customer (name) VALUES ('%s')%n";


}
