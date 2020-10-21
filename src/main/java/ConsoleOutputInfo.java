import dao.Country;
import dao.Customer;
import services.CountryService;
import services.CustomerService;

import java.sql.SQLException;
import java.util.*;
import java.util.regex.Pattern;

class ConsoleOutputInfo {
    public static void main(String[] args) throws SQLException {
        System.out.println("Hello! You are working with Travel Agency database!");
        int action = actionChoosing();
        if (action == 5) {
            exit();
        }
        if (action == 2 || action == 3 || action == 4) {
            System.out.println("This part is not ready yet..");
            exit();
        }
        int entity = 0;
        while (entity == 0) {
            entity = readActionChoosing();
            if (entity == 9) {
                entity = 0;
                action = actionChoosing();
                if (action == 5) {
                    exit();
                }
            }
            if (entity == 10) {
                exit();
            }
        }
        if (action == 1 && entity == 1) {
            List<Country> countryList;
            CountryService countryS = new CountryService();
            countryList = countryS.getAllRows();
            for (Country country: countryList) {
                System.out.println(country.getId() + " - " + country.getName());
            }
        }
        if (action == 1 && entity == 2) {
            System.out.println("Print start date in format YYYY-MM-DD:");
            Pattern datePattern = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");
            Scanner in = new Scanner(System.in);
            String startDate = "";
            try {
                startDate = in.next(datePattern);
            } catch (InputMismatchException exception) {
                System.out.println("Incorrect format!");
                exit();
            }
            String endDate = "";
            System.out.println("Print end date in format YYYY-MM-DD:");
            try {
                endDate = in.next(datePattern);
            } catch (InputMismatchException exception) {
                System.out.println("Incorrect format!");
                exit();
            }
            List<Country> countryList;
            CountryService countryServ = new CountryService();
            countryList = countryServ.getCountryVisitsCountByPeriod(startDate, endDate);
            if (countryList.size() > 0) {
                for (Country country: countryList) {
                    System.out.println(country.getName() + ": " + country.getCount());
                }
            } else {
                System.out.println("No matching data");
            }
        }
        if (action == 1 && entity == 3) {
            List<Customer> customerList;
            CustomerService customerServ = new CustomerService();
            customerList = customerServ.getAllRows();
            for (Customer customer: customerList) {
                System.out.println(customer.getId() + " - " +
                        customer.getFirstname() + " " +
                        customer.getLastname() + " " +
                        customer.getPhone());
            }
        }
        if (action == 1 && entity == 4) {
            List<Customer> customerList;
            CustomerService customerServ = new CustomerService();
            customerList = customerServ.getCustomerVisitsCount();
            for (Customer customer: customerList) {
                System.out.println(customer.getFirstname() + " " +
                        customer.getLastname() + " " +
                        customer.getTourCount());
            }
        }
        if (action == 1 && entity == 5) {
            List<Customer> customerList;
            CustomerService customerServ = new CustomerService();
            customerList = customerServ.getCustomerExcursions();
            for (Customer customer: customerList) {
                System.out.println(customer.getId() + " " +
                        customer.getFirstname() + " " +
                        customer.getLastname() + " " +
                        customer.getExcursions());
            }
        }
        exit();
    }

    public static int actionChoosing() {
        int action = 0;
        while (action != 1 && action != 2 && action != 3 && action != 4 && action != 5) {
            System.out.println("Print a code of what you would like:");
            System.out.println("1 - to read data from the database");
            System.out.println("2 - to edit data in the database");
            System.out.println("3 - to add new data into the database");
            System.out.println("4 - to delete data from the database");
            System.out.println("5 - to exit");
            Scanner in = new Scanner(System.in);
            try {
                action = in.nextInt();
            } catch (InputMismatchException exception) {
                System.out.println("Please, type correct action code:");
            }
        }
        return action;
    }

    public static int readActionChoosing() {
        int readAction = 0;
        while (readAction != 1
                && readAction != 2
                && readAction != 3
                && readAction != 4
                && readAction != 5
                && readAction != 6
                && readAction != 7
                && readAction != 8
                && readAction != 9
                && readAction != 10) {
            System.out.println("Print a code of what you would like to read:");
            System.out.println("1 - All countries");
            System.out.println("2 - Countries with count of visiting within a period of time");
            System.out.println("3 - All customers");
            System.out.println("4 - Customers who took a tour with count of those tours");
            System.out.println("5 - Customers who took an excursion");
            System.out.println("6 - Customers who took an excursion");
            System.out.println("7 - Customers who took an excursion");
            System.out.println("8 - Customers who took an excursion");

            System.out.println("9 - step back to change action");
            System.out.println("10 - to exit");
            Scanner in = new Scanner(System.in);
            try {
                readAction = in.nextInt();
            } catch (InputMismatchException exception) {
                System.out.println("Please, type correct readAction code");
            }
        }
        return readAction;
    }

    public static void exit() {
        System.out.println("\nThank you for using our app!");
        System.exit(0);
    }
}