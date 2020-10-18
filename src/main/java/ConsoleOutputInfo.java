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
            if (entity == 6) {
                entity = 0;
                action = actionChoosing();
                if (action == 5) {
                    exit();
                }
            }
            if (entity == 7) {
                exit();
            }
        }
        if (action == 1 && entity == 1) {
            List<String> l = new ArrayList<String>();
            CountryService country = new CountryService();
            l = country.getAllRows();
            for (String row: l) {
                System.out.println(row);
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
            List<String> l = new ArrayList<String>();
            CountryService country = new CountryService();
            l = country.getCountryVisitsCountByPeriod(startDate, endDate);
            if (l.size() > 0) {
                for (String row: l) {
                    System.out.println(row);
                }
            } else {
                System.out.println("No matching data");
            }
        }
        if (action == 1 && entity == 3) {
            List<String> l = new ArrayList<String>();
            CustomerService customer = new CustomerService();
            l = customer.getAllRows();
            for (String row: l) {
                System.out.println(row);
            }
        }
        if (action == 1 && entity == 4) {
            List<String> l = new ArrayList<String>();
            CustomerService customer = new CustomerService();
            l = customer.getCustomerVisitsCount();
            for (String row: l) {
                System.out.println(row);
            }
        }
        if (action == 1 && entity == 5) {
            List<String> l = new ArrayList<String>();
            CustomerService customer = new CustomerService();
            l = customer.getCustomerExcursions();
            for (String row: l) {
                System.out.println(row);
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
                && readAction != 7) {
            System.out.println("Print a code of what you would like to read:");
            System.out.println("1 - All countries");
            System.out.println("2 - Countries with count of visiting within a period of time");
            System.out.println("3 - All customers");
            System.out.println("4 - Customers who took a tour with count of those tours");
            System.out.println("5 - Customers who took an excursion");

            System.out.println("6 - step back to change action");
            System.out.println("7 - to exit");
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