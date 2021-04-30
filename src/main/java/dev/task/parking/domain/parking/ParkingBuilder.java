package dev.task.parking.domain.parking;

import dev.task.parking.domain.truck.Truck;

import java.util.ArrayList;
import java.util.List;

public class ParkingBuilder {
    private String name;
    private int slots;
    private double cost;
    private List<Truck> trucks;

    public ParkingBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ParkingBuilder slots(int slots) {
        this.slots = slots;
        return this;
    }

    public ParkingBuilder trucks() {
        this.trucks = new ArrayList<>();
        return this;
    }

    public ParkingBuilder cost(double cost) {
        this.cost = cost;
        return this;
    }

    public Parking build() {
        return new Parking(name, slots, cost, trucks);
    }
}