package carsharing.ui;

import carsharing.entity.Car;
import carsharing.entity.Company;
import carsharing.services.CarSharingService;
import carsharing.util.Msg;

import static carsharing.util.Msg.WRONG_INPUT;

public class ManagerUI {

    private final CarSharingService<Company, Car, Integer> companyService;
    private final CompanyUI companyUI;

    public ManagerUI(CarSharingService<Company, Car, Integer> companyService, CompanyUI companyUI) {
        this.companyService = companyService;
        this.companyUI = companyUI;
    }

    public void manager() {
        while (true) {
            System.out.println(Msg.MANAGER_MENU);
            String input = CarSharingService.SCANNER.nextLine();
            System.out.println();
            switch (input) {
                case "1":
                    int chooseCompany = companyService.chooseEntity();
                    if (chooseCompany == 0) {
                        continue;
                    }
                    if (chooseCompany == -1) {
                        continue;
                    }
                    Company companyById = companyService.getByIdFromList(chooseCompany);
                    companyUI.company(companyById);
                    break;
                case "2":
                    companyService.createEntity();
                    break;
                case "0":
                    return;
                default:
                    System.out.printf(WRONG_INPUT, input);
            }
        }
    }

}
