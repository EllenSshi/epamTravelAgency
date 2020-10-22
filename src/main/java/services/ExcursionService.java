package services;

import dao.CustomerActionsDaoImpl;
import dao.Excursion;
import dao.ExcursionActionsDaoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExcursionService {
    public List<Excursion> getAllRows() throws SQLException {
        List<Excursion> excursionList = new ArrayList<>();
        try {
            ExcursionActionsDaoImpl excursion = new ExcursionActionsDaoImpl();
            excursionList = excursion.getAllRows();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return excursionList;
    }

    public int createNewExcursion(String name) {
        int code = 0;
        ExcursionActionsDaoImpl excursion = new ExcursionActionsDaoImpl();
        code = excursion.createNewExcursion(name);
        return code;
    }
}
