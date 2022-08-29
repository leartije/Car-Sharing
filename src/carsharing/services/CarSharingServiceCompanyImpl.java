package carsharing.services;

import carsharing.conn.ConnectToH2;
import carsharing.entity.Car;
import carsharing.entity.Company;
import carsharing.repository.CompanyRepositoryImpl;
import carsharing.repository.Repository;
import carsharing.util.Msg;

import java.util.List;
import java.util.stream.IntStream;

public class CarSharingServiceCompanyImpl implements CarSharingService<Company, Car, Integer> {

    private final Repository<Company, Car> companyRepository;

    private List<Company> companyList;

    public CarSharingServiceCompanyImpl(ConnectToH2 connection) {
        this.companyRepository = new CompanyRepositoryImpl(connection);

    }

    @Override
    public void createEntity() {
        System.out.println(Msg.ENTER_COMPANY_NAME);
        String name = SCANNER.nextLine();
        Company company = new Company(name);
        companyRepository.save(company);
        System.out.println(Msg.COMPANY_CREATED);
    }

    @Override
    public int chooseEntity() {
        refreshList();
        if (companyList.isEmpty()) {
            System.out.println(Msg.COMPANY_LIST_IS_EMPTY);
            return -1;
        }
        System.out.println(Msg.CHOOSE_COMPANY);
        IntStream.range(0, companyList.size())
                .forEach(value -> System.out.printf(Msg.LIST, (value + 1), companyList.get(value).getName()));
        System.out.println("0. Back");
        String input = SCANNER.nextLine();
        System.out.println();
        return getInt(input, companyList.size());
    }


    @Override
    public Company getByIdFromList(Integer id) {
        refreshList();
        if (id < 1 || id > companyList.size()) {
            return null;
        }
        return companyList.get(id - 1);
    }

    @Override
    public Company getById(Integer integer) {
        return companyRepository.getEntity(integer);
    }

    private void refreshList() {
        this.companyList = companyRepository.getAll();
    }
}
