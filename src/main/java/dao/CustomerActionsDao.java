package dao;

import java.util.List;

public interface CustomerActionsDao extends CommonDBActionsDao {
    List<String> getCustomerVisitsCount();

    List<String> getCustomerExcursions();
}
