package Vista;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Controlador.UserClass;
import Controlador.ValidationClass;
import Modelo.CrudClass;
import Modelo.ConnectionDB;
import java.sql.*;
import java.awt.*;


public class FormularyAdmin extends JFrame{

    CrudClass crudClass = new CrudClass();
    ConnectionDB conect = new ConnectionDB();
    Connection cn = conect.connect();
    ValidationClass validate = new ValidationClass();

    public JPanel panel;
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
    private JLabel label_rut_error;
    private JLabel label_rut_error2;

    public JPanel getPanel() {
        return panel;
    }

    public static void main(String[] args) {
        FormularyAdmin formAdmin = new FormularyAdmin();
        formAdmin.setContentPane(new FormularyAdmin().panel);
        formAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        formAdmin.pack();
        formAdmin.setSize(800, 600);
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
                boolean rut;
                boolean validateNulls;
                boolean validateDuplicates;
                //-----------------------------------------------------
                validateNulls = validate.validateNulls(input_rut.getText(),input_name.getText(),input_lastName.getText(),input_password.getText());
                if (validateNulls){
                    JOptionPane.showMessageDialog(null, "Llene los campos en blanco", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                }
                //-----------------------------------------------------
                rut = validate.validateRut((input_rut.getText()));
                if (!rut){
                    label_rut_error.setForeground(Color.RED);
                    label_rut_error.setText("formato de rut incorrecto");
                }else{
                    label_rut_error.setText(null);
                }
                //-----------------------------------------------------
                validateDuplicates = crudClass.validateDuplicate((input_rut.getText()));
                if (validateDuplicates) {
                    label_rut_error2.setForeground(Color.RED);
                    label_rut_error2.setText("El rut ingresado ya existe");
                }else{
                    label_rut_error2.setText(null);
                }
                //-----------------------------------------------------
                if (!validateNulls  && rut && !validateDuplicates) {
                    UserClass user = new UserClass(input_rut.getText(), input_name.getText(), input_lastName.getText(), input_password.getText(), comb_access.getSelectedItem().toString());
                    boolean result = crudClass.addUser(user);
                    if (result) {
                        JOptionPane.showMessageDialog(null, "Se ha ingresado el registro", "OK", JOptionPane.INFORMATION_MESSAGE);
                        showData();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error. No se pudo ingresar el usuario", "Error", JOptionPane.ERROR_MESSAGE);
                        showData();
                    }
                }
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
                UserClass user = new UserClass(input_rut.getText(),input_name.getText(),input_lastName.getText(),input_password.getText(),comb_access.getSelectedItem().toString());
                boolean result = crudClass.apdateUser(user);
                showData();
            }
        });

        btn_delete.addActionListener(new ActionListener() {//---------------------------- Eliminar
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean result = crudClass.deleteUser(input_rut.getText());
                showData();
            }
        });
    }
}