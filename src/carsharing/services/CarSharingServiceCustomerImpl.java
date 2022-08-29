package carsharing.services;

import carsharing.conn.ConnectToH2;
import carsharing.entity.Car;
import carsharing.entity.Customer;
import carsharing.repository.CustomerRepositoryImpl;
import carsharing.repository.Repository;
import carsharing.util.Msg;

import java.util.List;
import java.util.stream.IntStream;

public class CarSharingServiceCustomerImpl implements CarSharingService<Customer, Car, Integer> {

    private final Repository<Customer, Car> customerRepository;
    private List<Customer> customerList;

    public CarSharingServiceCustomerImpl(ConnectToH2 connect) {
        this.customerRepository = new CustomerRepositoryImpl(connect);
    }

    @Override
    public void createEntity() {
        System.out.println(Msg.ENTER_CUSTOMER_NAME);
        String customerName = SCANNER.nextLine();
        Customer customer = new Customer(customerName);
        customerRepository.save(customer);
        System.out.println(Msg.CUSTOMER_WAS_ADDED);
    }

    @Override
    public int chooseEntity() {
        refreshList();
        if (customerList.isEmpty()) {
            System.out.println(Msg.CUSTOMER_LIST_IS_EMPTY);
            return -1;
        }
        System.out.println(Msg.CUSTOMER_LIST);
        IntStream.range(0, customerList.size())
                .forEach(value -> System.out.println((value + 1) + ". " + customerList.get(value).getName()));
        System.out.println("0. Back");
        String input = SCANNER.nextLine();
        System.out.println();
        return getInt(input, customerList.size());
    }

    @Override
    public Customer getByIdFromList(Integer id) {
        refreshList();
        if (id < 1 || id > customerList.size()) {
            return null;
        }
        return customerList.get(id - 1);
    }

    @Override
    public Customer getById(Integer integer) {
        return customerRepository.getEntity(integer);
    }

    @Override
    public void updateEntity(Customer customer, String id) {
        customerRepository.update(customer, id);
    }

    private void refreshList() {
        customerList = customerRepository.getAll();
    }
}
