package Controlador;

import Vista.formulario_admin;

import javax.swing.*;

public class main {
    public static void main(String[] args) {
        formulario_admin frame = new formulario_admin();

        // Configurar y mostrar la ventana
        formulario_admin formAdmin = new formulario_admin();
        formAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        formAdmin.pack();
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}
