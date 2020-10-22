package services;

import dao.City;
import dao.CityActionsDaoImpl;
import dao.Country;
import dao.CountryActionsDaoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityService {
    public List<City> getAllRows() throws SQLException {
        List<City> cityList = new ArrayList<>();
        try {
            CityActionsDaoImpl city = new CityActionsDaoImpl();
            cityList = city.getAllRows();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cityList;
    }
}
