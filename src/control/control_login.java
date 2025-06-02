/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class control_login extends koneksi {
    
    public control_login() {
        super.setKoneksi();
    }

   
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    
    public String login(String username, String password) throws SQLException, NoSuchAlgorithmException {
        String sql = "SELECT * FROM user WHERE username = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String hashedPasswordFromDb = rs.getString("password");
            String hashedInputPassword = hashPassword(password);

            if (hashedPasswordFromDb.equals(hashedInputPassword)) {
                return rs.getString("role"); 
            }
        }

        return null; 
    }

   
    public ResultSet getUserData(String username) throws SQLException {
        String sql = "SELECT * FROM user WHERE username = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);
        return ps.executeQuery();
    }
}