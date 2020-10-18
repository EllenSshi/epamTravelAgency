package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerActionsDaoImpl implements CustomerActionsDao {

    @Override
    public List<String> getCustomerVisitsCount() {
        Statement st = null;
        ResultSet rs = null;
        List<String> countryList = new ArrayList<String>();
        try {
            Connection conn = (new ConnectToTravelAgencyDB()).setConnection();
            st = conn.createStatement();
            rs = st.executeQuery("select c.firstname, c.lastname, count(cit.id) as count from customers c\n" +
                    "inner join customer_in_tour cit on c.id = cit.customer_id\n" +
                    "group by c.firstname, c.lastname");
            while (rs.next()) {
                countryList.add(rs.getString("firstName") + " " + rs.getString("lastName") + " - " + rs.getString("count"));
            }
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return countryList;
    }

    @Override
    public List<String> getCustomerExcursions() {
        Statement st = null;
        ResultSet rs = null;
        List<String> countryList = new ArrayList<String>();
        try {
            Connection conn = (new ConnectToTravelAgencyDB()).setConnection();
            st = conn.createStatement();
            rs = st.executeQuery("select ce.id, c.firstname, c.lastname, e.name as excursion, ce.excursion_date, t.name as tourname,\n" +
                    "       ci.name as city, ct.name as country\n" +
                    "       from customer_excursions_in_tour ce\n" +
                    "inner join excursions e on ce.excursion_id = e.id\n" +
                    "inner join customer_in_tour cit on ce.cust_in_tour_id = cit.id\n" +
                    "inner join customers c on c.id = cit.customer_id\n" +
                    "inner join tours t on t.id = cit.tour_id\n" +
                    "inner join cities ci on ci.id = t.city_id\n" +
                    "inner join countries ct on ct.id = ci.country_id");
            while (rs.next()) {
                countryList.add(rs.getString("id") + " - " +
                        rs.getString("firstname") + " " +
                        rs.getString("lastname") + " '" +
                        rs.getString("excursion") + "' " +
                        rs.getString("excursion_date") + " '" +
                        rs.getString("tourname") + "' " +
                        rs.getString("city") + " " +
                        rs.getString("country")
                );
            }
            rs.close();
            st.close();
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
            rs = st.executeQuery("SELECT * FROM customers");
            while (rs.next()) {
                countryList.add(rs.getString("id") + " - " +
                        rs.getString("firstname") + " " +
                        rs.getString("lastName") + " (" +
                        rs.getString("phone") + ")"
                );
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
