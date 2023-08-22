package Vista;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Controlador.userClass;
import Modelo.crudClass;
import Modelo.connectionDB;
import java.sql.*;

public class FormularyAdmin extends JFrame{

    crudClass crudClass = new crudClass();
    connectionDB conect = new connectionDB();
    Connection cn = conect.connect();

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
    private JButton btn_update;
    private JButton btn_delete;
    private JTable table;
    private JButton btn_endSession;
    private JButton btn_listar;


    public static void main(String[] args) {
        FormularyAdmin formAdmin = new FormularyAdmin();
        formAdmin.setContentPane(new FormularyAdmin().panel);
        formAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        formAdmin.pack();
        formAdmin.setVisible(true);
    }
    void showData() {//------------------------------------------------------------- Tabla
        //esto llena los titulos de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Rut");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellido");
        modelo.addColumn("Clave");
        modelo.addColumn("Acceso");

        table.setModel(modelo);//el nombre de la tabla Design
        String[] datos = new String[5];

        try { //el try catch lo pide el Statementst = cn.createStatement();
            Statement st = cn.createStatement();//crea la instancia para tirar el Query
            ResultSet rs = st.executeQuery("SELECT * FROM users");//si rs es != a null
//            if (rs != null){
//                JOptionPane.showMessageDialog(null, "ya existe el usuario", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
//            }
            while (rs.next()) {
                //espacio en el array         filas de la tabla
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                modelo.addRow(datos);
            }
            table.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(FormularyAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public FormularyAdmin(){
        btn_create.addActionListener(new ActionListener() {//-------------------------- Crea
            @Override
            public void actionPerformed(ActionEvent e) {
                userClass user = new userClass(input_rut.getText(),input_name.getText(),input_lastName.getText(),input_password.getText(),comb_access.getSelectedItem().toString());
                boolean result = crudClass.addUser(user);
                System.out.println(input_name.getText());
                showData();
                System.out.println("prueba git!!!");
            }
        });

        btn_listar.addActionListener(new ActionListener() {//-------------------------- Listar
            @Override
            public void actionPerformed(ActionEvent e) {
                showData();
            }
        });

        btn_update.addActionListener(new ActionListener() {//-------------------------- Actualizar
            @Override
            public void actionPerformed(ActionEvent e) {
                userClass user = new userClass(input_rut.getText(),input_name.getText(),input_lastName.getText(),input_password.getText(),comb_access.getSelectedItem().toString());
                boolean result = crudClass.apdateUser(user);
                showData();
            }
        });

        btn_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean result = crudClass.deleteUser(input_rut.getText());
                showData();
            }
        });
    }
}