import dao.City;
import dao.Country;
import dao.Customer;
import dao.Excursion;
import dao.Tour;
import services.CityService;
import services.CountryService;
import services.CustomerService;
import services.ExcursionService;
import services.TourService;

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
        int entity = 0;
        if (action == 3) {
            entity = editActionChoosing();
            if (entity == 2) {
                exit();
            }
        }
        if (action == 1) {
            entity = readActionChoosing();
            if (entity == 10) {
                exit();
            }
        }
        if (action == 2) {
            entity = addActionChoosing();
            if (entity == 3) {
                exit();
            }
        }
        if (action == 4) {
            entity = deleteActionChoosing();
            if (entity == 3) {
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
        if (action == 1 && entity == 6) {
            List<City> cityList;
            CityService cityServ = new CityService();
            cityList = cityServ.getAllRows();
            for (City city: cityList) {
                System.out.println(city.getId() + " - " +
                        city.getName() + " (" +
                        city.getCountry() + ")");
            }
        }
        if (action == 1 && entity == 7) {
            List<Tour> tourList;
            TourService tourServ = new TourService();
            tourList = tourServ.getAllRows();
            for (Tour tour: tourList) {
                System.out.println(tour.getId() + " - " +
                        tour.getName() + " (" +
                        tour.getCity() + ", " +
                        tour.getCountry() + ")");
            }
        }
        if (action == 1 && entity == 8) {
            List<Excursion> excursionList;
            ExcursionService excursionServ = new ExcursionService();
            excursionList = excursionServ.getAllRows();
            for (Excursion excursion: excursionList) {
                System.out.println(excursion.getNum() + " - Excursion: " +
                        excursion.getName() + ", Tour: " +
                        excursion.getTour() + " (" +
                        excursion.getCity() + ", " +
                        excursion.getCountry() + ")");
            }
        }
        if (action == 2 && entity == 1) {
            System.out.println("Print customer firstname");
            Scanner in = new Scanner(System.in);
            String firstname = in.next();
            System.out.println("Print customer lastname");
            String lastname = in.next();
            System.out.println("Print customer phone");
            String phone = in.next();
            int code = 0;
            CustomerService customerServ = new CustomerService();
            code = customerServ.createNewCustomer(firstname, lastname, phone);
            if (code == 1) {
                System.out.println("Success! Customer created.");
            } else {
                System.out.println("Error! Customer not created");
            }
        }
        if (action == 2 && entity == 2) {
            System.out.println("Print excursion name");
            Scanner in = new Scanner(System.in);
            String excursionName = in.next();
            int code = 0;
            ExcursionService excursionServ = new ExcursionService();
            code = excursionServ.createNewExcursion(excursionName);
            if (code == 1) {
                System.out.println("Success! Excursion created.");
            } else {
                System.out.println("Error! Excursion not created");
            }
        }
        if (action == 4 && entity == 1) {
            System.out.println("Print tour id to delete");
            Scanner in = new Scanner(System.in);
            int tourId = in.nextInt();
            int code = 0;
            TourService tourServ = new TourService();
            code = tourServ.deleteTourById(tourId);
            if (code == 1) {
                System.out.println("Success! Tour deleted.");
            } else {
                System.out.println("Error! Tour cannot be deleted or does not exist");
            }
        }
        if (action == 4 && entity == 2) {
            System.out.println("Print excursion id to delete");
            Scanner in = new Scanner(System.in);
            int excursionId = in.nextInt();
            int code = 0;
            ExcursionService excursionServ = new ExcursionService();
            code = excursionServ.deleteExcursionById(excursionId);
            if (code == 1) {
                System.out.println("Success! Excursion deleted.");
            } else {
                System.out.println("Error! Excursion cannot be deleted or does not exist");
            }
        }
        if (action == 3 && entity == 1) {
            System.out.println("Print customer id to edit");
            Scanner in = new Scanner(System.in);
            int customerId = in.nextInt();
            int code = 0;
            CustomerService custServ = new CustomerService();
            Customer customer = custServ.getCustomerById(customerId);
            System.out.println(customer.getId() + " - " +
                    customer.getFirstname() + " " +
                    customer.getLastname() + ", phone: " +
                    customer.getPhone());
            System.out.println("Print new customer values in format: firstname/lastname/phone");
            String[] custData = in.next().split("/");
            customer.setFirstname(custData[0]);
            customer.setLastname(custData[1]);
            customer.setPhone(custData[2]);
            code = custServ.editCustomerById(customer.getId(),
                    customer.getFirstname(),
                    customer.getLastname(),
                    customer.getPhone());
            if (code == 1) {
                System.out.println("Success! Customer updated.");
            } else {
                System.out.println("Error! Customer cannot be updated");
            }
        }
        exit();
    }

    public static int actionChoosing() {
        int action = 0;
        while (action != 1 && action != 2 && action != 3 && action != 4 && action != 5) {
            System.out.println("Print a code of what you would like:");
            System.out.println("1 - to read data from the database");
            System.out.println("2 - to add data in the database");
            System.out.println("3 - to edit new data into the database");
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

    public static int addActionChoosing() {
        int addAction = 0;
        while (addAction != 1 && addAction != 2 && addAction != 3) {
            System.out.println("Print a code of what you would like to add:");
            System.out.println("1 - to add customer");
            System.out.println("2 - to add excursion");
            System.out.println("3 - to exit");
            Scanner in = new Scanner(System.in);
            try {
                addAction = in.nextInt();
            } catch (InputMismatchException exception) {
                System.out.println("Please, type correct addAction code:");
            }
        }
        return addAction;
    }

    public static int deleteActionChoosing() {
        int addAction = 0;
        while (addAction != 1 && addAction != 2 && addAction != 3) {
            System.out.println("Print a code of what you would like to add:");
            System.out.println("1 - delete tour by id");
            System.out.println("2 - delete excursion by id");
            System.out.println("3 - to exit");
            Scanner in = new Scanner(System.in);
            try {
                addAction = in.nextInt();
            } catch (InputMismatchException exception) {
                System.out.println("Please, type correct addAction code:");
            }
        }
        return addAction;
    }

    public static int editActionChoosing() {
        int editAction = 0;
        while (editAction != 1 && editAction != 2) {
            System.out.println("Print a code of what you would like to add:");
            System.out.println("1 - edit customer by id");
            System.out.println("2 - exit");
            Scanner in = new Scanner(System.in);
            try {
                editAction = in.nextInt();
            } catch (InputMismatchException exception) {
                System.out.println("Please, type correct editAction code:");
            }
        }
        return editAction;
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
                && readAction != 10) {
            System.out.println("Print a code of what you would like to read:");
            System.out.println("1 - All countries");
            System.out.println("2 - Countries with count of visiting within a period of time");
            System.out.println("3 - All customers");
            System.out.println("4 - Customers who took a tour with count of those tours");
            System.out.println("5 - Customers who took an excursion");
            System.out.println("6 - All cities");
            System.out.println("7 - All tours");
            System.out.println("8 - Excursions which are in tours");

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