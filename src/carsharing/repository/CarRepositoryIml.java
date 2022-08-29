package carsharing.repository;

import carsharing.conn.ConnectToH2;
import carsharing.entity.Car;
import carsharing.entity.Company;
import carsharing.util.SQLStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static carsharing.util.Msg.*;

public class CarRepositoryIml implements Repository<Car, Company> {

    private final ConnectToH2 connectToH2;

    public CarRepositoryIml(ConnectToH2 connectToH2) {
        this.connectToH2 = connectToH2;
    }

    @Override
    public void save(Car car, Company company) {
        try (Statement statement = connectToH2.getConnection().createStatement()) {

            statement.executeUpdate(
                    String.format(SQLStatement.INSERT_INTO_CAR, car.getName(), company.getId())
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Car> getAll(Company company) {
        List<Car> carList = new ArrayList<>();

        try (
                Statement statement = connectToH2.getConnection().createStatement();
                ResultSet resultSet = statement
                        .executeQuery(String.format(SQLStatement.SELECT_CAR_WHERE_COMPANY_ID, company.getId()))
        ) {

            while (resultSet.next()) {
                int id = resultSet.getInt(ID);
                String name = resultSet.getString(NAME);
                int companyId = resultSet.getInt(COMPANY_ID);

                carList.add(new Car(id, name, companyId));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carList;
    }


    @Override
    public Car getEntity(int id) {
        try (Statement statement = connectToH2.getConnection().createStatement();
             ResultSet resultSet = statement
                     .executeQuery(String.format(SQLStatement.SELECT_CAR_WHERE_ID, id))) {

            while (resultSet.next()) {
                int carId = resultSet.getInt(ID);
                String carName = resultSet.getString(NAME);
                int rentedCarId = resultSet.getInt(COMPANY_ID);

                return new Car(carId, carName, rentedCarId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


}
