package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CommonDBActionsDao {
    List<String> getAllRows() throws SQLException;
}
