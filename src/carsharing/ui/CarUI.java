package carsharing.ui;

import carsharing.entity.Car;
import carsharing.entity.Company;
import carsharing.entity.Customer;
import carsharing.services.CarSharingService;

import static carsharing.util.Msg.*;

public class CarUI {

    private final CarSharingService<Company, Car, Integer> companyService;
    private final CarSharingService<Car, Company, Integer> carService;
    private final CarSharingService<Customer, Car, Integer> customerService;

    public CarUI(CarSharingService<Company, Car, Integer> companyService,
                 CarSharingService<Car, Company, Integer> carService,
                 CarSharingService<Customer, Car, Integer> customerService) {
        this.companyService = companyService;
        this.carService = carService;
        this.customerService = customerService;
    }

    public void car(Customer customer) {
        while (true) {
            System.out.println(CUSTOMER_MAIN_MENU);
            String input = CarSharingService.SCANNER.nextLine();
            System.out.println();
            switch (input) {
                case "1":
                    customer = customerService.getById(customer.getId());
                    if (customer.getRentedCarId() != 0) {
                        System.out.println(YOU_ALREADY_RENTED_A_CAR);
                        continue;
                    }

                    int chooseCompany = companyService.chooseEntity();
                    if (chooseCompany == -1) {
                        continue;
                    }

                    Company company = companyService.getByIdFromList(chooseCompany);
                    int chooseCar = carService.chooseEntity(company);
                    if (chooseCar == -1) {
                        continue;
                    }

                    Car car = carService.getByIdFromList(chooseCar);
                    customerService.updateEntity(customer, String.valueOf(car.getId()));
                    System.out.printf(YOU_RENTED_CAR, car.getName());
                    break;
                case "2":
                    customer = customerService.getById(customer.getId());
                    if (customer.getRentedCarId() == 0) {
                        System.out.println(YOU_DIDNT_RENT_A_CAR);
                        continue;
                    }
                    customerService.updateEntity(customer, "DEFAULT");
                    System.out.println(RETURN_CAR);
                    break;
                case "3":
                    customer = customerService.getById(customer.getId());
                    if (customer.getRentedCarId() == 0) {
                        System.out.println(YOU_DIDNT_RENT_A_CAR);
                        continue;
                    }
                    Car car1 = carService.getById(customer.getRentedCarId());
                    Company company1 = companyService.getById(car1.getCompanyId());
                    System.out.printf(YOUR_RENTED_CAR, car1.getName(), company1.getName());
                    break;
                case "0":
                    return;
                default:
                    System.out.printf(WRONG_INPUT, input);
            }
        }
    }
}
