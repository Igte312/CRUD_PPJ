package Modelo;
//import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;


public class ConnectionDB {
    String bd = "crud_ppj";
    String url = "jdbc:mysql://localhost:3306/";
    String user = "root";
    String pass = "";
    String driver = "com.mysql.cj.jdbc.Driver"; //Versión 8 (version 5 va sin cj)
    Connection cx;

    public ConnectionDB() {
    }

    public Connection connect() {
        try {
            Class.forName(driver);
            cx = DriverManager.getConnection(url + bd, user, pass);
            System.out.println("CONEXIÓN CON LA BD " + bd);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("ERROR EN LA CONEXION A LA BD " + ex);
        }
        return cx;
    }

    public void disconnect() {
        try {
            cx.close();
        } catch (SQLException ex) {
            System.out.println("No se pudo desconectar ");
        }
    }
    public static void main(String[] args) {
        ConnectionDB connect = new ConnectionDB();
        connect.connect();
    }
    public boolean executeSQL (String QuerySQL){
        try {
            PreparedStatement pstm = cx.prepareStatement(QuerySQL);
            pstm.execute();
            return true;
        } catch (Exception e){
            System.out.println("Error: "+e);
            return false;
        }
    }

    public ResultSet registros (String QuerySQL){
        try{
            PreparedStatement pstm = cx.prepareStatement(QuerySQL);
            ResultSet response = pstm.executeQuery();
            return response;
        }catch (Exception e){
            System.out.println("Error: "+e);
            return null;
        }
    }
}


