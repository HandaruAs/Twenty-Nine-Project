/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
import control.koneksi;
import gui.FormLaporan;
import gui.FormUtama;
import gui.Transaksi;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author handa
 */
public class laporan extends koneksi{
 Connection con;
 
 
    public laporan(){
       super.setKoneksi();
    }
    
    public void tampailBarangTerjual(){
     try {
         String sql = "SELECT SUM(qty) FROM barangpenjualan";
         rs = st.executeQuery(sql);
         while(rs.next()){
             FormLaporan.brgTerjual.setText(Integer.toString(rs.getInt(1)));
         }
     } catch (SQLException ex) {
         Logger.getLogger(laporan.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
    public void tampilPendapatanHariIni(String tgl){
     try {
       // String sql = "SELECT SUM(IF (tanggal = '"+tgl+"%', total,0)) as totalDay FROM penjualan";
         String sql = "SELECT SUM(total) AS total FROM penjualan WHERE tanggal = '"+tgl+"'";
         System.out.println(tgl);
         rs = st.executeQuery(sql);
         while(rs.next()){
             FormLaporan.pendapatanHariini.setText(Integer.toString(rs.getInt(1)));
         }
     } catch (SQLException ex) {
         Logger.getLogger(laporan.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
    public void tampilTotal(){
     try {
         String sql = "SELECT SUM(total) FROM penjualan";
         rs = st.executeQuery(sql);
         while(rs.next()){
             FormLaporan.txTotal.setText(Integer.toString(rs.getInt(1)));
         }
     } catch (SQLException ex) {
         Logger.getLogger(laporan.class.getName()).log(Level.SEVERE, null, ex);
     }
    }    
    
       public void grafik(){
     try {
         DefaultCategoryDataset obj = new DefaultCategoryDataset();
         String sql = "SELECT COUNT(no_faktur),tanggal FROM penjualan GROUP BY tanggal";
         rs = st.executeQuery(sql);
         while(rs.next()){
             for(int i=0;i<rs.getRow();i++){
                 obj.setValue(rs.getInt(1), "HARI", rs.getString(2));
                 JFreeChart chart = ChartFactory.createLineChart("GRAFIK PENJUALAN", null, null, obj);
                 CategoryPlot  objc = chart.getCategoryPlot();
                 objc.setRangeGridlinePaint(Color.black);
                 objc.setBackgroundPaint(Color.white); 
              
                 
                 ChartPanel panel = new ChartPanel(chart);
                 
             }
         }
     } catch (SQLException ex) {
         Logger.getLogger(laporan.class.getName()).log(Level.SEVERE, null, ex);
     }
        
     
    }
       
       public void grafikDashboard(){
     try {
         DefaultCategoryDataset obj = new DefaultCategoryDataset();
         String sql = "SELECT COUNT(no_faktur),tanggal FROM penjualan GROUP BY tanggal";
         rs = st.executeQuery(sql);
         while(rs.next()){
             for(int i=0;i<rs.getRow();i++){
                 obj.setValue(rs.getInt(1), "HARI", rs.getString(2));
                 JFreeChart chart = ChartFactory.createLineChart("GRAFIK PENJUALAN", null, null, obj);
                 CategoryPlot  objc = chart.getCategoryPlot();
                 objc.setRangeGridlinePaint(Color.black);
                 objc.setBackgroundPaint(Color.white); 
              
                 
                 ChartPanel panel = new ChartPanel(chart);
               
             }
         }
     } catch (SQLException ex) {
         Logger.getLogger(laporan.class.getName()).log(Level.SEVERE, null, ex);
     }
        
     
    }
    
     public void printNota(String nofak) {
    JasperReport jasRep;
    JasperPrint jasPri;
    JasperDesign jasdes;
    Map<String, Object> param = new HashMap<>();

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistemkasir", "root", "");

        InputStream reportStream = getClass().getClassLoader().getResourceAsStream("report2/ASW.jrxml");

        if (reportStream == null) {
            throw new FileNotFoundException("File report2/ASW.jrxml tidak ditemukan.");
        }

        param.put("nofak", nofak);
        jasdes = JRXmlLoader.load(reportStream);
        jasRep = JasperCompileManager.compileReport(jasdes);
        jasPri = JasperFillManager.fillReport(jasRep, param, con);

        // Tampilkan hanya sekali
        JasperViewer viewer = new JasperViewer(jasPri, false);
        viewer.setTitle("NOTA");
        viewer.setVisible(true);

    } catch (FileNotFoundException e) {
        JOptionPane.showMessageDialog(null, "Laporan tidak ditemukan: " + e.getMessage());
    } catch (ClassNotFoundException | SQLException | JRException e) {
        Logger.getLogger(FormUtama.class.getName()).log(Level.SEVERE, null, e);
    }
}

    
      
       public void printLap(String tanggalCetak) {
            JasperReport jasRep;
        JasperPrint jasPri;
        JasperDesign jasdes;
         Map<String, Object> param = new HashMap<String, Object>();
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
         con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistemkasir","root","");
        InputStream reportStream = getClass().getClassLoader().getResourceAsStream("report2/report5.jrxml");
        
        if (reportStream == null) {
            System.out.println("⚠ ERROR: File laporan tidak ditemukan dalam resource!");
            return;
        }

        
         param.put("tanggalCetak", tanggalCetak);
         jasdes = JRXmlLoader.load(reportStream);
         jasRep = JasperCompileManager.compileReport(jasdes);
         jasPri = JasperFillManager.fillReport(jasRep,param, con);
         JasperViewer jasperViewer = new JasperViewer(jasPri, false);
           JDialog dialog = new JDialog();//the owner
            dialog.setContentPane(jasperViewer.getContentPane());
            dialog.setSize(jasperViewer.getSize());
            dialog.setTitle("Laporan");
            dialog.setAlwaysOnTop(true);
            dialog.setModalityType(Dialog.ModalityType.MODELESS);
            dialog.setModal(true);
             dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(FormUtama.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(FormUtama.class.getName()).log(Level.SEVERE, null, ex);
    } catch (JRException ex) {
        Logger.getLogger(FormUtama.class.getName()).log(Level.SEVERE, null, ex);
    }
       }

}



