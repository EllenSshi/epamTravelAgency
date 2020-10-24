package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ExcursionActionsDaoImpl implements ExcursionActionsDao {
    @Override
    public List<Excursion> getAllRows() throws SQLException {
        Statement st = null;
        ResultSet rs = null;
        List<Excursion> excursionList = new ArrayList<>();
        try {
            Connection conn = (new ConnectToTravelAgencyDB()).setConnection();
            st = conn.createStatement();
            rs = st.executeQuery("select te.id as num, e.name as name, t.name as tour, ci.name as city, ct.name as country\n" +
                    "       from tour_with_excursions te\n" +
                    "inner join excursions e on te.excursion_id = e.id\n" +
                    "inner join tours t on te.tour_id = t.id\n" +
                    "inner join cities ci on t.city_id = ci.id\n" +
                    "inner join countries ct on ci.country_id = ct.id");
            while (rs.next()) {
                Excursion excursion = new Excursion(rs.getInt("num"), rs.getString("name"), rs.getString("tour"), rs.getString("city"), rs.getString("country"));
                excursionList.add(excursion);
            }
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return excursionList;
    }

    @Override
    public int createNewExcursion(String name) {
        Excursion excursion = new Excursion(name);
        int code = 0;
        try {
            Connection conn = (new ConnectToTravelAgencyDB()).setConnection();
            PreparedStatement ps = conn.prepareStatement("insert into excursions (name) values (?)");
            ps.setString(1, String.valueOf(excursion.getName()));
            code = ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return code;
    }

    @Override
    public int deleteExcursionById(int id) {
        int code = 0;
        try {
            Connection conn = (new ConnectToTravelAgencyDB()).setConnection();
            PreparedStatement ps = conn.prepareStatement("delete from excursions where id=? and id not in (select excursion_id from customer_excursions_in_tour)");
            ps.setString(1, String.valueOf(id));
            code = ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return code;
    }
}
