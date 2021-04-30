package dev.task.parking.domain.truck;

import dev.task.parking.domain.time.Time;

public class TruckBuilder {
    private String licensePlate;
    private Time time;
    private double totalAmount;


    public TruckBuilder licensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
        return this;
    }

    public TruckBuilder time(Time time) {
        this.time = time;
        return this;
    }

    public TruckBuilder totalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }


    public Truck build() {
        return new Truck(licensePlate, time, totalAmount);
    }
}