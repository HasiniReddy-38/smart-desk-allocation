package model;

public class Desk {
    private int deskId;
    private String location;

    public Desk(int deskId, String location) {
        this.deskId = deskId;
        this.location = location;
    }

    public int getDeskId() {
        return deskId;
    }

    public String getLocation() {
        return location;
    }

    public void setDeskId(int deskId) {
        this.deskId = deskId;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Desk [deskId=" + deskId + ", location=" + location + "]";
    }
}

