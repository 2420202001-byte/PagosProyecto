package com.mycompany.concesionario.view;

import com.mycompany.concesionario.controller.AutomovilController;
import com.mycompany.concesionario.model.TipoAutomovil;

import javax.swing.*;
import java.awt.*;

public class InsertarFrame extends JFrame {

    private final AutomovilController controller;
    private final TipoAutomovil tipo;

    // Comunes
    private final JTextField txtPlaca = new JTextField(12);
    private final JTextField txtMarca = new JTextField(12);
    private final JTextField txtModelo = new JTextField(12);

    // Gasolina
    private final JTextField txtCapacidadLitros = new JTextField(12);
    private final JTextField txtConsumoKmPorLitro = new JTextField(12);

    // Eléctrico
    private final JTextField txtBateriaKwh = new JTextField(12);
    private final JTextField txtConsumoKwhPorKm = new JTextField(12);

    private final JButton btnInsertar = new JButton("Insertar");
    private final JButton btnLimpiar = new JButton("Limpiar");

    // (Opcional) constructor viejo para que no se dañen llamadas anteriores
    public InsertarFrame(AutomovilController controller) {
        this(controller, TipoAutomovil.GASOLINA);
    }

    // Constructor nuevo (el que tu MainFrame está usando)
    public InsertarFrame(AutomovilController controller, TipoAutomovil tipo) {
        this.controller = controller;
        this.tipo = tipo;

        String tituloTipo = (tipo == TipoAutomovil.GASOLINA) ? "Gasolina" : "Eléctrico";
        setTitle("Insertar — " + tituloTipo);
        setSize(520, 280);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(8, 8));
        add(crearFormulario(), BorderLayout.CENTER);
        add(crearBotones(), BorderLayout.SOUTH);

        btnInsertar.addActionListener(e -> insertar());
        btnLimpiar.addActionListener(e -> limpiar());
    }

    private JPanel crearFormulario() {
        JPanel p = new JPanel(new GridLayout(0, 2, 8, 8));

        // Campos comunes
        p.add(new JLabel("Placa (única):"));
        p.add(txtPlaca);

        p.add(new JLabel("Marca:"));
        p.add(txtMarca);

        p.add(new JLabel("Modelo:"));
        p.add(txtModelo);

        // Campos específicos según tipo (ya no hay combo)
        if (tipo == TipoAutomovil.GASOLINA) {
            p.add(new JLabel("Capacidad tanque (L):"));
            p.add(txtCapacidadLitros);

            p.add(new JLabel("Consumo (km/L):"));
            p.add(txtConsumoKmPorLitro);
        } else {
            p.add(new JLabel("Batería (kWh):"));
            p.add(txtBateriaKwh);

            p.add(new JLabel("Consumo (kWh/km):"));
            p.add(txtConsumoKwhPorKm);
        }

        return p;
    }

    private JPanel crearBotones() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        p.add(btnLimpiar);
        p.add(btnInsertar);
        return p;
    }

    private void insertar() {
        try {
            String placa = txtPlaca.getText().trim();
            String marca = txtMarca.getText().trim();
            String modelo = txtModelo.getText().trim();

            if (tipo == TipoAutomovil.GASOLINA) {
                double capacidadL = Double.parseDouble(txtCapacidadLitros.getText().trim());
                double consumoKmL = Double.parseDouble(txtConsumoKmPorLitro.getText().trim());
                controller.insertarGasolina(placa, marca, modelo, capacidadL, consumoKmL);
            } else {
                double bateria = Double.parseDouble(txtBateriaKwh.getText().trim());
                double consumo = Double.parseDouble(txtConsumoKwhPorKm.getText().trim());
                controller.insertarElectrico(placa, marca, modelo, bateria, consumo);
            }

            JOptionPane.showMessageDialog(this, "✅ Insertado correctamente");
            limpiar();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "❌ Error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiar() {
        txtPlaca.setText("");
        txtMarca.setText("");
        txtModelo.setText("");
        txtCapacidadLitros.setText("");
        txtConsumoKmPorLitro.setText("");
        txtBateriaKwh.setText("");
        txtConsumoKwhPorKm.setText("");
    }
}
