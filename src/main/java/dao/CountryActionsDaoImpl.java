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
    public List<String> getCountryVisitsCountByPeriod(String startDate, String endDate) {
        ResultSet rs = null;
        List<String> countryList = new ArrayList<String>();
        try {
            Connection conn = (new ConnectToTravelAgencyDB()).setConnection();
            //st = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement("select c.name, count(cit.id) as count from countries c\n" +
                    "inner join cities ct on ct.country_id = c.id\n" +
                    "inner join tours t on t.city_id = ct.id\n" +
                    "inner join customer_in_tour cit on t.id = cit.tour_id\n" +
                    "where cit.start_date >= ? and cit.end_date <= ?\n" +
                    "group by c.name");
            ps.setString(1, String.valueOf(startDate));
            ps.setString(2, String.valueOf(endDate));
            rs = ps.executeQuery();

            while (rs.next()) {
                countryList.add(rs.getString("name") + " " + rs.getString("count"));
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
    public List<String> getAllRows() throws SQLException {
        Statement st = null;
        ResultSet rs = null;
        List<String> countryList = new ArrayList<String>();
        try {
            Connection conn = (new ConnectToTravelAgencyDB()).setConnection();
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM countries");
            while (rs.next()) {
                countryList.add(rs.getString("id") + " - " + rs.getString("name"));
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
