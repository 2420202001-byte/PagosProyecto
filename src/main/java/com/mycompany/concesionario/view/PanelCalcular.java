package com.mycompany.concesionario.view;

import com.mycompany.concesionario.controller.AutomovilController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PanelCalcular extends JPanel {

    private final AutomovilController controller;

    private final JTextField txtPlaca = new JTextField(14);
    private final JTextArea txtSalida = new JTextArea(10, 50);

    public PanelCalcular(AutomovilController controller) {
        this.controller = controller;

        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(14, 14, 14, 14));

        JLabel title = new JLabel("Calcular autonomía (polimorfismo)");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 16f));
        add(title, BorderLayout.NORTH);

        txtSalida.setEditable(false);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        top.add(new JLabel("Placa:"));
        top.add(txtPlaca);

        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.addActionListener(e -> calcular());
        top.add(btnCalcular);

        JButton btnEco = new JButton("Aplicar modo ECO (solo eléctrico)");
        btnEco.addActionListener(e -> aplicarEco());
        top.add(btnEco);

        add(top, BorderLayout.CENTER);
        add(new JScrollPane(txtSalida), BorderLayout.SOUTH);
    }

    private void calcular() {
        try {
            String placa = txtPlaca.getText().trim();
            if (placa.isEmpty()) throw new IllegalArgumentException("La placa es obligatoria.");

            double autonomia = controller.calcularAutonomia(placa);
            txtSalida.setText(
                    "Placa: " + placa + "\n" +
                    "Autonomía (km): " + String.format("%.2f", autonomia) + "\n\n" +
                    "Tip: si es eléctrico puedes aplicar ECO y recalcular."
            );

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "❌ " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void aplicarEco() {
        try {
            String placa = txtPlaca.getText().trim();
            if (placa.isEmpty()) throw new IllegalArgumentException("La placa es obligatoria.");

            controller.aplicarModoEcoSiElectrico(placa);
            JOptionPane.showMessageDialog(this, "✅ ECO aplicado. Ahora vuelve a calcular.");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "❌ " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
