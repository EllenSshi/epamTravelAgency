package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CountryActionsDaoImpl implements CountryActionsDao {
    @Override
    public List<Country> getCountryVisitsCountByPeriod(String startDate, String endDate) {
        ResultSet rs = null;
        List<Country> countryList = new ArrayList<>();
        try {
            Connection conn = (new ConnectToTravelAgencyDB()).setConnection();
            PreparedStatement ps = conn.prepareStatement("select c.id, c.name, count(cit.id) as count from countries c\n" +
                    "inner join cities ct on ct.country_id = c.id\n" +
                    "inner join tours t on t.city_id = ct.id\n" +
                    "inner join customer_in_tour cit on t.id = cit.tour_id\n" +
                    "where cit.start_date >= ? and cit.end_date <= ?\n" +
                    "group by c.name");
            ps.setString(1, String.valueOf(startDate));
            ps.setString(2, String.valueOf(endDate));
            rs = ps.executeQuery();

            while (rs.next()) {
                Country country = new Country(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("count"));
                countryList.add(country);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return countryList;
    }

    @Override
    public List<Country> getAllRows() throws SQLException {
        Statement st = null;
        ResultSet rs = null;
        List<Country> countryList = new ArrayList<>();
        try {
            Connection conn = (new ConnectToTravelAgencyDB()).setConnection();
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM countries");
            while (rs.next()) {
                Country country = new Country(rs.getInt("id"), rs.getString("name"));
                countryList.add(country);
            }
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return countryList;
    }
}
