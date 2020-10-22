package services;

import dao.Tour;
import dao.TourActionsDaoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TourService {
    public List<Tour> getAllRows() throws SQLException {
        List<Tour> tourList = new ArrayList<>();
        try {
            TourActionsDaoImpl tour = new TourActionsDaoImpl();
            tourList = tour.getAllRows();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tourList;
    }
}
