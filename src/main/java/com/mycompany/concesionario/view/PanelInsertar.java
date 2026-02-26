package com.mycompany.concesionario.view;

import com.mycompany.concesionario.controller.AutomovilController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PanelInsertar extends JPanel {

    private final AutomovilController controller;

    private final JRadioButton rbGasolina = new JRadioButton("Gasolina", true);
    private final JRadioButton rbElectrico = new JRadioButton("Eléctrico");

    private final JTextField txtPlaca = new JTextField(12);
    private final JTextField txtMarca = new JTextField(12);
    private final JTextField txtModelo = new JTextField(12);

    // Gasolina
    private final JTextField txtCapacidadLitros = new JTextField(12);
    private final JTextField txtConsumoKmPorLitro = new JTextField(12);

    // Eléctrico
    private final JTextField txtBateriaKwh = new JTextField(12);
    private final JTextField txtConsumoKwhPorKm = new JTextField(12);

    // Card para campos específicos
    private final JPanel specificCards = new JPanel(new CardLayout());
    private static final String CARD_GAS = "GAS";
    private static final String CARD_ELEC = "ELEC";

    public PanelInsertar(AutomovilController controller) {
        this.controller = controller;

        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(14, 14, 14, 14));

        JLabel title = new JLabel("Insertar automóvil");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 16f));
        add(title, BorderLayout.NORTH);

        add(buildForm(), BorderLayout.CENTER);
        add(buildButtons(), BorderLayout.SOUTH);

        rbGasolina.addActionListener(e -> showSpecificCard(CARD_GAS));
        rbElectrico.addActionListener(e -> showSpecificCard(CARD_ELEC));
        showSpecificCard(CARD_GAS);
    }

    private JPanel buildForm() {
        JPanel form = new JPanel(new BorderLayout(10, 10));

        // Tipo (radio)
        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        typePanel.add(new JLabel("Tipo:"));
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbGasolina);
        bg.add(rbElectrico);
        typePanel.add(rbGasolina);
        typePanel.add(rbElectrico);

        form.add(typePanel, BorderLayout.NORTH);

        // Comunes
        JPanel common = new JPanel(new GridLayout(0, 2, 10, 10));
        common.add(new JLabel("Placa (única):"));
        common.add(txtPlaca);
        common.add(new JLabel("Marca:"));
        common.add(txtMarca);
        common.add(new JLabel("Modelo:"));
        common.add(txtModelo);

        // Específicos
        specificCards.add(buildGasPanel(), CARD_GAS);
        specificCards.add(buildElectricPanel(), CARD_ELEC);

        JPanel center = new JPanel(new BorderLayout(10, 10));
        center.add(common, BorderLayout.NORTH);
        center.add(specificCards, BorderLayout.CENTER);

        form.add(center, BorderLayout.CENTER);
        return form;
    }

    private JPanel buildGasPanel() {
        JPanel p = new JPanel(new GridLayout(0, 2, 10, 10));
        p.setBorder(BorderFactory.createTitledBorder("Datos gasolina"));
        p.add(new JLabel("Capacidad tanque (L):"));
        p.add(txtCapacidadLitros);
        p.add(new JLabel("Consumo (km/L):"));
        p.add(txtConsumoKmPorLitro);
        return p;
    }

    private JPanel buildElectricPanel() {
        JPanel p = new JPanel(new GridLayout(0, 2, 10, 10));
        p.setBorder(BorderFactory.createTitledBorder("Datos eléctrico"));
        p.add(new JLabel("Batería (kWh):"));
        p.add(txtBateriaKwh);
        p.add(new JLabel("Consumo (kWh/km):"));
        p.add(txtConsumoKwhPorKm);
        return p;
    }

    private JPanel buildButtons() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));

        JButton btnLimpiar = new JButton("Limpiar");
        JButton btnInsertar = new JButton("Insertar");

        btnLimpiar.addActionListener(e -> limpiar());
        btnInsertar.addActionListener(e -> insertar());

        p.add(btnLimpiar);
        p.add(btnInsertar);
        return p;
    }

    private void showSpecificCard(String name) {
        CardLayout cl = (CardLayout) specificCards.getLayout();
        cl.show(specificCards, name);
    }

    private void insertar() {
        try {
            String placa = txtPlaca.getText().trim();
            String marca = txtMarca.getText().trim();
            String modelo = txtModelo.getText().trim();

            if (placa.isEmpty() || marca.isEmpty() || modelo.isEmpty()) {
                throw new IllegalArgumentException("Placa, marca y modelo son obligatorios.");
            }

            if (rbGasolina.isSelected()) {
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
            JOptionPane.showMessageDialog(this, "❌ " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        rbGasolina.setSelected(true);
        showSpecificCard(CARD_GAS);
    }
}
