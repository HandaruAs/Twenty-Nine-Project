/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
import gui.FormHistory;
import gui_user.FormHistory_user;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author handa
 */
public class histori extends koneksi {
    public DefaultTableModel model = new DefaultTableModel();
    private FormHistory_user formHistory;

   
    public histori() {
        super.setKoneksi();
    }

    
    public void setFormHistory(FormHistory_user formHistory) {
        this.formHistory = formHistory;
    }

    
    public void tampilTb() {
        try {
            String sql = "SELECT no_faktur, kasir, id_pelanggan, nama_pelanggan, total, " +
                         "DATE_FORMAT(tanggal, '%d-%m-%Y') as tgl FROM penjualan";
            rs = st.executeQuery(sql);

            String[] kolom = {"Tanggal", "No Faktur", "Kasir", "ID Pelanggan", "Nama Pelanggan", "Total"};
            model.setColumnIdentifiers(kolom);
            model.setRowCount(0);

            while (rs.next()) {
                Object[] data = {
                    rs.getString("tgl"),
                    rs.getString("no_faktur"),
                    rs.getString("kasir"),
                    rs.getString("id_pelanggan"),
                    rs.getString("nama_pelanggan"),
                    rs.getString("total")
                };
                model.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(histori.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void tampilTbFilter(Date tanggalAwal, Date tanggalAkhir) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String tglAwalStr = sdf.format(tanggalAwal);
            String tglAkhirStr = sdf.format(tanggalAkhir);

            String sql = "SELECT no_faktur, kasir, id_pelanggan, nama_pelanggan, total, " +
                         "DATE_FORMAT(tanggal, '%d-%m-%Y') as tgl FROM penjualan " +
                         "WHERE tanggal BETWEEN ? AND ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, tglAwalStr);
            pst.setString(2, tglAkhirStr);

            rs = pst.executeQuery();

            String[] kolom = {"Tanggal", "No Faktur", "Kasir", "ID Pelanggan", "Nama Pelanggan", "Total"};
            model.setColumnIdentifiers(kolom);
            model.setRowCount(0);

            while (rs.next()) {
                Object[] data = {
                    rs.getString("tgl"),
                    rs.getString("no_faktur"),
                    rs.getString("kasir"),
                    rs.getString("id_pelanggan"),
                    rs.getString("nama_pelanggan"),
                    rs.getString("total")
                };
                model.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(histori.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    public void tglAwal() {
        try {
            String sql = "SELECT tanggal FROM penjualan ORDER BY tanggal ASC LIMIT 1";
            rs = st.executeQuery(sql);
            if (rs.next() && formHistory != null) {
                formHistory.setTglAwal(rs.getString("tanggal"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(histori.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    public void tglAkhir() {
        try {
            String sql = "SELECT tanggal FROM penjualan ORDER BY tanggal DESC LIMIT 1";
            rs = st.executeQuery(sql);
            if (rs.next() && formHistory != null) {
                formHistory.setTglAkhir(rs.getString("tanggal"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(histori.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

    


    
