package Modelo;
import Controlador.userClass;
public class crudClass {

    public boolean addUser(userClass user) {
        connectionDB connect = new connectionDB();
        connect.connect();
        boolean resultado = false;

        try {
            resultado = connect.executeSQL("insert into users values ('"
                    + user.getRut() + "'" + ",'"
                    + user.getName() + "','"
                    + user.getLastName() + "','"
                    + user.getPassword() + "','"
                    + user.getAccess() + "')");
            System.out.println("insert into users values (null,'"
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

    public boolean actualizarUsuario(userClass usuario) {
        connectionDB connect = new connectionDB();
        connect.connect();
        boolean result = false;

        try {
            result = connect.executeSQL("update usuarios set nombres ="
                    + "'" + usuario.getName() + "', "
                    + "apellidos = '" + usuario.getLastName()
                    + "', contrasena = '" + usuario.getPassword()
                    + "', acceso = '" + usuario.getAccess()
                    + "' where rut = "
                    + "'" + usuario.getRut() + "'");
            result = true;
        } catch (Exception e) {
            System.out.println("Error en la actualización " + e);
            result = false;
        }
        connect.disconnect();
        return result;
    }

    public boolean deleteUsuario(String rut) {
        connectionDB connect = new connectionDB();
        connect.connect();
        boolean result = false;

        try {
            result = connect.executeSQL("delete from usuarios where rut = '" + rut + "'");
        } catch (Exception e) {
            System.out.println("Error en la eliminación " + e);
        }
        connect.disconnect();
        return result;
    }
}
