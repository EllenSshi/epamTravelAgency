package services;

import dao.Customer;
import dao.CustomerActionsDaoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    public int createNewCustomer(String firstname, String lastname, String phone) {
        int code = 0;
        CustomerActionsDaoImpl customer = new CustomerActionsDaoImpl();
        code = customer.createNewCustomer(firstname, lastname, phone);
        return code;
    }

    public List<Customer> getAllRows() throws SQLException {
        List<Customer> customerList = new ArrayList<>();
        try {
            CustomerActionsDaoImpl customer = new CustomerActionsDaoImpl();
            customerList = customer.getAllRows();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerList;
    }

    public List<Customer> getCustomerVisitsCount() {
        List<Customer> customerList;
        CustomerActionsDaoImpl customer = new CustomerActionsDaoImpl();
        customerList = customer.getCustomerVisitsCount();
        return customerList;
    }

    public List<Customer> getCustomerExcursions() {
        List<Customer> customerList;
        CustomerActionsDaoImpl customer = new CustomerActionsDaoImpl();
        customerList = customer.getCustomerExcursions();
        return customerList;
    }
}
