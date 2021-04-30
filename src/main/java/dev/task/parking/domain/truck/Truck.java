package dev.task.parking.domain.truck;

import dev.task.parking.domain.time.Time;

public class Truck {
    private final String licensePlate;
    private final Time time;
    private double totalAmount;

    public Truck(String licensePlate, Time time, double totalAmount) {
        this.licensePlate = licensePlate;
        this.time = time;
        this.totalAmount = totalAmount;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Time getTime() {
        return time;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return licensePlate + "       ,         " + time.getInTime() + "      ,      " + time.getOutTime()
                + "   -   S./" + totalAmount;
    }

    public static TruckBuilder builder() {
        return new TruckBuilder();
    }

    public void setTotalAmount(double amount) {
        this.totalAmount = amount;
    }
}
