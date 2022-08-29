package carsharing.services;

import carsharing.conn.ConnectToH2;
import carsharing.entity.Car;
import carsharing.entity.Company;
import carsharing.repository.CarRepositoryIml;
import carsharing.repository.Repository;
import carsharing.util.Msg;

import java.util.List;
import java.util.stream.IntStream;

public class CarSharingServiceCarImpl implements CarSharingService<Car, Company, Integer> {

    private final Repository<Car, Company> carRepository;
    private List<Car> carList;

    public CarSharingServiceCarImpl(ConnectToH2 connection) {
        this.carRepository = new CarRepositoryIml(connection);
    }

    @Override
    public void createEntity(Company company) {
        System.out.println(Msg.ENTER_CAR_NAME);
        String carName = SCANNER.nextLine();
        Car car = new Car(carName, company.getId());
        carRepository.save(car, company);
        System.out.println(Msg.CAR_WAS_ADDED);
    }

    @Override
    public int chooseEntity(Company company) {
        refreshCarList(company);
        if (carList.isEmpty()) {
            System.out.println(Msg.CAR_LIST_IS_EMPTY);
            return -1;
        }
        System.out.println(Msg.CHOOSE_CAR);
        IntStream.range(0, carList.size())
                .forEach(value -> System.out.printf(Msg.LIST, (value + 1), carList.get(value).getName()));
        System.out.println("0. Back");
        String index = SCANNER.nextLine();
        System.out.println();
        return getInt(index, carList.size());

    }

    @Override
    public void printAll(Company company) {
        refreshCarList(company);
        if (carList.isEmpty()) {
            System.out.println(Msg.CAR_LIST_IS_EMPTY);
            return;
        }
        System.out.println(Msg.CHOOSE_CAR);
        IntStream.range(0, carList.size())
                .forEach(i -> System.out.printf(Msg.LIST, (i + 1), carList.get(i).getName()));
    }

    @Override
    public Car getByIdFromList(Integer id) {
        if (id < 1 || id > carList.size()) {
            return null;
        }
        return carList.get(id - 1);
    }

    @Override
    public Car getById(Integer id) {
        return carRepository.getEntity(id);
    }

    private void refreshCarList(Company company) {
        carList = carRepository.getAll(company);
    }
}
