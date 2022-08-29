package carsharing.repository;

import carsharing.conn.ConnectToH2;
import carsharing.entity.Car;
import carsharing.entity.Customer;
import carsharing.util.Msg;
import carsharing.util.SQLStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static carsharing.util.Msg.*;

public class CustomerRepositoryImpl implements Repository<Customer, Car> {

    private final ConnectToH2 connect;

    public CustomerRepositoryImpl(ConnectToH2 connect) {
        this.connect = connect;
    }

    @Override
    public void save(Customer entity) {
        try (Statement statement = connect.getConnection().createStatement()) {

            statement.executeUpdate(String.format(SQLStatement.INSERT_INTO_CUSTOMER, entity.getName()));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customerList = new ArrayList<>();

        try (Statement statement = connect.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(SQLStatement.SELECT_ALL_FROM_CUSTOMER)) {

            while (resultSet.next()) {
                int id = resultSet.getInt(Msg.ID);
                String name = resultSet.getString(Msg.NAME);
                int rentedCarId = resultSet.getInt(Msg.RENTED_CAR_ID);

                Customer customer = new Customer(id, name, rentedCarId);
                customerList.add(customer);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerList;
    }

    @Override
    public void update(Customer customer, String rentedCarId) {
        try (Statement statement = connect.getConnection().createStatement()) {
            statement.executeUpdate(String.format(SQLStatement.RENTED_CAR, rentedCarId, customer.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Customer getEntity(int id) {
        try (Statement statement = connect.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(String.format(SQLStatement.SELECT_CUSTOMER_WHERE_ID, id))) {

            while (resultSet.next()) {
                int customerId = resultSet.getInt(ID);
                String customerName = resultSet.getString(NAME);
                int rentedCarId = resultSet.getInt(RENTED_CAR_ID);


                return new Customer(customerId, customerName, rentedCarId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
