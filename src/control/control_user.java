/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;

public class control_user extends koneksi {
    public control_user() {
        super.setKoneksi();
    }

    public DefaultTableModel model = new DefaultTableModel();

    // ✅ SIMPAN DATA USER
    public void simpan(String rfid_tag, String role, String user, String pass, String nama, String nohp) throws SQLException {
        String sql = "INSERT INTO user (rfid_tag, Role, username, password, nama, nohp) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, rfid_tag);
        ps.setString(2, role);
        ps.setString(3, user);
        ps.setString(4, pass);
        ps.setString(5, nama);
        ps.setString(6, nohp);
        ps.executeUpdate();
    }

    // ✅ EDIT DATA USER
    public void edit(String rfid_tag, String user, String pass, String nama, String nohp, String role) throws SQLException {
        String sql = "UPDATE user SET username = ?, password = ?, nama = ?, nohp = ?, Role = ? WHERE rfid_tag = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, user);
        ps.setString(2, pass);
        ps.setString(3, nama);
        ps.setString(4, nohp);
        ps.setString(5, role);
        ps.setString(6, rfid_tag);
        ps.executeUpdate();
    }

    // ✅ HAPUS USER
    public void hapus(String rfid_tag) throws SQLException {
        String sql = "DELETE FROM user WHERE rfid_tag = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, rfid_tag);
        ps.executeUpdate();
    }

    // ✅ TAMPILKAN DATA KE TABEL
    public void tampil() {
        String sql = "SELECT * FROM user ORDER BY rfid_tag";
        String[] kolom = {"RFID Tag", "Username", "Password", "Nama", "No Hp", "Role"};

        try {
            rs = st.executeQuery(sql);
            model.setColumnIdentifiers(kolom);
            model.setRowCount(0); 

            while (rs.next()) {
                Object[] data = new Object[6];
                data[0] = rs.getString("rfid_tag");
                data[1] = rs.getString("username");
                data[2] = rs.getString("password");
                data[3] = rs.getString("nama");
                data[4] = rs.getString("nohp");
                data[5] = rs.getString("Role");

                model.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(control_user.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public boolean cekDuplikatRfid(String rfid_tag) {
        try {
            String sql = "SELECT rfid_tag FROM user WHERE rfid_tag = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, rfid_tag);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(control_user.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}

