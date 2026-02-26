package com.mycompany.concesionario.view;

import com.mycompany.concesionario.controller.AutomovilController;
import com.mycompany.concesionario.model.TipoAutomovil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {

    private final AutomovilController controller;
    private TipoAutomovil tipoActual = TipoAutomovil.GASOLINA;

    private final JLabel lblTipo = new JLabel();

    public MainFrame(AutomovilController controller) {
        this.controller = controller;

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900, 520);
        setLocationRelativeTo(null);

        setJMenuBar(buildMenuBar());
        setContentPane(buildHomePanel());

        actualizarTituloYEstado();
    }

    private void actualizarTituloYEstado() {
        setTitle("Concesionario — " + (tipoActual == TipoAutomovil.GASOLINA ? "Gasolina" : "Eléctrico"));
        lblTipo.setText("Tipo actual: " + (tipoActual == TipoAutomovil.GASOLINA ? "Gasolina" : "Eléctrico"));
    }

    private JMenuBar buildMenuBar() {
        JMenuBar bar = new JMenuBar();

        // Menú SOLO para seleccionar tipo (una sola fuente de verdad)
        JMenu mTipo = new JMenu("Tipo");
        mTipo.setMnemonic(KeyEvent.VK_T);

        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem rbGas = new JRadioButtonMenuItem("Gasolina", true);
        JRadioButtonMenuItem rbElec = new JRadioButtonMenuItem("Eléctrico");

        rbGas.addActionListener(e -> { tipoActual = TipoAutomovil.GASOLINA; actualizarTituloYEstado(); });
        rbElec.addActionListener(e -> { tipoActual = TipoAutomovil.ELECTRICO; actualizarTituloYEstado(); });

        group.add(rbGas);
        group.add(rbElec);
        mTipo.add(rbGas);
        mTipo.add(rbElec);

        // Menú CRUD (genérico, usa tipoActual)
        JMenu mCrud = new JMenu("CRUD");
        mCrud.setMnemonic(KeyEvent.VK_C);

        mCrud.add(menuItem("Insertar", KeyEvent.VK_I,
                KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK),
                e -> abrirInsertar()));

        mCrud.add(menuItem("Buscar (placa)", KeyEvent.VK_B,
                KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK),
                e -> abrirBuscar()));

        mCrud.add(menuItem("Eliminar (placa)", KeyEvent.VK_E,
                KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK),
                e -> abrirEliminar()));

        mCrud.add(menuItem("Listar (JTable)", KeyEvent.VK_L,
                KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK),
                e -> abrirListar()));

        mCrud.addSeparator();

        mCrud.add(menuItem("Calcular autonomía", KeyEvent.VK_A,
                KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK),
                e -> abrirCalcular()));

        bar.add(mTipo);
        bar.add(mCrud);

        // derecha: Ayuda / Exit
        bar.add(Box.createHorizontalGlue());

        JMenu mAyuda = new JMenu("Ayuda");
        JMenuItem miAbout = new JMenuItem("Acerca de...");
        miAbout.addActionListener(e -> showAbout());
        mAyuda.add(miAbout);

        JMenu mExit = new JMenu("Exit");
        JMenuItem miSalir = new JMenuItem("Salir");
        miSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        miSalir.addActionListener(e -> salir());
        mExit.add(miSalir);

        bar.add(mAyuda);
        bar.add(mExit);

        return bar;
    }

    private JMenuItem menuItem(String text, int mnemonic, KeyStroke accelerator, ActionListener al) {
        JMenuItem it = new JMenuItem(text);
        it.setMnemonic(mnemonic);
        if (accelerator != null) it.setAccelerator(accelerator);
        it.addActionListener(al);
        return it;
    }

    private JPanel buildHomePanel() {
        JPanel root = new JPanel(new BorderLayout(14, 14));
        root.setBorder(new EmptyBorder(18, 18, 18, 18));

        JLabel title = new JLabel("Panel principal");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 22f));

        lblTipo.setForeground(new Color(120, 120, 120));

        JPanel header = new JPanel(new GridLayout(0, 1));
        header.add(title);
        header.add(lblTipo);

        JPanel quick = new JPanel(new GridLayout(2, 3, 10, 10));
        quick.add(btn("Insertar", e -> abrirInsertar()));
        quick.add(btn("Listar (JTable)", e -> abrirListar()));
        quick.add(btn("Buscar por placa", e -> abrirBuscar()));
        quick.add(btn("Eliminar por placa", e -> abrirEliminar()));
        quick.add(btn("Calcular autonomía", e -> abrirCalcular()));
        quick.add(btn("Acerca de...", e -> showAbout()));

        JLabel status = new JLabel("Atajos: Ctrl+I Insertar | Ctrl+L Listar | Ctrl+Q Salir");
        status.setForeground(new Color(120, 120, 120));

        root.add(header, BorderLayout.NORTH);
        root.add(quick, BorderLayout.CENTER);
        root.add(status, BorderLayout.SOUTH);
        return root;
    }

    private JButton btn(String text, ActionListener al) {
        JButton b = new JButton(text);
        b.setFocusPainted(false);
        b.addActionListener(al);
        return b;
    }

    // ===== Abrir ventanas =====
    private void abrirInsertar() {
        // Aquí ya NO se muestra selector de tipo en el InsertarFrame.
        new InsertarFrame(controller, tipoActual).setVisible(true);
    }

    private void abrirBuscar() {
        new BuscarFrame(controller).setVisible(true);
    }

    private void abrirEliminar() {
        new EliminarFrame(controller).setVisible(true);
    }

    private void abrirListar() {
        // Si quieres filtrar la tabla por tipo, crea ListarFrame(controller, tipoActual)
        new ListarFrame(controller).setVisible(true);
    }

    private void abrirCalcular() {
        new CalcularFrame(controller).setVisible(true);
    }

    private void showAbout() {
        JOptionPane.showMessageDialog(
                this,
                "Concesionario - Prototipo v1.0\n\n" +
                        "Integrantes:\n" +
                        "Yaser Rondón - 22202111047\n" +
                        "Ismael Cardozo - 2220191044\n" +
                        "Juan Mancipe - 2220222034\n",
                "Acerca de...",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void salir() {
        int op = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas salir?", "Salir",
                JOptionPane.YES_NO_OPTION);
        if (op == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }
}
