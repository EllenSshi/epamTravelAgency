package dao;

import java.sql.SQLException;
import java.util.List;

public interface CommonDBActionsDao<T> {
    List<T> getAllRows() throws SQLException;
}
