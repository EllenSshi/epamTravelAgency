package dao;

import java.util.List;

public interface CustomerActionsDao extends CommonDBActionsDao {
    List<Customer> getCustomerVisitsCount();

    List<Customer> getCustomerExcursions();

    Customer getCustomerById(int id);

    int createNewCustomer(String firstname, String lastname, String phone);

    int editCustomerById(int id, String firstname, String lastname, String phone);
}
