package model;

import java.time.LocalDateTime;

public class Attendance {
    private int id;
    private int employeeId;
    private LocalDateTime checkinDatetime;
    private LocalDateTime checkoutDatetime;

    // Constructors
    public Attendance() {
        // Default constructor
    }

    public Attendance(int id, int employeeId, LocalDateTime checkinDatetime, LocalDateTime checkoutDatetime) {
        this.id = id;
        this.employeeId = employeeId;
        this.checkinDatetime = checkinDatetime;
        this.checkoutDatetime = checkoutDatetime;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDateTime getCheckinDatetime() {
        return checkinDatetime;
    }

    public void setCheckinDatetime(LocalDateTime checkinDatetime) {
        this.checkinDatetime = checkinDatetime;
    }

    public LocalDateTime getCheckoutDatetime() {
        return checkoutDatetime;
    }

    public void setCheckoutDatetime(LocalDateTime checkoutDatetime) {
        this.checkoutDatetime = checkoutDatetime;
    }

    // toString method
    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", checkinDatetime=" + checkinDatetime +
                ", checkoutDatetime=" + checkoutDatetime +
                '}';
    }
}
