package services;

import dao.CustomerActionsDaoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    public List<String> getAllRows() throws SQLException {
        List<String> l = new ArrayList<String>();
        try {
            CustomerActionsDaoImpl customer = new CustomerActionsDaoImpl();
            l = customer.getAllRows();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return l;
    }

    public List<String> getCustomerVisitsCount() {
        List<String> l = new ArrayList<String>();
        CustomerActionsDaoImpl customer = new CustomerActionsDaoImpl();
        l = customer.getCustomerVisitsCount();
        return l;
    }

    public List<String> getCustomerExcursions() {
        List<String> l = new ArrayList<String>();
        CustomerActionsDaoImpl customer = new CustomerActionsDaoImpl();
        l = customer.getCustomerExcursions();
        return l;
    }
}
