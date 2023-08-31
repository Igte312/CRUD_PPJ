package Controlador;

import Vista.FormularyAdmin;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Configurar y mostrar la ventana
        SwingUtilities.invokeLater(() -> {
            FormularyAdmin form = new FormularyAdmin();
            //JFrame frame = new JFrame("Formulario Administrador");
            form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            form.setContentPane(form.getPanel());
            form.pack();
            form.setSize(800, 600);
            form.setVisible(true);
        });

    }
}
