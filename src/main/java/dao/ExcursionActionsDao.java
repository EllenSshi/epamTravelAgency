package dao;

public interface ExcursionActionsDao extends CommonDBActionsDao {
    int createNewExcursion(String name);

    int deleteExcursionById(int id);
}
