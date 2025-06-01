/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author handa
 */
public class koneksi {
    
    Connection con;
    Statement st;
    ResultSet rs;
    
    public Connection getKoneksi(){
        return con;
    }
    
    public void setKoneksi(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistemkasir","root","");
             st = con.createStatement();
             System.out.println("KONEKSI BERHASIL");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    public static void main(String[] args) {
        koneksi k = new koneksi();
        k.setKoneksi();
    }
}
