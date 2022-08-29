package carsharing.repository;

import carsharing.conn.ConnectToH2;
import carsharing.entity.Car;
import carsharing.entity.Company;
import carsharing.util.Msg;
import carsharing.util.SQLStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static carsharing.util.Msg.ID;
import static carsharing.util.Msg.NAME;

public class CompanyRepositoryImpl implements Repository<Company, Car> {

    private final ConnectToH2 connectToH2;

    public CompanyRepositoryImpl(ConnectToH2 connectToH2) {
        this.connectToH2 = connectToH2;
    }

    @Override
    public void save(Company company) {
        try (Statement statement = connectToH2.getConnection().createStatement()) {
            statement.executeUpdate(String.format(SQLStatement.INSERT_INTO_COMPANY, company.getName()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Company> getAll() {

        List<Company> companyList = new ArrayList<>();

        try (
                Statement statement = connectToH2.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery(SQLStatement.SELECT_ALL_FROM_COMPANY)
        ) {

            while (resultSet.next()) {
                int id = resultSet.getInt(Msg.ID);
                String name = resultSet.getString(Msg.NAME);

                companyList.add(new Company(id, name));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return companyList;
    }


    @Override
    public Company getEntity(int id) {
        try (Statement statement = connectToH2.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(String.format(SQLStatement.SELECT_COMPANY_WHERE_ID, id))) {

            while (resultSet.next()) {
                int carId = resultSet.getInt(ID);
                String companyName = resultSet.getString(NAME);


                return new Company(carId, companyName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
