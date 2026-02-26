package com.mycompany.concesionario.view;

import com.mycompany.concesionario.controller.AutomovilController;

import javax.swing.*;
import java.awt.*;

public class EliminarFrame extends JFrame {

    private final AutomovilController controller;

    private final JTextField txtPlaca = new JTextField(12);
    private final JButton btnEliminar = new JButton("Eliminar");

    public EliminarFrame(AutomovilController controller) {
        this.controller = controller;

        setTitle("Eliminar Automóvil por Placa");
        setSize(420, 140);
        setLocationRelativeTo(null);

        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        p.add(new JLabel("Placa:"));
        p.add(txtPlaca);
        p.add(btnEliminar);

        add(p);

        btnEliminar.addActionListener(e -> eliminar());
    }

    private void eliminar() {
        try {
            String placa = txtPlaca.getText().trim();
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

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "❌ Error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
