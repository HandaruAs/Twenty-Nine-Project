/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
import gui.FormUtama;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author handa
 */
public class utama extends koneksi{
    public utama(){
        super.setKoneksi();
    }
   
    public void tampilCountTrx(){
        try {
            String sql = "SELECT COUNT(no_faktur) from penjualan";
            rs = st.executeQuery(sql);
            while(rs.next()){
                FormUtama.totalTrx2.setText(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(utama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void tampilCountBrg(){
        try {
            String sql = "SELECT COUNT(kode_barang) FROM masterbarang";
            rs = st.executeQuery(sql);
            while(rs.next()){
                FormUtama.totalBrg.setText(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(utama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
       public void tampilCountPlg(){
        try {
            String sql = "SELECT COUNT(id_pelanggan) FROM pelanggan";
            rs = st.executeQuery(sql);
            while(rs.next()){
               FormUtama.totalPlg.setText(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(utama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}