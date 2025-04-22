/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author handa
 */
public class control_resetPass extends koneksi {
    public control_resetPass(){
    super.setKoneksi();
    }
   
    public boolean resetPassword(String username, String newPassword) {
        try {
            super.setKoneksi(); 
            String sql = "UPDATE user SET password = ? WHERE username = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, newPassword); 
            pst.setString(2, username);

            int rowsAffected = pst.executeUpdate();
            pst.close(); 
            
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Password berhasil direset!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Username tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

}