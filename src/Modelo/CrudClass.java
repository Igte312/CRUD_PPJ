package Modelo;
import Controlador.UserClass;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;

public class CrudClass {

    public boolean addUser(UserClass user) {
        ConnectionDB connect = new ConnectionDB();
        connect.connect();
        boolean resultado = false;

        try {
            resultado = connect.executeSQL("insert into users values ('"
                    + user.getRut() + "'" + ",'"
                    + user.getName() + "','"
                    + user.getLastName() + "','"
                    + user.getPassword() + "','"
                    + user.getAccess() + "')");
            System.out.println("insert into users values ('"
                    + user.getRut() + "'" + ",'"
                    + user.getName() + "','"
                    + user.getLastName() + "','"
                    + user.getPassword() + "','"
                    + user.getAccess() + "')");
        } catch (Exception e) {
            System.out.println("Error en la inserción " + e);
        }
        connect.disconnect();
        return resultado;
    }

    public boolean apdateUser(UserClass user) {
        ConnectionDB connect = new ConnectionDB();
        connect.connect();
        boolean result = false;

        try {
            result = connect.executeSQL("update users set name ="
                    + "'" + user.getName() + "', "
                    + "last_name = '" + user.getLastName()
                    + "', password = '" + user.getPassword()
                    + "', access = '" + user.getAccess()
                    + "' where rut = "
                    + "'" + user.getRut() + "'");
            result = true;
        } catch (Exception e) {
            System.out.println("Error en la actualización " + e);
            result = false;
        }
        connect.disconnect();
        return result;
    }

    public boolean deleteUser(String rut) {
        ConnectionDB connect = new ConnectionDB();
        connect.connect();
        boolean result = false;

        try {
            result = connect.executeSQL("delete from users where rut = '" + rut + "'");
        } catch (Exception e) {
            System.out.println("Error en la eliminación " + e);
        }
        connect.disconnect();
        return result;
    }

    public boolean validateDuplicate(String rut) {
        ConnectionDB connect = new ConnectionDB();
        Connection cn = connect.connect(); // Obtén la conexión de la forma que estás utilizando
        boolean exists = false;

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM users WHERE rut = '" + rut + "'");

            if (rs.next()) {
                exists = true; // Si hay un resultado, el RUT existe en la base de datos
            }

            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(CrudClass.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connect.disconnect(); // Asegúrate de desconectar la conexión en todos los casos
        }

        return exists;
    }
}
