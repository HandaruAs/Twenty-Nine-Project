/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.nio.charset.StandardCharsets;


public class registrasi extends koneksi {

    public registrasi() {
        super.setKoneksi();
    }

    
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    
    public boolean registerUser(String username, String password, String nama, String nohp, String rfid, String role) {
        try {
            
            if (!role.equalsIgnoreCase("admin") && !role.equalsIgnoreCase("user")) {
                System.out.println("Role tidak valid. Harus 'admin' atau 'user'.");
                return false;
            }

           
            String hashedPassword = hashPassword(password);

            String sql = "INSERT INTO user (username, password, nama, nohp, rfid_tag, role) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, hashedPassword);
            ps.setString(3, nama);
            ps.setString(4, nohp);
            ps.setString(5, rfid);
            ps.setString(6, role.toLowerCase()); 

            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException | NoSuchAlgorithmException ex) {
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