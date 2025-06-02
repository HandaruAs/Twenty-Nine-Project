/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
import custom.JTextfieldRounded;
import gui.FormTransaksi;
import static gui.FormTransaksi.txNoFaktur;
import gui.Transaksi;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author handa
 */
public class control_transaksi extends koneksi {
    
    public control_transaksi() {
        super.setKoneksi();
         String[] kolom = {"Kode Barang", "Nama Barang", "Ukuran", "Harga", "Qty", "Total"};
    model.setColumnIdentifiers(kolom);
    }

    public DefaultTableModel model = new DefaultTableModel();

   
   public void tampilPengeluaran(String kodebarang, String namaBarang, String ukuran, int harga, int qty, int total) {
    if (!(kodebarang.equals(""))) {
        System.out.println("DEBUG tampilPengeluaran -> harga: " + harga + ", qty: " + qty + ", total: " + total);
        Object[] data = new Object[6];
        data[0] = kodebarang;
        data[1] = namaBarang;
        data[2] = ukuran;
        data[3] = harga;
        data[4] = qty;
        data[5] = total;
        model.addRow(data);
    }
}


    
    public void simpanPenjualan(String no_faktur, String kasir, String id_pelanggan, String namaPelanggan, int diskon, int total, int bayar, int kembali, String tgl) throws SQLException {
        String sql = "INSERT INTO penjualan (no_faktur, kasir, id_pelanggan, nama_pelanggan, diskon, total, bayar, kembali, tanggal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql); 
        pst.setString(1, no_faktur);
        pst.setString(2, kasir);
        pst.setString(3, id_pelanggan);
        pst.setString(4, namaPelanggan);
        pst.setInt(5, diskon);
        pst.setInt(6, total);
        pst.setInt(7, bayar);
        pst.setInt(8, kembali);
        pst.setString(9, tgl);
        pst.executeUpdate();
    }

    
    public void insertBarang(String nofak, String kodeBarang, String namaBarang, String ukuran, int harga, int qty, int total) throws SQLException {
        String sql = "INSERT INTO barangPenjualan (no_faktur, kode_barang, nama_barang, ukuran, harga, qty, total1) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, nofak);
        pst.setString(2, kodeBarang);
        pst.setString(3, namaBarang);
        pst.setString(4, ukuran);
        pst.setInt(5, harga);
        pst.setInt(6, qty);
        pst.setInt(7, total);
        pst.executeUpdate();
    }

    public void kurangiStok(String kodeBarang, int qty) throws SQLException {
    System.out.println("DEBUG kurangiStok -> kode: " + kodeBarang + ", qty: " + qty);

    String sqlSelect = "SELECT stok FROM masterBarang WHERE kode_barang = ?";
    PreparedStatement pstSelect = con.prepareStatement(sqlSelect);
    pstSelect.setString(1, kodeBarang);
    ResultSet rs = pstSelect.executeQuery();

    if (rs.next()) {
        int stokSekarang = rs.getInt("stok");
        int sisa = stokSekarang - qty;

        System.out.println("Stok sekarang: " + stokSekarang + ", dikurangi: " + qty + ", sisa: " + sisa);

        if (sisa < 0) {
            JOptionPane.showMessageDialog(null, "Stok tidak mencukupi!");
            return;
        }

        String sqlUpdate = "UPDATE masterBarang SET stok = ? WHERE kode_barang = ?";
        PreparedStatement pstUpdate = con.prepareStatement(sqlUpdate);
        pstUpdate.setInt(1, sisa);
        pstUpdate.setString(2, kodeBarang);
        pstUpdate.executeUpdate();
    } else {
        JOptionPane.showMessageDialog(null, "Kode barang tidak ditemukan!");
    }
}

 


    public void noFak() {
        try {
            String sql = "SELECT * FROM penjualan ORDER BY no_faktur DESC";
            rs = st.executeQuery(sql);
            if (rs.next()) {
                String nofak = rs.getString("no_faktur").substring(1);
                String AN = "" + (Integer.parseInt(nofak) + 1);
                String nol = "";

                if (AN.length() == 1) {
                    nol = "000";
                } else if (AN.length() == 2) {
                    nol = "00";
                } else if (AN.length() == 3) {
                    nol = "0";
                } else if (AN.length() == 4) {
                    nol = "";
                }
                txNoFaktur.setText("F" + nol + AN);
            } else {
                txNoFaktur.setText("F0001");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateStok(int sisa, String kode_barang) throws SQLException {
    String sql = "UPDATE masterBarang SET stok = ? WHERE kode_barang = ?";
    PreparedStatement pst = con.prepareStatement(sql);
    pst.setInt(1, sisa);
    pst.setString(2, kode_barang);
    pst.executeUpdate();
    pst.close();
}

}


