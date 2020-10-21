package dao;

public class Country {
    private final int id;
    private final String name;
    private final int count;

    public Country(int id, String name) {
        this.id = id;
        this.name = name;
        this.count = 0;
    }

    public Country(int id, String name, int count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }
}
