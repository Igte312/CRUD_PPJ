package Vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Controlador.userClass;
import Modelo.connectionDB;
import Modelo.crudClass;

public class formulario_admin extends JFrame{

    crudClass crudClass = new crudClass();


    private JPanel panel;
    private JLabel label_rut;
    private JTextField input_rut;
    private JLabel label_name;
    private JTextField input_name;
    private JTextField input_lastName;
    private JLabel label_lastName;
    private JPasswordField input_password;
    private JLabel label_password;
    private JComboBox comb_access;
    private JLabel label_access;
    private JLabel label_admin;
    private JButton btn_create;
    private JButton btm_update;
    private JButton btn_delete;
    private JLabel label_rep;
    private JTable table;
    private JButton btn_endSession;



    public static void main(String[] args) {
        formulario_admin formAdmin = new formulario_admin();
        formAdmin.setContentPane(new formulario_admin().panel);
        formAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        formAdmin.pack();
        formAdmin.setVisible(true);
    }

    public formulario_admin(){
        btn_create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userClass user = new userClass(input_rut.getText(),input_name.getName(),input_lastName.getText(),input_password.getText(),comb_access.getSelectedItem().toString());
                boolean result = crudClass.addUser(user);
                System.out.println("prueba git!!!");
            }
        });
    }
}