/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author handa
 */
public class MasterBarang extends koneksi {

    public MasterBarang() {
        super.setKoneksi();
    }

    public DefaultTableModel modelBarang = new DefaultTableModel();

    // Fungsi simpan termasuk ukuran
    public void simpan(String kode, String nama, int stok, int harga, String status, int ukuran) throws SQLException {
        String sql = "INSERT INTO masterbarang VALUES('" + kode + "','" + nama + "','" + stok + "','" + harga + "','" + status + "','" + ukuran + "')";
        st.executeUpdate(sql);
    }

    // Fungsi edit termasuk ukuran
    public void edit(String kode, String nama, int stok, int harga, String status, int ukuran) throws SQLException {
        String sql = "UPDATE masterbarang SET nama_barang = '" + nama + "', stok = '" + stok + "', harga = '" + harga + "', status = '" + status + "', ukuran = '" + ukuran + "' WHERE kode_barang = '" + kode + "'";
        st.executeUpdate(sql);
    }

    public void hapus(String kode) throws SQLException {
        String sql = "DELETE FROM masterbarang WHERE kode_barang = '" + kode + "'";
        st.executeUpdate(sql);
    }

    // Fungsi tampil termasuk ukuran
    public void tampil() {
        try {
            String sqli = "SELECT * FROM masterbarang";
            String[] kolom = {"Kode Barang", "Nama Barang", "Stok", "Harga", "Status", "Ukuran"};
            modelBarang.setColumnIdentifiers(kolom);
            rs = st.executeQuery(sqli);
            while (rs.next()) {
                Object[] data = new Object[6];
                data[0] = rs.getString("kode_barang");
                data[1] = rs.getString("nama_barang");
                data[2] = rs.getInt("stok");
                data[3] = rs.getInt("harga");
                data[4] = rs.getString("status");
                data[5] = rs.getInt("ukuran");
                modelBarang.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MasterBarang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


