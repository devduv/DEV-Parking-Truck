package dev.task.parking.panel;

import dev.task.parking.domain.parking.Parking;
import dev.task.parking.domain.truck.Truck;
import jdk.nashorn.internal.runtime.regexp.joni.Option;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class JFrameParking {
    private Parking parking;
    private JFrame app;
    private JPanel panel;
    private JTextField licensePlateInput;
    private JButton addTruckButton = new JButton("Agregar Camión");
    private JButton makePaymentButton = new JButton("Realizar Pago");
    private JButton generateReportButton = new JButton("Reporte de Camiones");
    private JTable table;

    public JFrameParking(Parking parking) {
        this.parking = parking;
    }

    public void init() {
        app = new JFrame("Parking");
        panel = new JPanel();
        panel.add(new JLabel("Playa de Estacionamiento"));

        addField();
        addButtons();
        addTable();
        listener();
        app.add(panel);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.pack();
        app.setSize(400, 400);
        app.setLocationRelativeTo(null);
        app.setVisible(true);


    }

    private void addField() {
        licensePlateInput = new JTextField(10);
        panel.add(licensePlateInput, BorderLayout.SOUTH);
    }

    private void addButtons() {
        panel.add(addTruckButton);
        panel.add(makePaymentButton);
        panel.add(generateReportButton);
    }

    private void addTable() {
        String[] columnsNames = {"Place", "Hora de Ingreso", "Hora de Salida", "Monto Total"};

        if (table == null) {
            table = new JTable() {
                public boolean isCellEditable(int nRow, int nCol) {
                    return false;
                }
            };
        }
        DefaultTableModel contactTableModel = (DefaultTableModel) table
                .getModel();
        contactTableModel.setColumnIdentifiers(columnsNames);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panel.add(table);
    }

    private void removeTable() {
        DefaultTableModel dm = (DefaultTableModel) table.getModel();
        int rowCount = dm.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
        }
    }

    private void updateTable() {
        removeTable();
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

        for (int i = 0; i < parking.getTrucks().size(); i++) {
            String[] data = new String[7];
            Truck truck = parking.getTrucks().get(i);
            data[0] = truck.getLicensePlate();
            data[1] = truck.getTime().getInTime();
            data[2] = truck.getTime().getOutTime();
            data[3] = "S./" + truck.getTotalAmount();

            tableModel.addRow(data);
        }
        table.setModel(tableModel);
    }

    private void showMessage(String title, String body, int type) {
        JOptionPane.showMessageDialog(null, body, title, type);
    }
    private void listener() {
        this.addTruckButton.addActionListener(l ->
        {
            int registered = parking.registerTruck(licensePlateInput.getText().toString());
            if (registered == 0) {
                showMessage("Registrar", "Registrado correctamente",JOptionPane.INFORMATION_MESSAGE);
            } else if (registered == 1) {
                showMessage("Registrar", "Camión ya existe",JOptionPane.WARNING_MESSAGE);
            } else {
                showMessage("Registrar", "Ya no se puede registrar más camiones",JOptionPane.ERROR_MESSAGE);
            }
            updateTable();
        });
        this.makePaymentButton.addActionListener(l ->
        {
            int paid = parking.makePayment(licensePlateInput.getText().toString());
            if (paid == 0) {
                showMessage("Realizar pago", "Pago realizado correctamente",JOptionPane.INFORMATION_MESSAGE);
            } else {
                showMessage("Realizar pago", "Camión no se encuentra regitrado para realizar el pago",JOptionPane.ERROR_MESSAGE);
            }
            updateTable();
        });
        this.generateReportButton.addActionListener(l ->
        {
            parking.generateReport();
            updateTable();
        });
    }
}
