package dao;

import java.util.List;

public interface CountryActionsDao extends CommonDBActionsDao {
    List<String> getCountryVisitsCountByPeriod(String startDate, String endDate);
}
