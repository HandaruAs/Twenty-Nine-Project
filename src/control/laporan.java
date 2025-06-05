/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
import control.koneksi;

import gui.FormUtama;
import gui.Transaksi;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.RenderingHints;
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
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
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
       public ChartPanel getChartPanel() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    try {
        String sql = "SELECT COUNT(no_faktur) AS jumlah, tanggal FROM penjualan GROUP BY tanggal ORDER BY tanggal";
        rs = st.executeQuery(sql);
        while (rs.next()) {
            dataset.setValue(rs.getInt("jumlah"), "Transaksi", rs.getString("tanggal"));
        }
    } catch (SQLException ex) {
        Logger.getLogger(laporan.class.getName()).log(Level.SEVERE, null, ex);
    }

    JFreeChart chart = ChartFactory.createBarChart(
            "Grafik Penjualan Harian",
            "Tanggal",
            "Jumlah Transaksi",
            dataset,
            PlotOrientation.VERTICAL,
            true, true, false
    );

    chart.setTextAntiAlias(true);
    chart.setAntiAlias(true);
    chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    chart.getRenderingHints().put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    chart.getRenderingHints().put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    chart.getRenderingHints().put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
    chart.getRenderingHints().put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);

    CategoryPlot plot = chart.getCategoryPlot();
    plot.setRangeGridlinePaint(Color.BLACK);
    
    NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
    rangeAxis.setAutoRange(false);
    rangeAxis.setRange(0, 30);
    rangeAxis.setTickUnit(new NumberTickUnit(5));
    
    chart.setBackgroundPaint(new Color(0, 0, 0, 0)); 
    chart.getPlot().setBackgroundPaint(new Color(0, 0, 0, 0)); 
    chart.getPlot().setOutlinePaint(null); 
    
    return new ChartPanel(chart);
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
           JDialog dialog = new JDialog();
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



