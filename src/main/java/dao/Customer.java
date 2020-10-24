package dao;

import java.util.ArrayList;
import java.util.HashMap;

public class Customer {
    private final int id;
    private  String firstname;
    private  String lastname;
    private  String phone;
    private final int tourCount;
    private ArrayList<HashMap<String, String>> excursions;

    public Customer(int id, String firstname, String lastname, String phone) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.excursions = null;
        this.tourCount = 0;
    }

    public Customer(String firstname, String lastname, String phone) {
        this.id = 0;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.excursions = null;
        this.tourCount = 0;
    }

    public Customer(String firstname, String lastname, int tourCount) {
        this.excursions = null;
        this.id = 0;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = "";
        this.tourCount = tourCount;
    }

    public Customer(int id, String firstname, String lastname, ArrayList<HashMap<String, String>> excursions) {
        this.excursions = excursions;
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = "";
        this.tourCount = 0;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhone() {
        return phone;
    }

    public int getTourCount() {
        return tourCount;
    }

    public ArrayList<HashMap<String, String>> getExcursions() {
        return excursions;
    }

    public void setExcursions(HashMap<String, String> excursion) {
        this.excursions.add(excursion);
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
