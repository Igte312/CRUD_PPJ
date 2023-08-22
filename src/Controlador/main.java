package Controlador;

import Vista.FormularyAdmin;

import javax.swing.*;

public class main {
    public static void main(String[] args) {
        FormularyAdmin frame = new FormularyAdmin();

        // Configurar y mostrar la ventana
        FormularyAdmin formAdmin = new FormularyAdmin();
        formAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        formAdmin.pack();
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}
