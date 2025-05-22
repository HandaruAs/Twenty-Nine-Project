/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author handa
 */
public class registrasi extends koneksi {
    
    public registrasi() {
        super.setKoneksi(); 
    }

   
   public boolean registerUser(String username, String password, String nama, String nohp) {
        try {
            
            String sql = "INSERT INTO user (username, password, nama, nohp) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, nama);
            ps.setString(4, nohp);
            
            
            int result = ps.executeUpdate(); 
            return result > 0; 
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    
    public boolean checkUsernameExists(String username) {
        try {
            String sql = "SELECT * FROM user WHERE username = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next(); 
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}