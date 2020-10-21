package services;

import dao.CountryActionsDaoImpl;
import dao.Country;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryService {
    public List<Country> getAllRows() throws SQLException {
        List<Country> countryList = new ArrayList<>();
        try {
            CountryActionsDaoImpl country = new CountryActionsDaoImpl();
            countryList = country.getAllRows();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return countryList;
    }

    public List<Country> getCountryVisitsCountByPeriod(String startDate, String endDate) {
        List<Country> countryList = new ArrayList<>();
        CountryActionsDaoImpl country = new CountryActionsDaoImpl();
        countryList = country.getCountryVisitsCountByPeriod(startDate, endDate);
        return countryList;
    }
}
