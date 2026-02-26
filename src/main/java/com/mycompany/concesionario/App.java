package com.mycompany.concesionario;

import com.mycompany.concesionario.controller.AutomovilController;
import com.mycompany.concesionario.repo.InMemoryAutomovilRepository;
import com.mycompany.concesionario.service.AutomovilService;
import com.mycompany.concesionario.view.MainFrame;

import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            var repo = new InMemoryAutomovilRepository();
            var service = new AutomovilService(repo);
            var controller = new AutomovilController(service);
            new MainFrame(controller).setVisible(true);
        });
    }
}
