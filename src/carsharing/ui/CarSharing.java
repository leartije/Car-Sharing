package carsharing.ui;

import carsharing.conn.ConnectToH2;
import carsharing.entity.Car;
import carsharing.entity.Company;
import carsharing.entity.Customer;
import carsharing.services.CarSharingService;
import carsharing.services.CarSharingServiceCarImpl;
import carsharing.services.CarSharingServiceCompanyImpl;
import carsharing.services.CarSharingServiceCustomerImpl;
import carsharing.util.Msg;

import java.sql.SQLException;

import static carsharing.services.CarSharingService.SCANNER;

public class CarSharing {

    private  final ConnectToH2 connect;
    private final CarSharingService<Customer, Car, Integer> customerService;
    private final ManagerUI managerUI;
    private final CustomerUI customerUI;

    public CarSharing(String dbName) {
        this.connect = new ConnectToH2(dbName);
        CarSharingService<Company, Car, Integer> companyService = new CarSharingServiceCompanyImpl(connect);
        CarSharingService<Car, Company, Integer> carService = new CarSharingServiceCarImpl(connect);
        this.customerService = new CarSharingServiceCustomerImpl(connect);
        CompanyUI companyUI = new CompanyUI(carService);
        this.managerUI = new ManagerUI(companyService, companyUI);
        CarUI carUI = new CarUI(companyService, carService, customerService);
        this.customerUI = new CustomerUI(customerService, carUI);
    }

    public void start() throws SQLException {
        while (true) {
            System.out.println(Msg.MAIN_MENU);
            String input = SCANNER.nextLine();
            System.out.println();
            switch (input) {
                case "1":
                    managerUI.manager();
                    break;
                case "2":
                    customerUI.customer();
                    break;
                case "3":
                    customerService.createEntity();
                    break;
                case "0":
                    connect.getConnection().close();
                    return;
            }
        }
    }
}
