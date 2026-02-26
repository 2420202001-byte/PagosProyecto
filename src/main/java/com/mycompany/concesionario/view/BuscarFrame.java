package com.mycompany.concesionario.view;

import com.mycompany.concesionario.controller.AutomovilController;
import com.mycompany.concesionario.model.Automovil;

import javax.swing.*;
import java.awt.*;

public class BuscarFrame extends JFrame {

    private final AutomovilController controller;

    private final JTextField txtPlaca = new JTextField(12);
    private final JTextArea txtResultado = new JTextArea(8, 40);
    private final JButton btnBuscar = new JButton("Buscar");

    public BuscarFrame(AutomovilController controller) {
        this.controller = controller;

        setTitle("Buscar Automóvil por Placa");
        setSize(600, 300);
        setLocationRelativeTo(null);

        txtResultado.setEditable(false);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        top.add(new JLabel("Placa:"));
        top.add(txtPlaca);
        top.add(btnBuscar);

        setLayout(new BorderLayout(8, 8));
        add(top, BorderLayout.NORTH);
        add(new JScrollPane(txtResultado), BorderLayout.CENTER);

        btnBuscar.addActionListener(e -> buscar());
    }

    private void buscar() {
        try {
            String placa = txtPlaca.getText().trim();
            Automovil a = controller.buscar(placa).orElse(null);

            if (a == null) {
                txtResultado.setText("No encontrado: " + placa);
                return;
            }

            // salida simple
            txtResultado.setText(
                    "Placa: " + a.getMatricula() + "\n" +
                    "Marca: " + a.getMarca() + "\n" +
                    "Modelo: " + a.getModelo() + "\n" +
                    "Tipo: " + a.getTipo() + "\n" +
                    "Autonomía (km): " + String.format("%.2f", a.calcularAutonomiaKm())
            );

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "❌ Error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
