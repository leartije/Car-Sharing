package carsharing.ui;

import carsharing.entity.Car;
import carsharing.entity.Company;
import carsharing.services.CarSharingService;
import carsharing.util.Msg;

import static carsharing.util.Msg.WRONG_INPUT;

public class CompanyUI {
    private final CarSharingService<Car, Company, Integer> carService;

    public CompanyUI(CarSharingService<Car, Company, Integer> carService) {
        this.carService = carService;
    }

    public void company(Company company) {
        while (true) {
            System.out.printf(Msg.COMPANY_MAIN_MENU, company.getName());
            String input = CarSharingService.SCANNER.nextLine();
            System.out.println();
            switch (input) {
                case "1":
                    carService.printAll(company);
                    break;
                case "2":
                    carService.createEntity(company);
                    break;
                case "0":
                    return;
                default:
                    System.out.printf(WRONG_INPUT, input);
            }
        }

    }
}
