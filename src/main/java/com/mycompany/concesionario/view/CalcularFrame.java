package com.mycompany.concesionario.view;

import com.mycompany.concesionario.controller.AutomovilController;

import javax.swing.*;
import java.awt.*;

public class CalcularFrame extends JFrame {

    private final AutomovilController controller;

    private final JTextField txtPlaca = new JTextField(12);
    private final JButton btnCalcular = new JButton("Calcular autonomía");
    private final JButton btnModoEco = new JButton("Aplicar modo ECO (solo eléctrico)");
    private final JTextArea txtSalida = new JTextArea(8, 40);

    public CalcularFrame(AutomovilController controller) {
        this.controller = controller;

        setTitle("Calcular Autonomía (Polimorfismo)");
        setSize(650, 320);
        setLocationRelativeTo(null);

        txtSalida.setEditable(false);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        top.add(new JLabel("Placa:"));
        top.add(txtPlaca);
        top.add(btnCalcular);
        top.add(btnModoEco);

        setLayout(new BorderLayout(8, 8));
        add(top, BorderLayout.NORTH);
        add(new JScrollPane(txtSalida), BorderLayout.CENTER);

        btnCalcular.addActionListener(e -> calcular());
        btnModoEco.addActionListener(e -> aplicarEco());
    }

    private void calcular() {
        try {
            String placa = txtPlaca.getText().trim();
            double autonomia = controller.calcularAutonomia(placa);
            txtSalida.setText("Placa: " + placa + "\nAutonomía (km): " + String.format("%.2f", autonomia));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "❌ Error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void aplicarEco() {
        try {
            String placa = txtPlaca.getText().trim();
            controller.aplicarModoEcoSiElectrico(placa);
            JOptionPane.showMessageDialog(this, "✅ Modo ECO aplicado (si era eléctrico). Ahora recalcula.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "❌ " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
