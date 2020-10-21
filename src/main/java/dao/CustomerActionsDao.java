package dao;

import java.util.List;

public interface CustomerActionsDao extends CommonDBActionsDao {
    List<Customer> getCustomerVisitsCount();

    List<Customer> getCustomerExcursions();
}
