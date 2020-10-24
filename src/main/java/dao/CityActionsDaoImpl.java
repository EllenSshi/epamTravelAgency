package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CityActionsDaoImpl implements CityActionsDao {
    @Override
    public List<City> getAllRows() throws SQLException {
        Statement st = null;
        ResultSet rs = null;
        List<City> cityList = new ArrayList<>();
        try {
            Connection conn = (new ConnectToTravelAgencyDB()).setConnection();
            st = conn.createStatement();
            rs = st.executeQuery("select ci.id as id, ci.name as name, ct.name as country from cities ci\n" +
                    "inner join countries ct on ci.country_id = ct.id");
            while (rs.next()) {
                City city = new City(rs.getInt("id"), rs.getString("name"), rs.getString("country"));
                cityList.add(city);
            }
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cityList;
    }
}
