package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TourActionsDaoImpl implements TourActionsDao {
    @Override
    public List<Tour> getAllRows() throws SQLException {
        Statement st = null;
        ResultSet rs = null;
        List<Tour> tourList = new ArrayList<>();
        try {
            Connection conn = (new ConnectToTravelAgencyDB()).setConnection();
            st = conn.createStatement();
            rs = st.executeQuery("select t.id as id, t.name as name, ci.name as city, ct.name as country from tours t\n" +
                    "inner join cities ci on t.city_id = ci.id\n" +
                    "inner join countries ct on ci.country_id = ct.id");
            while (rs.next()) {
                Tour tour = new Tour(rs.getInt("id"), rs.getString("name"), rs.getString("city"), rs.getString("country"));
                tourList.add(tour);
            }
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tourList;
    }
}
