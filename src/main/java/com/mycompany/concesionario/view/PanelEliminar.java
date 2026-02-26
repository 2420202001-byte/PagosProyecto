package com.mycompany.concesionario.view;

import com.mycompany.concesionario.controller.AutomovilController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PanelEliminar extends JPanel {

    private final AutomovilController controller;
    private final JTextField txtPlaca = new JTextField(14);

    public PanelEliminar(AutomovilController controller) {
        this.controller = controller;

        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(14, 14, 14, 14));

        JLabel title = new JLabel("Eliminar automóvil por placa");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 16f));
        add(title, BorderLayout.NORTH);

        JPanel center = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        center.add(new JLabel("Placa:"));
        center.add(txtPlaca);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> eliminar());
        center.add(btnEliminar);

        add(center, BorderLayout.CENTER);
    }

    private void eliminar() {
        try {
            String placa = txtPlaca.getText().trim();
            if (placa.isEmpty()) throw new IllegalArgumentException("La placa es obligatoria.");

            int op = JOptionPane.showConfirmDialog(
                    this,
                    "¿Eliminar el automóvil con placa " + placa + "?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION
            );
            if (op != JOptionPane.YES_OPTION) return;

            boolean ok = controller.eliminar(placa);
            if (ok) JOptionPane.showMessageDialog(this, "✅ Eliminado: " + placa);
            else JOptionPane.showMessageDialog(this, "No existía: " + placa);

            txtPlaca.setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "❌ " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
