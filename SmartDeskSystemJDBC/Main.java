import model.Employee;
import model.Desk;
import model.Booking;
import service.BookingService;
import exception.BookingException;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BookingService service = new BookingService();
        Scanner sc = new Scanner(System.in);

        try {
            while (true) {
                System.out.println("\n📋 MENU");
                System.out.println("1. Add Employee");
                System.out.println("2. Add Desk");
                System.out.println("3. Book Desk");
                System.out.println("4. View Available Desks on a Date");
                System.out.println("5. View Booking History of an Employee");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter Employee ID: ");
                        String empId = sc.nextLine();
                        System.out.print("Enter Employee Name: ");
                        String name = sc.nextLine();
                        service.addEmployee(new Employee(empId, name));
                        break;

                    case 2:
                        System.out.print("Enter Desk ID: ");
                        int deskId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Desk Location: ");
                        String location = sc.nextLine();
                        service.addDesk(new Desk(deskId, location));
                        break;

                    case 3:
                        System.out.print("Enter Employee ID: ");
                        String eId = sc.nextLine();
                        System.out.print("Enter Desk ID: ");
                        int dId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Date (yyyy-mm-dd): ");
                        String dateStr = sc.nextLine();
                        Date date = Date.valueOf(dateStr);
                        service.bookDesk(new Booking(0, eId, dId, date));
                        break;

                    case 4:
                        System.out.print("Enter Date (yyyy-mm-dd): ");
                        String viewDateStr = sc.nextLine();
                        Date viewDate = Date.valueOf(viewDateStr);
                        List<Desk> availableDesks = service.getAvailableDesks(viewDate);
                        if (availableDesks.isEmpty()) {
                            System.out.println("❌ No desks available.");
                        } else {
                            System.out.println("✅ Available Desks:");
                            for (Desk d : availableDesks) {
                                System.out.println(d);
                            }
                        }
                        break;

                    case 5:
                        System.out.print("Enter Employee ID: ");
                        String emp = sc.nextLine();
                        List<Booking> history = service.getBookingHistory(emp);
                        if (history.isEmpty()) {
                            System.out.println("❌ No bookings found.");
                        } else {
                            System.out.println("✅ Booking History:");
                            for (Booking b : history) {
                                System.out.println(b);
                            }
                        }
                        break;

                    case 6:
                        System.out.println("Exiting... 👋");
                        return;

                    default:
                        System.out.println("Invalid choice!");
                }
            }
        } catch (BookingException be) {
            System.out.println(be.getMessage());
        } catch (Exception e) {
            System.out.println("❌ An error occurred:");
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}


