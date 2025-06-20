/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package gui_user;

import gui.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import control.koneksi;
import control.laporan;
import static gui.FormUtama.totalBrg;
import static gui.FormUtama.totalPlg;
import static gui.FormUtama.totalTrx2;
import java.awt.BorderLayout;
import java.awt.Color;
import org.jfree.chart.ChartPanel;
/**
 *
 * @author handa
 */
public class FormHome_user extends javax.swing.JInternalFrame {

   Connection conn;

    /**
     * Creates new form FormHome
     */
    public FormHome_user() {
        initComponents();
           this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI ui=(BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
        
        
        koneksi kon = new koneksi();
        kon.setKoneksi();
        conn = kon.getKoneksi();
        
        totalTrx2.setText(String.valueOf(getTotalPenjualan()));
        totalBrg.setText(String.valueOf(getTotalBarang()));
        totalPlg.setText(String.valueOf(getTotalPelanggan()));
        
                
          laporan lap = new laporan();
    ChartPanel grafik = lap.getChartPanel();

    
    grafik.setOpaque(false);
    grafik.setBackground(new Color(0, 0, 0, 0)); 

    panelGrafik.setOpaque(false);
    panelGrafik.setBackground(new Color(0, 0, 0, 0)); 

   
    panelGrafik.removeAll();
    panelGrafik.setLayout(new BorderLayout());
    panelGrafik.add(grafik, BorderLayout.CENTER);
    panelGrafik.revalidate();
    panelGrafik.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundedDesktopPane1 = new custom.RoundedDesktopPane();
        kGradientPanel4 = new keeptoo.KGradientPanel();
        jLabel3 = new javax.swing.JLabel();
        totalBrg = new javax.swing.JLabel();
        kGradientPanel5 = new keeptoo.KGradientPanel();
        jLabel2 = new javax.swing.JLabel();
        totalPlg = new javax.swing.JLabel();
        kGradientPanel6 = new keeptoo.KGradientPanel();
        TotalTransaksi2 = new javax.swing.JLabel();
        totalTrx2 = new javax.swing.JLabel();
        panelGrafik = new javax.swing.JPanel();

        kGradientPanel4.setkEndColor(new java.awt.Color(255, 153, 51));
        kGradientPanel4.setkGradientFocus(400);
        kGradientPanel4.setkStartColor(new java.awt.Color(255, 153, 51));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Jumlah Barang");

        totalBrg.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        totalBrg.setForeground(new java.awt.Color(255, 255, 255));
        totalBrg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        totalBrg.setText("0");

        javax.swing.GroupLayout kGradientPanel4Layout = new javax.swing.GroupLayout(kGradientPanel4);
        kGradientPanel4.setLayout(kGradientPanel4Layout);
        kGradientPanel4Layout.setHorizontalGroup(
            kGradientPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 142, Short.MAX_VALUE))
                    .addComponent(totalBrg, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
                .addContainerGap())
        );
        kGradientPanel4Layout.setVerticalGroup(
            kGradientPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(totalBrg)
                .addContainerGap())
        );

        roundedDesktopPane1.add(kGradientPanel4);
        kGradientPanel4.setBounds(270, 20, 260, 110);

        kGradientPanel5.setkEndColor(new java.awt.Color(255, 153, 51));
        kGradientPanel5.setkStartColor(new java.awt.Color(255, 153, 51));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Jumlah Pelanggan");

        totalPlg.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        totalPlg.setForeground(new java.awt.Color(255, 255, 255));
        totalPlg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        totalPlg.setText("0");

        javax.swing.GroupLayout kGradientPanel5Layout = new javax.swing.GroupLayout(kGradientPanel5);
        kGradientPanel5.setLayout(kGradientPanel5Layout);
        kGradientPanel5Layout.setHorizontalGroup(
            kGradientPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 141, Short.MAX_VALUE))
                    .addComponent(totalPlg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        kGradientPanel5Layout.setVerticalGroup(
            kGradientPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(totalPlg)
                .addContainerGap())
        );

        roundedDesktopPane1.add(kGradientPanel5);
        kGradientPanel5.setBounds(560, 20, 280, 110);

        kGradientPanel6.setkEndColor(new java.awt.Color(255, 153, 51));
        kGradientPanel6.setkStartColor(new java.awt.Color(255, 153, 51));

        TotalTransaksi2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        TotalTransaksi2.setForeground(new java.awt.Color(255, 255, 255));
        TotalTransaksi2.setText("Total Transaksi");

        totalTrx2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        totalTrx2.setForeground(new java.awt.Color(255, 255, 255));
        totalTrx2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        totalTrx2.setText("0");

        javax.swing.GroupLayout kGradientPanel6Layout = new javax.swing.GroupLayout(kGradientPanel6);
        kGradientPanel6.setLayout(kGradientPanel6Layout);
        kGradientPanel6Layout.setHorizontalGroup(
            kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel6Layout.createSequentialGroup()
                        .addComponent(TotalTransaksi2)
                        .addGap(0, 108, Short.MAX_VALUE))
                    .addComponent(totalTrx2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        kGradientPanel6Layout.setVerticalGroup(
            kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TotalTransaksi2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(totalTrx2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        roundedDesktopPane1.add(kGradientPanel6);
        kGradientPanel6.setBounds(10, 20, 240, 110);

        panelGrafik.setBackground(new java.awt.Color(255, 255, 204));
        panelGrafik.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Grafik Penjualan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(51, 51, 51))); // NOI18N
        panelGrafik.setLayout(new java.awt.BorderLayout());
        roundedDesktopPane1.add(panelGrafik);
        panelGrafik.setBounds(0, 170, 880, 320);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundedDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundedDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
  public int getTotalPenjualan() {
        int total = 0;
        String sql = "SELECT count(*) AS total_penjualan FROM penjualan";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                total = rs.getInt("total_penjualan");
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return total;
    }
    
    public int getTotalBarang() {
        int total = 0;
        String sql = "SELECT COUNT(*) AS total_barang FROM masterbarang";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                total = rs.getInt("total_barang");
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return total;
    }
    
    public int getTotalPelanggan() {
        int total = 0;
        String sql = "SELECT COUNT(*) AS total_pelanggan FROM pelanggan";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                total = rs.getInt("total_pelanggan");
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return total;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TotalTransaksi2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private keeptoo.KGradientPanel kGradientPanel4;
    private keeptoo.KGradientPanel kGradientPanel5;
    public keeptoo.KGradientPanel kGradientPanel6;
    public static javax.swing.JPanel panelGrafik;
    private custom.RoundedDesktopPane roundedDesktopPane1;
    public static javax.swing.JLabel totalBrg;
    public static javax.swing.JLabel totalPlg;
    public static javax.swing.JLabel totalTrx2;
    // End of variables declaration//GEN-END:variables
}
