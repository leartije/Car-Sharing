package carsharing.ui;

import carsharing.entity.Car;
import carsharing.entity.Customer;
import carsharing.services.CarSharingService;

public class CustomerUI {

    private final CarSharingService<Customer, Car, Integer> customerService;
    private final CarUI carUI;

    public CustomerUI(CarSharingService<Customer, Car, Integer> customerService, CarUI carUI) {
        this.customerService = customerService;
        this.carUI = carUI;
    }

    public void customer() {
        while (true) {
            int id = customerService.chooseEntity();
            if (id == -1) {
                return;
            }
            Customer customerById = customerService.getByIdFromList(id);
            carUI.car(customerById);
        }
    }

}
