package dao;

public class Excursion {
    private final int num;
    private final String name;
    private final String tour;
    private final String city;
    private final String country;

    public Excursion(int num, String name, String tour, String city, String country) {
        this.num = num;
        this.name = name;
        this.tour = tour;
        this.city = city;
        this.country = country;
    }

    public Excursion(String name) {
        this.num = 0;
        this.name = name;
        this.tour = "";
        this.city = "";
        this.country = "";
    }

    public int getNum() {
        return num;
    }

    public String getName() {
        return name;
    }

    public String getTour() {
        return tour;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}
