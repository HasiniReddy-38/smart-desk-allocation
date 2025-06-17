package service;

import model.Employee;
import model.Desk;
import model.Booking;
import util.DBUtil;
import exception.BookingException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingService {

    // 1. Register an Employee
    public void addEmployee(Employee emp) throws SQLException {
        String sql = "INSERT INTO EMPLOYEE (emp_id, name) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, emp.getEmpId());
            stmt.setString(2, emp.getName());
            stmt.executeUpdate();
            System.out.println("✅ Employee added successfully!");
        }
    }

    // 2. Add a Desk
    public void addDesk(Desk desk) throws SQLException {
        String sql = "INSERT INTO DESK (desk_id, location) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, desk.getDeskId());
            stmt.setString(2, desk.getLocation());
            stmt.executeUpdate();
            System.out.println("✅ Desk added successfully!");
        }
    }

    // 3. Book a Desk
    public void bookDesk(Booking booking) throws BookingException {
    try (Connection conn = DBUtil.getConnection()) {
        // Step 1: Check if the desk is already booked for the date
        String checkSql = "SELECT * FROM BOOKING WHERE desk_id = ? AND date = ?";
        try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setInt(1, booking.getDeskId());
            checkStmt.setDate(2, booking.getDate());

            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                throw new BookingException("Desk is already booked for that date.");
            }
        }

        // Step 2: Insert the booking if available
        String insertSql = "INSERT INTO BOOKING(emp_id, desk_id, date) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(insertSql)) {
            stmt.setString(1, booking.getEmpId());
            stmt.setInt(2, booking.getDeskId());
            stmt.setDate(3, booking.getDate());

            stmt.executeUpdate();
            System.out.println("✅ Booking successful!");
        }

    } catch (SQLException e) {
        throw new BookingException("Database error: " + e.getMessage());
    }
}
   
    // 4. View Available Desks for a Date
    public List<Desk> getAvailableDesks(Date date) throws SQLException {
        List<Desk> available = new ArrayList<>();
        String sql = "SELECT * FROM DESK WHERE desk_id NOT IN " +
                     "(SELECT desk_id FROM BOOKING WHERE date = ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, date);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Desk d = new Desk(rs.getInt("desk_id"), rs.getString("location"));
                available.add(d);
            }
        }

        return available;
    }

    // 5. View Booking History of an Employee
    public List<Booking> getBookingHistory(String empId) throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM BOOKING WHERE emp_id = ? ORDER BY date DESC";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, empId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Booking b = new Booking(
                    rs.getInt("booking_id"),
                    rs.getString("emp_id"),
                    rs.getInt("desk_id"),
                    rs.getDate("date")
                );
                bookings.add(b);
            }
        }

        return bookings;
    }
}
