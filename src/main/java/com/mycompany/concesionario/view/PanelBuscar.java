package com.mycompany.concesionario.view;

import com.mycompany.concesionario.controller.AutomovilController;
import com.mycompany.concesionario.model.Automovil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PanelBuscar extends JPanel {

    private final AutomovilController controller;

    private final JTextField txtPlaca = new JTextField(14);
    private final JTextArea txtResultado = new JTextArea(10, 50);

    public PanelBuscar(AutomovilController controller) {
        this.controller = controller;

        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(14, 14, 14, 14));

        JLabel title = new JLabel("Buscar automóvil por placa");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 16f));
        add(title, BorderLayout.NORTH);

        txtResultado.setEditable(false);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        top.add(new JLabel("Placa:"));
        top.add(txtPlaca);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> buscar());
        top.add(btnBuscar);

        add(top, BorderLayout.CENTER);
        add(new JScrollPane(txtResultado), BorderLayout.SOUTH);
    }

    private void buscar() {
        try {
            String placa = txtPlaca.getText().trim();
            if (placa.isEmpty()) throw new IllegalArgumentException("La placa es obligatoria.");

            Automovil a = controller.buscar(placa).orElse(null);
            if (a == null) {
                txtResultado.setText("No encontrado: " + placa);
                return;
            }

            txtResultado.setText(
                    "Placa: " + a.getMatricula() + "\n" +
                    "Marca: " + a.getMarca() + "\n" +
                    "Modelo: " + a.getModelo() + "\n" +
                    "Tipo: " + a.getTipo() + "\n" +
                    "Autonomía (km): " + String.format("%.2f", a.calcularAutonomiaKm())
            );

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "❌ " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
