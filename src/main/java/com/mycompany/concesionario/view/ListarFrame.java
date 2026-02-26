package com.mycompany.concesionario.view;

import com.mycompany.concesionario.controller.AutomovilController;
import com.mycompany.concesionario.model.Automovil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ListarFrame extends JFrame {
    private final AutomovilController controller;
    private final DefaultTableModel model = new DefaultTableModel(
            new Object[]{"Matrícula", "Marca", "Modelo", "Tipo", "Autonomía (km)"}, 0
    );
    private final JTable table = new JTable(model);

    public ListarFrame(AutomovilController controller) {
        this.controller = controller;
        setTitle("Listar Automóviles");
        setSize(800, 350);
        setLocationRelativeTo(null);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton btnRefrescar = new JButton("Refrescar");
        btnRefrescar.addActionListener(e -> cargar());
        add(btnRefrescar, BorderLayout.SOUTH);

        cargar();
    }

    private void cargar() {
        model.setRowCount(0);
        for (Automovil a : controller.listar()) {
            model.addRow(new Object[]{
                    a.getMatricula(), a.getMarca(), a.getModelo(), a.getTipo(),
                    String.format("%.2f", a.calcularAutonomiaKm())
            });
        }
    }
}
