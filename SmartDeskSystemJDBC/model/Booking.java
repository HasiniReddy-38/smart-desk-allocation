package model;

import java.sql.Date;

public class Booking {
    private int bookingId;
    private String empId;
    private int deskId;
    private Date date;

    public Booking(int bookingId, String empId, int deskId, Date date) {
        this.bookingId = bookingId;
        this.empId = empId;
        this.deskId = deskId;
        this.date = date;
    }

    public int getBookingId() {
        return bookingId;
    }

    public String getEmpId() {
        return empId;
    }

    public int getDeskId() {
        return deskId;
    }

    public Date getDate() {
        return date;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public void setDeskId(int deskId) {
        this.deskId = deskId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Booking [bookingId=" + bookingId + ", empId=" + empId +
               ", deskId=" + deskId + ", date=" + date + "]";
    }
}

