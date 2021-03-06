package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomerActionsDaoImpl implements CustomerActionsDao {

    @Override
    public List<Customer> getCustomerVisitsCount() {
        Statement st = null;
        ResultSet rs = null;
        List<Customer> customerList = new ArrayList<>();
        try {
            Connection conn = (new ConnectToTravelAgencyDB()).setConnection();
            st = conn.createStatement();
            rs = st.executeQuery("select c.firstname, c.lastname, count(cit.id) as count from customers c\n" +
                    "inner join customer_in_tour cit on c.id = cit.customer_id\n" +
                    "group by c.firstname, c.lastname");
            while (rs.next()) {
                Customer customer = new Customer(rs.getString("firstname"),
                        rs.getString("lastName"),
                        rs.getInt("count"));
                customerList.add(customer);
            }
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerList;
    }

    @Override
    public List<Customer> getCustomerExcursions() {
        Statement st = null;
        ResultSet rs = null;
        List<Customer> customerList = new ArrayList<>();
        try {
            Connection conn = (new ConnectToTravelAgencyDB()).setConnection();
            st = conn.createStatement();
            rs = st.executeQuery("select c.id, c.firstname, c.lastname, e.name as excursion, ce.excursion_date, t.name as tourname,\n" +
                    "       ci.name as city, ct.name as country\n" +
                    "       from customer_excursions_in_tour ce\n" +
                    "inner join excursions e on ce.excursion_id = e.id\n" +
                    "inner join customer_in_tour cit on ce.cust_in_tour_id = cit.id\n" +
                    "inner join customers c on c.id = cit.customer_id\n" +
                    "inner join tours t on t.id = cit.tour_id\n" +
                    "inner join cities ci on ci.id = t.city_id\n" +
                    "inner join countries ct on ct.id = ci.country_id");

            while (rs.next()) {
                int count = 0;
                for (Customer customer: customerList) {
                    if (customer.getId() == rs.getInt("id")) {
                        HashMap<String, String> excursion = new HashMap<>();
                        excursion.put("excursion", rs.getString("excursion"));
                        excursion.put("excursionDate", rs.getString("excursion_date"));
                        excursion.put("tourname", rs.getString("tourname"));
                        excursion.put("city", rs.getString("city"));
                        excursion.put("country", rs.getString("country"));
                        customer.setExcursions(excursion);
                        count++;
                    }
                }
                if (count == 0) {
                    HashMap<String, String> excursion = new HashMap<>();
                    excursion.put("excursion", rs.getString("excursion"));
                    excursion.put("excursionDate", rs.getString("excursion_date"));
                    excursion.put("tourname", rs.getString("tourname"));
                    excursion.put("city", rs.getString("city"));
                    excursion.put("country", rs.getString("country"));
                    ArrayList<HashMap<String, String>> excursionList = new ArrayList<>();
                    excursionList.add(excursion);
                    Customer customer = new Customer(rs.getInt("id"),
                            rs.getString("firstname"),
                            rs.getString("lastname"),
                            excursionList);
                    customerList.add(customer);
                }

            }
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerList;
    }

    @Override
    public List<Customer> getAllRows() throws SQLException {
        Statement st = null;
        ResultSet rs = null;
        List<Customer> customerList = new ArrayList<>();
        try {
            Connection conn = (new ConnectToTravelAgencyDB()).setConnection();
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM customers");
            while (rs.next()) {
                Customer customer = new Customer(rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastName"),
                        rs.getString("phone"));
                customerList.add(customer);
            }
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerList;
    }

    @Override
    public int createNewCustomer(String firstname, String lastname, String phone) {
        Customer customer = new Customer(firstname, lastname, phone);
        int code = 0;
        try {
            Connection conn = (new ConnectToTravelAgencyDB()).setConnection();
            PreparedStatement ps = conn.prepareStatement("insert into customers (firstname, lastname, phone) values (?, ?, ?)");
            ps.setString(1, String.valueOf(customer.getFirstname()));
            ps.setString(2, String.valueOf(customer.getLastname()));
            ps.setString(3, String.valueOf(customer.getPhone()));
            code = ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return code;
    }

    @Override
    public int editCustomerById(int id, String firstname, String lastname, String phone) {
        int code = 0;
        try {
            Connection conn = (new ConnectToTravelAgencyDB()).setConnection();
            PreparedStatement ps = conn.prepareStatement("update customers set firstname=?, lastname=?, phone=? where id=?");
            ps.setString(1, String.valueOf(firstname));
            ps.setString(2, String.valueOf(lastname));
            ps.setString(3, String.valueOf(phone));
            ps.setString(4, String.valueOf(id));
            code = ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return code;
    }

    @Override
    public Customer getCustomerById(int id) {
        ResultSet rs = null;
        Customer customer = null;
        try {
            Connection conn = (new ConnectToTravelAgencyDB()).setConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM customers where id = ?");
            ps.setString(1, String.valueOf(id));
            rs = ps.executeQuery();
            if (rs.next()) {
                customer = new Customer(rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastName"),
                        rs.getString("phone"));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customer;
    }
}
