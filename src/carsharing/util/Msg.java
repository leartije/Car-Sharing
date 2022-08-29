package carsharing.util;

public class Msg {

    //menus
    public static final String MAIN_MENU = "1. Log in as a manager\n" +
            "2. Log in as a customer\n" +
            "3. Create a customer\n" +
            "0. Exit";

    public static final String MANAGER_MENU = "1. Company list\n" +
            "2. Create a company\n" +
            "0. Back";

    public static final String COMPANY_MAIN_MENU = "'%s' company:%n" +
            "1. Car list%n" +
            "2. Create a car%n" +
            "0. Back%n";


    public static final String CUSTOMER_MAIN_MENU = "1. Rent a car\n" +
            "2. Return a rented car\n" +
            "3. My rented car\n" +
            "0. Back";

    //company
    public static final String CHOOSE_COMPANY = "Choose a company:";
    public static final String COMPANY_LIST_IS_EMPTY = "The company list is empty!\n";
    public static final String ENTER_COMPANY_NAME = "Enter the company name:";
    public static final String COMPANY_CREATED = "The company was created!\n";

    //car
    public static final String CHOOSE_CAR = "Choose a car:";
    public static final String ENTER_CAR_NAME = "Enter the car name:";
    public static final String CAR_WAS_ADDED = "The car was added!\n";
    public static final String CAR_LIST_IS_EMPTY = "The car list is empty!\n";
    public static final String YOU_RENTED_CAR = "You rented '%s'%n";
    public static final String YOU_ALREADY_RENTED_A_CAR = "You've already rented a car!\n";
    public static final String YOU_DIDNT_RENT_A_CAR = "You didn't rent a car!\n";
    public static final String RETURN_CAR = "You've returned a rented car!\n";
    public static final String YOUR_RENTED_CAR = """
            Your rented car:
            %s
            Company:
            %s%n
            """;

    //customer
    public static final String CUSTOMER_LIST = "Customer list:";
    public static final String ENTER_CUSTOMER_NAME = "Enter the customer name:";
    public static final String CUSTOMER_WAS_ADDED = "The customer was added!\n";
    public static final String CUSTOMER_LIST_IS_EMPTY = "The customer list is empty!\n";



    //variables
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String COMPANY_ID = "company_id";
    public static final String RENTED_CAR_ID = "rented_car_id";


    //generic
    public static final String LIST = "%d. %s%n";
    public static final String WRONG_INPUT = "'%s' is not valid input, try again%n%n";
    public static final String INDEX_OUT_OF_BOUND = "'%d' is out of bound%n%n";

}
