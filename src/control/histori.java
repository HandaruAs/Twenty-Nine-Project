/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
import gui.FormHistory;
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
public class histori extends koneksi{
    public histori(){
        super.setKoneksi();
    }
    public DefaultTableModel model = new DefaultTableModel();
    public void tampilTb(){
        try {
            String sql = "SELECT `no_faktur`,`kasir`,`id_pelanggan`,`nama_pelanggan`,total,DATE_FORMAT(tanggal, '%d-%m-%Y') as tgl FROM penjualan";
            rs = st.executeQuery(sql);
            String kolom[] ={"Tangggal","No Faktur","Kasir","ID Pelanggan","Nama Pelanggan","Total"};
            model.setColumnIdentifiers(kolom);
           
            while(rs.next()){
                Object[] data = new Object[6];
                data[0] = rs.getString("tgl");
                data[1] = rs.getString("no_faktur");
                data[2] = rs.getString("kasir");
                data[3] = rs.getString("id_pelanggan");
                data[4] = rs.getString("nama_pelanggan");
                data[5] = rs.getString("total");
                
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

            String sql = "SELECT `no_faktur`,`kasir`,`id_pelanggan`,`nama_pelanggan`,total,DATE_FORMAT(tanggal, '%d-%m-%Y') as tgl " +
                         "FROM penjualan WHERE tanggal BETWEEN ? AND ?";

            PreparedStatement pst = con.prepareStatement(sql); // pastikan 'conn' adalah Connection Anda
            pst.setString(1, tglAwalStr);
            pst.setString(2, tglAkhirStr);

            rs = pst.executeQuery();

            String kolom[] = {"Tangggal", "No Faktur", "Kasir", "ID Pelanggan", "Nama Pelanggan", "Total"};
            model.setColumnIdentifiers(kolom);
            model.setRowCount(0);

            while (rs.next()) {
                Object[] data = new Object[6];
                data[0] = rs.getString("tgl");
                data[1] = rs.getString("no_faktur");
                data[2] = rs.getString("kasir");
                data[3] = rs.getString("id_pelanggan");
                data[4] = rs.getString("nama_pelanggan");
                data[5] = rs.getString("total");

                model.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(histori.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void tglAwal(){
        try {
            String sql = "SELECT tanggal FROM penjualan ORDER BY tanggal asc LIMIT 1";
            rs = st.executeQuery(sql);
            while(rs.next()){
                  FormHistory.tglAwal.setText(rs.getString(1));
            }   } catch (SQLException ex) {
            Logger.getLogger(histori.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
    public void tglAkhir(){
        try {
            String sql = "SELECT tanggal FROM penjualan ORDER BY tanggal DESC LIMIT 1";
            rs = st.executeQuery(sql);
            while(rs.next()){
                FormHistory.tglAkhir.setText(rs.getString(1));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(histori.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
