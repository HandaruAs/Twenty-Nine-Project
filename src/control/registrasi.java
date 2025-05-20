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

public class registrasi extends koneksi {
    
    public registrasi() {
        super.setKoneksi(); 
    }

    // Fungsi untuk menghasilkan hash dari password menggunakan SHA-256
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    // Fungsi untuk registrasi pengguna dengan password yang di-hash
    public boolean registerUser(String username, String password, String nama, String nohp) {
        try {
            // Hash password sebelum disimpan
            String hashedPassword = hashPassword(password);

            String sql = "INSERT INTO user (username, password, nama, nohp) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, hashedPassword); // Gunakan hashed password
            ps.setString(3, nama);
            ps.setString(4, nohp);

            int result = ps.executeUpdate(); 
            return result > 0; 
        } catch (SQLException | NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Fungsi untuk mengecek apakah username sudah ada dalam database
    public boolean checkUsernameExists(String username) {
        try {
            String sql = "SELECT * FROM user WHERE username = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Jika ada baris yang ditemukan, berarti username sudah ada
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}