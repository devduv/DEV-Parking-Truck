package dev.task.parking;

import dev.task.parking.domain.parking.Parking;
import dev.task.parking.panel.JFrameParking;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {

    public static Scanner read = new Scanner(System.in);
    public static Parking parking;

    public static void main(String[] args) {
        initOnFrame();
        // initOnConsole();
    }

    public static void initOnFrame() {
        parking = Parking.builder()
                .name("Al Fondo Hay Sitio")
                .slots(10)
                .trucks()
                .cost(5.5)
                .build();

        JFrameParking app = new JFrameParking(parking);
        app.init();
    }

    public static void initOnConsole() {
        int slots;
        System.out.print("Ingrese el número de Espacios del estacionamiento: ");
        slots = read.nextInt();

        parking = Parking.builder()
                .name("Al Fondo Hay Sitio")
                .slots(slots)
                .trucks()
                .cost(5.5)
                .build();

        System.out.println("Playa de Estacionamiento " + parking.getName());

        menu();
    }

    public static void menu() {
        int option;
        do {

            System.out.println("(1) Agregar Camión");
            System.out.println("(2) Realizar Pago");
            System.out.println("(3) Reporte de Camiones");
            System.out.println("(0) Salir");

            System.out.print("Seleccione una opción: ");
            try {
                option = read.nextInt();
                read.nextLine();
                optionSelected(option);
            } catch (InputMismatchException e) {
                System.out.println("Por favor ingrese un número..");
                option = -1;
                read.nextLine();
            }


            System.out.println(" - - - - - - - - - -");
        } while (option != 0);
    }

    public static String readTruck() {
        System.out.print("Ingresar Placa del camión: ");
        return read.nextLine();
    }

    public static void optionSelected(int option) {
        switch (option) {
            case 1:
                String licensePlate = readTruck();
                int registered = parking.registerTruck(licensePlate);
                System.out.println("Return: " + registered);
                break;
            case 2:
                String licensePlateRegistered = readTruck();
                int paid = parking.makePayment(licensePlateRegistered);
                System.out.println("Return: " + paid);
                break;
            case 3:
                parking.generateReport();
                break;
            case 0:
                System.out.println("Se finalizó el sistema");
                break;
            default:
                break;
        }
    }

}
