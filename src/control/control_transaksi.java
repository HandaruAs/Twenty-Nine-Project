/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
import static gui.FormTransaksi.txNoFaktur;
import gui.Transaksi;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author handa
 */
public class control_transaksi extends koneksi {
    
    public control_transaksi() {
        super.setKoneksi();
    }

    public DefaultTableModel model = new DefaultTableModel();

    // ⬇️ Tambahkan parameter 'ukuran'
    public void tampilPengeluaran(String kodebarang, String namaBarang, String ukuran, int harga, int qty, int total) {
        String[] kolom = {"Kode Barang", "Nama Barang", "Ukuran", "Harga", "Qty", "Total"};
        model.setColumnIdentifiers(kolom);

        if (!(kodebarang.equals(""))) {
            Object[] data = new Object[6]; // Menyesuaikan jumlah kolom
            data[0] = kodebarang;
            data[1] = namaBarang;
            data[2] = ukuran;     // Kolom baru
            data[3] = harga;
            data[4] = qty;
            data[5] = total;
            model.addRow(data);
        }
    }

    public void simpanPenjualan(String no_faktur, String kasir, String id_pelanggan, String namaPelanggan, int diskon, int total, int bayar, int kembali, String tgl) throws SQLException {
        String sql = "INSERT INTO penjualan VALUES('" + no_faktur + "','" + kasir + "','" + id_pelanggan + "','" + namaPelanggan + "','" + diskon + "','" + total + "','" + bayar + "','" + kembali + "','" + tgl + "')";
        st.executeUpdate(sql);
    }

    // ⬇️ Tambahkan parameter 'ukuran'
    public void insertBarang(String nofak, String kodeBarang, String namaBarang, String ukuran, int harga, int qty, int total) throws SQLException {
        String sql = "INSERT INTO barangPenjualan VALUES('" + nofak + "','" + kodeBarang + "','" + namaBarang + "','" + ukuran + "','" + harga + "','" + qty + "','" + total + "')";
        st.executeUpdate(sql);
    }

    public void updateStok(int sisa, String kode_barang) throws SQLException {
        String sql = "UPDATE masterBarang set stok = '" + sisa + "' WHERE kode_barang = '" + kode_barang + "'";
        st.executeUpdate(sql);
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
            Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

