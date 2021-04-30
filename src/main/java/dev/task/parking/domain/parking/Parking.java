package dev.task.parking.domain.parking;

import dev.task.parking.Util.DateUtil;
import dev.task.parking.domain.time.Time;
import dev.task.parking.domain.truck.Truck;

import java.time.LocalDateTime;
import java.util.List;

public class Parking {
    private final String name;
    private final int slots;
    private final double cost;
    private final List<Truck> trucks;

    public Parking(String name, int slots, double cost, List<Truck> trucks) {
        this.name = name;
        this.slots = slots;
        this.cost = cost;
        this.trucks = trucks;
    }

    public int registerTruck(String licensePlate) {
        if (trucks.size() < slots) {
            if (findTruckByLicensePlate(licensePlate) == null) {
                String inTime = DateUtil.formatDate(LocalDateTime.now()); //"09:00";
                Time time = Time.builder().inTime(inTime).outTime("NO").build();
                this.trucks.add(Truck.builder().licensePlate(licensePlate).time(time).build());
                return 0;
            }
            return 1;
        }
        return 2;
    }

    public Truck findTruckByLicensePlate(String licensePlate) {
        for(Truck truck: trucks) {
            if (truck.getLicensePlate().equalsIgnoreCase(licensePlate)) {
                return truck;
            }
        }
        return null;
    }

    public int makePayment(String licensePlate) {
        Truck truck = findTruckByLicensePlate(licensePlate);
        if (findTruckByLicensePlate(licensePlate) != null) {
            String outTime = DateUtil.formatDate(LocalDateTime.now());
            truck.getTime().setOutTime(outTime);
            double amount = calculateTotalAmount(truck.getTime().getInTime(), truck.getTime().getOutTime());
            truck.setTotalAmount(amount);
            return 0;
        }
      return 1;

    }

    private double calculateTotalAmount(String inTime, String outTime) {
        long diff = DateUtil.formatTime(outTime) - DateUtil.formatTime(inTime);
        double totalHours = (double) diff / (60 * 60 * 1000);
        return DateUtil.formatDouble(totalHours * cost);
    }

    public void generateReport() {
        System.out.println("Placa   -   Hora de Ingreso   -   Hora de Salida   -   Monto Total");
        for (Truck truck: trucks) {
            System.out.println(truck.toString());
        }
    }

    public String getName() {
        return name;
    }

    public static ParkingBuilder builder() {
        return new ParkingBuilder();
    }

    public List<Truck> getTrucks() {
        return trucks;
    }
}
