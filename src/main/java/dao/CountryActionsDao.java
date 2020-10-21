package dao;

import java.util.List;

public interface CountryActionsDao extends CommonDBActionsDao {
    List<Country> getCountryVisitsCountByPeriod(String startDate, String endDate);
}
