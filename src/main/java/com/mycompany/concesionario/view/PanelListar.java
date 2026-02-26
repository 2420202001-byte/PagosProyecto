package com.mycompany.concesionario.view;

import com.mycompany.concesionario.controller.AutomovilController;
import com.mycompany.concesionario.model.Automovil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelListar extends JPanel {

    private final AutomovilController controller;

    private final DefaultTableModel model = new DefaultTableModel(
            new Object[]{"Placa", "Marca", "Modelo", "Tipo", "Autonomía (km)"}, 0
    );
    private final JTable table = new JTable(model);

    public PanelListar(AutomovilController controller) {
        this.controller = controller;

        setLayout(new BorderLayout(8, 8));

        JLabel title = new JLabel("Listar Automóviles (JTable)");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 16f));
        add(title, BorderLayout.NORTH);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton btnRefrescar = new JButton("Refrescar");
        btnRefrescar.addActionListener(e -> refrescarTabla());
        add(btnRefrescar, BorderLayout.SOUTH);
    }

    public void refrescarTabla() {
        model.setRowCount(0);
        for (Automovil a : controller.listar()) {
            model.addRow(new Object[]{
                    a.getMatricula(),
                    a.getMarca(),
                    a.getModelo(),
                    a.getTipo(),
                    String.format("%.2f", a.calcularAutonomiaKm())
            });
        }
    }
}
