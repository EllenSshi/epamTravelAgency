package services;

import dao.CountryActionsDaoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryService {
    public List<String> getAllRows() throws SQLException {
        List<String> l = new ArrayList<String>();
        try {
            CountryActionsDaoImpl country = new CountryActionsDaoImpl();
            l = country.getAllRows();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return l;
    }

    public List<String> getCountryVisitsCountByPeriod(String startDate, String endDate) {
        List<String> l = new ArrayList<String>();
        CountryActionsDaoImpl country = new CountryActionsDaoImpl();
        l = country.getCountryVisitsCountByPeriod(startDate, endDate);
        return l;
    }
}
