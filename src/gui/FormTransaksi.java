/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;
import com.jtattoo.plaf.mint.MintLookAndFeel;
import control.control_transaksi;
import control.koneksi;
import control.laporan;
import control.utama;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;


import net.sf.jasperreports.view.JasperViewer;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author handa
 */
public class FormTransaksi extends javax.swing.JFrame {

    //ecak
    private laporan servisReport = new laporan();
    control_transaksi ct;
    laporan lp;
    koneksi db;
    utama ut;

    public String kode_barang;
    public String nama_barang;
    public String ukuran;
    public static int qty;
    public static int harga;
    public static int stok;
    public int total;

    public FormTransaksi() {
        initComponents();
         resetForm(); 
        btnCari.setEnabled(false);
        btnhapus.setEnabled(false);
        btnSimpan.setEnabled(false);
        btncancel.setEnabled(false);
        btnReset.setEnabled(false);
         
        txqty.setEditable(true);
        txnama.setEditable(false);
        txUkuran.setEditable(false);
        txidPelanggan.setEditable(false);
        txNoFaktur.setEditable(false);
        txpelanggan.setEditable(false);
        txtotal2.setEditable(false);
        txkode.setEditable(false);
        txGrandTotal.setEditable(false);
        txKembali.setEditable(false);
        txDiskon.setEditable(false);
        txBayar.setEditable(false);
               
        txDiskon.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                hitungGrandTotal();
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                hitungGrandTotal();
            }

            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                hitungGrandTotal();
            }
        });

        txBayar.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                hitungKembali();
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                hitungKembali();
            }

            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                hitungKembali();
            }
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Dimension dmax = Toolkit.getDefaultToolkit().getScreenSize();
        this.setMaximumSize(dmax);
        this.setSize(dmax.width, dmax.height);
        setLocationRelativeTo(null);
        db = new koneksi();
        ct = new control_transaksi();
        lp = new laporan();
        ut = new utama();
        tbPengeluaran.setModel(ct.model);
        ct.tampilPengeluaran("", "", "", 0, 0, 0);
         ct.noFak();
        setTanggal();
    }

    public void setNamaPengguna(String nama) {
        lblNama.setText(nama); 
    }

     public int parseAngka(String teks) throws NumberFormatException {
        if (teks == null) {
            return 0;
        }
        teks = teks.replaceAll("[^\\d]", ""); 
        if (teks.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(teks);
    }

    public void hitungGrandTotal() {
        try {
            int subTotal = parseAngka(txtotal2.getText());
            int diskon = parseAngka(txDiskon.getText());
            int grandTotal = subTotal - (subTotal * diskon / 100);
            txGrandTotal.setText(String.valueOf(grandTotal));
        } catch (Exception e) {
            txGrandTotal.setText("0");
        }
    }

    public void hitungKembali() {
        try {
            int bayar = parseAngka(txBayar.getText());
            int grandTotal = parseAngka(txGrandTotal.getText());
            int kembali = bayar - grandTotal;
            txKembali.setText(String.valueOf(kembali));
        } catch (Exception e) {
            txKembali.setText("0");
        }
    }
    private void resetForm() {
    
   
    txidPelanggan.setText("");
    txpelanggan.setText("");
    txtotal2.setText("");
    txDiskon.setText("0"); 
    txBayar.setText("");
    txGrandTotal.setText("");
    txKembali.setText("");
   
}
    public void hitungSubtotalDariTable() {
    int subTotal = 0;
    for (int i = 0; i < tbPengeluaran.getRowCount(); i++) {
        try {
            int harga = Integer.parseInt(tbPengeluaran.getValueAt(i, 2).toString());
            int qty = Integer.parseInt(tbPengeluaran.getValueAt(i, 3).toString());
            int total = harga * qty;
            subTotal += total;

            
            tbPengeluaran.setValueAt(total, i, 4);
        } catch (Exception e) {
          
            continue;
        }
    }

    tampilTTL.setText(String.valueOf(subTotal));
    txtotal2.setText(String.valueOf(subTotal));

    hitungGrandTotal();
    hitungKembali();
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPengeluaran = new custom.JTable_Custom();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txNoFaktur = new custom.JTextfieldRounded();
        txidPelanggan = new custom.JTextfieldRounded();
        txpelanggan = new custom.JTextfieldRounded();
        txtotal2 = new custom.JTextfieldRounded();
        txDiskon = new custom.JTextfieldRounded();
        txGrandTotal = new custom.JTextfieldRounded();
        btnCariPlg = new custom.Custom_ButtonRounded();
        txBayar = new custom.JTextfieldRounded();
        txKembali = new custom.JTextfieldRounded();
        btnSave = new custom.Custom_ButtonRounded();
        btnReset = new custom.Custom_ButtonRounded();
        custom_ButtonRounded1 = new custom.Custom_ButtonRounded();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txkode = new custom.JTextfieldRounded();
        txnama = new custom.JTextfieldRounded();
        txUkuran = new custom.JTextfieldRounded();
        txqty = new custom.JTextfieldRounded();
        jLabel19 = new javax.swing.JLabel();
        btnCari = new custom.Custom_ButtonRounded();
        btnNew = new custom.Custom_ButtonRounded();
        btnhapus = new custom.Custom_ButtonRounded();
        btnSimpan = new custom.Custom_ButtonRounded();
        btncancel = new custom.Custom_ButtonRounded();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        tampilTTL = new javax.swing.JLabel();
        lblNama = new javax.swing.JLabel();
        tanggal = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbPengeluaran.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbPengeluaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPengeluaranMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbPengeluaran);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 1210, 150));

        jPanel2.setBackground(new java.awt.Color(255, 255, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("PEMBAYARAN"));

        jLabel4.setText("No. Faktur");

        jLabel5.setText("Nama Pelanggan");

        jLabel6.setText("Sub Total");

        jLabel7.setText("Diskon (%)");

        jLabel8.setText("Grand Total");

        jLabel9.setText("Bayar");

        jLabel10.setText("Kembali");

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("SIMPAN");

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("RESET");

        jLabel17.setText("ID Pelanggan");

        txNoFaktur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txNoFakturActionPerformed(evt);
            }
        });

        txidPelanggan.setText("P0001");

        txpelanggan.setText("UMUM");
        txpelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txpelangganActionPerformed(evt);
            }
        });

        txtotal2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtotal2ActionPerformed(evt);
            }
        });

        txDiskon.setText("0");
        txDiskon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txDiskonActionPerformed(evt);
            }
        });

        txGrandTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txGrandTotalActionPerformed(evt);
            }
        });

        btnCariPlg.setText("Cari");
        btnCariPlg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariPlgActionPerformed(evt);
            }
        });

        txKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txKembaliActionPerformed(evt);
            }
        });

        btnSave.setText("Simpan");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        custom_ButtonRounded1.setText("Kembali");
        custom_ButtonRounded1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                custom_ButtonRounded1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel17)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txpelanggan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                            .addComponent(txNoFaktur, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txidPelanggan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(btnCariPlg, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtotal2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txGrandTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(txDiskon, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(txBayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txKembali, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(custom_ButtonRounded1, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                        .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(157, 157, 157))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel14))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                            .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(custom_ButtonRounded1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtotal2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txNoFaktur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCariPlg, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txDiskon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txidPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(jLabel10)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txKembali, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txpelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txGrandTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 1210, -1));

        jPanel5.setBackground(new java.awt.Color(255, 255, 153));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("ITEM BARANG"));

        jLabel1.setText("KODE BARANG");

        jLabel2.setText("NAMA BARANG");

        jLabel18.setText("Ukuran");

        txkode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txkodeActionPerformed(evt);
            }
        });

        jLabel19.setText("qty");

        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnhapus.setText("Hapus");
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btncancel.setText("Cancel");
        btncancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txkode, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txnama, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txUkuran, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txqty, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btncancel, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btncancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txkode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txUkuran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txqty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnhapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 1190, 110));

        kGradientPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "TOTAL", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        kGradientPanel1.setkBorderRadius(0);
        kGradientPanel1.setkEndColor(new java.awt.Color(255, 255, 153));
        kGradientPanel1.setkStartColor(new java.awt.Color(255, 204, 51));

        tampilTTL.setFont(new java.awt.Font("Segoe UI", 1, 50)); // NOI18N
        tampilTTL.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tampilTTL.setText("0");

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(tampilTTL, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tampilTTL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.add(kGradientPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 70, 470, 100));

        lblNama.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblNama.setForeground(new java.awt.Color(255, 204, 51));
        lblNama.setText("0000");
        jPanel3.add(lblNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 120, 150, 40));

        tanggal.setBackground(new java.awt.Color(255, 204, 51));
        tanggal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tanggal.setForeground(new java.awt.Color(255, 204, 51));
        tanggal.setText("2020-03-28");
        jPanel3.add(tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, -1, 40));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/MENU TRANSAKSI paling paling baru.png"))); // NOI18N
        jLabel13.setText("jLabel13");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1286, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        ut.tampilCountTrx();
        ut.tampilCountBrg();
        ut.tampilCountPlg();
    }//GEN-LAST:event_formWindowClosing

    private void tbPengeluaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPengeluaranMouseClicked
        btnhapus.setEnabled(true);
        btnSimpan.setEnabled(false);
        btncancel.setEnabled(true);
    }//GEN-LAST:event_tbPengeluaranMouseClicked

    private void txkodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txkodeActionPerformed
         koneksi konek = new koneksi();
        konek.setKoneksi();

        Connection conn = konek.getKoneksi();

        try{
            String query = "SELECT * FROM masterbarang WHERE kode_barang = '" + txkode.getText() + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            if(rs.next()){
                String nama = rs.getString("nama_barang");
                String ukurann = rs.getString("ukuran");
                Integer hargaa = rs.getInt("harga");
                Integer stokk = rs.getInt("stok");
                txnama.setText(nama);
                txUkuran.setText(ukurann);
                harga = hargaa;
                ukuran = ukurann;
                btnSimpan.setEnabled(true);
                txqty.setEnabled(true);
                stok = stokk;
            }else{
                JOptionPane.showMessageDialog(this, "Kode Barang Tidak Ditemukan");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "GAGAL MENYIMPAN DATA\n" + e.getMessage());
        }
    }//GEN-LAST:event_txkodeActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        CariBarang caribarang = new CariBarang(this, true,"Transaksi");
        caribarang.setTitle("PILIH BARANG");
        caribarang.setVisible(true);
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        txkode.setEditable(true);
        txDiskon.setEditable(true);
        txBayar.setEditable(true);

        btnhapus.setEnabled(true);
        btnSimpan.setEnabled(true);
        btnCari.setEnabled(true);
        btnNew.setEnabled(false);
        btncancel.setEnabled(true);
        txkode.requestFocus();
         resetForm();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
      int row = tbPengeluaran.getSelectedRow();
    if (row >= 0) {
        ct.model.removeRow(row);
    }

    btnhapus.setEnabled(false);
    btncancel.setEnabled(false);
    btnNew.setEnabled(true);
    btnCari.setEnabled(false);

    hitungSubtotalDariTable(); 
    }//GEN-LAST:event_btnhapusActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
       kode_barang = txkode.getText();
    nama_barang = txnama.getText();
    ukuran = txUkuran.getText(); 
    
    if(txqty.getText().isEmpty()){
        JOptionPane.showMessageDialog(this, "ISI QTY TERLEBIH DAHULU");
        return; 
    }

    qty = Integer.parseInt(txqty.getText());

    if(qty >= stok){
        JOptionPane.showMessageDialog(this, "STOK TIDAK MENCUKUPI");
        return;
    }

    total = harga * qty;

    boolean itemSudahAda = false;

    for (int i = 0; i < tbPengeluaran.getRowCount(); i++) {
        String kodeBarangTabel = tbPengeluaran.getValueAt(i, 0).toString();
        String ukuranTabel = tbPengeluaran.getValueAt(i, 2).toString();
        int hargaTabel = Integer.parseInt(tbPengeluaran.getValueAt(i, 3).toString());

        if (kode_barang.equals(kodeBarangTabel) && ukuran.equals(ukuranTabel) && harga == hargaTabel) {
            int qtyLama = Integer.parseInt(tbPengeluaran.getValueAt(i, 4).toString());
            int qtyBaru = qtyLama + qty;
            int totalBaru = harga * qtyBaru;

            tbPengeluaran.setValueAt(qtyBaru, i, 4);
            tbPengeluaran.setValueAt(totalBaru, i, 5);

            itemSudahAda = true;
            break;
        }
    }

    if (!itemSudahAda) {
        ct.tampilPengeluaran(kode_barang, nama_barang, ukuran, harga, qty, total);
    }

    txkode.setText("");
    txnama.setText("");
    txUkuran.setText("");
    txqty.setText("");

    int subTotal = 0;

    for(int n = 0; n < tbPengeluaran.getRowCount(); n++){
        int total2 = (int) tbPengeluaran.getValueAt(n, 5); 
        subTotal += total2;
    }

    tampilTTL.setText(Integer.toString(subTotal));
    txtotal2.setText(Integer.toString(subTotal));
    txDiskon.setText("0");

    btnNew.setEnabled(true);
    btnCari.setEnabled(false);
    btnSimpan.setEnabled(false);
    btncancel.setEnabled(false);
    btnSave.setEnabled(true);
    btnReset.setEnabled(true);
    
    txDiskon.setEditable(true);
    txBayar.setEditable(true);
    btnCariPlg.setEnabled(true);
    txqty.setEditable(true);
    txqty.setText("");
    txBayar.requestFocus();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btncancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelActionPerformed
      btncancel.setEnabled(false);
        btnSimpan.setEnabled(false);
        btnNew.setEnabled(true);
        btnCari.setEnabled(false);
        btnhapus.setEnabled(false);
               
        txqty.setEditable(false);
        txqty.setText("");
        txkode.setText("");
        txnama.setText("");
        txUkuran.setText("");
        tbPengeluaran.clearSelection();
    }//GEN-LAST:event_btncancelActionPerformed

    private void txpelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txpelangganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txpelangganActionPerformed

    private void txtotal2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtotal2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtotal2ActionPerformed

    private void txDiskonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txDiskonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txDiskonActionPerformed

    private void btnCariPlgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariPlgActionPerformed
         CariBarang caribarang = new CariBarang(this, true, "Pelanggan");
        caribarang.setTitle("PELANGGAN");
        caribarang.setVisible(true);
    }//GEN-LAST:event_btnCariPlgActionPerformed

    private void txKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txKembaliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txKembaliActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
       String nofak = txNoFaktur.getText().trim();
        String kasir = lblNama.getText().trim();
        String id_pelanggan = txidPelanggan.getText().trim();
        String pelanggan = txpelanggan.getText().trim();
        String tanggall = tanggal.getText().trim();

        if (nofak.isEmpty() || kasir.isEmpty() || id_pelanggan.isEmpty() || pelanggan.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Lengkapi semua data terlebih dahulu!");
            return;
        }

        if (tbPengeluaran.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tabel barang kosong!");
            return;
        }

        int total, diskon, bayar, kembali;
        try {
            total = Integer.parseInt(txGrandTotal.getText());
            diskon = Integer.parseInt(txDiskon.getText());
            bayar = Integer.parseInt(txBayar.getText());
            kembali = Integer.parseInt(txKembali.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(rootPane, "Format angka tidak valid.");
            return;
        }

        if (bayar < total) {
            JOptionPane.showMessageDialog(rootPane, "PEMBAYARAN KURANG");
            return;
        }

        try {

            ct.simpanPenjualan(nofak, kasir, id_pelanggan, pelanggan, diskon, total, bayar, kembali, tanggall);

            for (int n = 0; n < tbPengeluaran.getRowCount(); n++) {
                String kode_barang = tbPengeluaran.getValueAt(n, 0).toString(); 
                String nama_barang = tbPengeluaran.getValueAt(n, 1).toString(); 
                String ukuran = tbPengeluaran.getValueAt(n, 2).toString();      
                int harga = Integer.parseInt(tbPengeluaran.getValueAt(n, 3).toString()); 
                int qty = Integer.parseInt(tbPengeluaran.getValueAt(n, 4).toString());   
                int totall = Integer.parseInt(tbPengeluaran.getValueAt(n, 5).toString()); 

                db.setKoneksi();
                String sqli = "SELECT stok FROM masterBarang WHERE kode_barang = ?";
                PreparedStatement pst = db.getKoneksi().prepareStatement(sqli);
                pst.setString(1, kode_barang);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    int stok = rs.getInt("stok");
                    if (qty > stok) {
                        JOptionPane.showMessageDialog(rootPane, "Stok barang " + nama_barang + " tidak mencukupi!");
                        return;
        }
                    int sisa = stok - qty;
                    ct.updateStok(sisa, kode_barang);
                }

                ct.insertBarang(nofak, kode_barang, nama_barang, ukuran, harga, qty, totall);

            }

            JOptionPane.showMessageDialog(rootPane, "DATA BERHASIL DI SIMPAN");

            ct.noFak();
            ((DefaultTableModel) tbPengeluaran.getModel()).setRowCount(0);

            servisReport.printNota(nofak);

            
            btnReset.setEnabled(false);
            btnSave.setEnabled(false);
            btnNew.setEnabled(true);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "GAGAL MENYIMPAN DATA\n" + ex.getMessage());
            Logger.getLogger(FormTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
         ct.model.removeRow(0);
        tampilTTL.setText("0");
        tbPengeluaran.removeAll();
        ct.noFak();
        txpelanggan.setText("");
        txDiskon.setText("0");
        txtotal2.setText("");
        txGrandTotal.setText("");
        txBayar.setText("");
        txKembali.setText("");
        txBayar.setEditable(false);
        txDiskon.setEditable(false);
        ct.model.setRowCount(0);

        btnReset.setEnabled(false);
        btnCariPlg.setEnabled(false);
        btnSave.setEnabled(false);
        btnhapus.setEnabled(false);
        btnSimpan.setEnabled(false);
        btncancel.setEnabled(false);

        btnNew.setEnabled(true);
        
    }//GEN-LAST:event_btnResetActionPerformed

    private void custom_ButtonRounded1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_custom_ButtonRounded1ActionPerformed
         FormUtama frm = new FormUtama();
        dispose();
        FormUtama.pengguna.setText(lblNama.getText());
        frm.setVisible(true);
        ut.tampilCountTrx();
        dispose();
    }//GEN-LAST:event_custom_ButtonRounded1ActionPerformed

    private void txGrandTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txGrandTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txGrandTotalActionPerformed

    private void txNoFakturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txNoFakturActionPerformed
       
    }//GEN-LAST:event_txNoFakturActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormTransaksi().setVisible(true);
            }
        });
    }

     public void setTanggal(){
      Date tgl = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        tanggal.setText(format.format(tgl));
    }
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private custom.Custom_ButtonRounded btnCari;
    private custom.Custom_ButtonRounded btnCariPlg;
    private custom.Custom_ButtonRounded btnNew;
    private custom.Custom_ButtonRounded btnReset;
    private custom.Custom_ButtonRounded btnSave;
    public static custom.Custom_ButtonRounded btnSimpan;
    private custom.Custom_ButtonRounded btncancel;
    private custom.Custom_ButtonRounded btnhapus;
    private custom.Custom_ButtonRounded custom_ButtonRounded1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private keeptoo.KGradientPanel kGradientPanel1;
    public static javax.swing.JLabel lblNama;
    private javax.swing.JLabel tampilTTL;
    private javax.swing.JLabel tanggal;
    private custom.JTable_Custom tbPengeluaran;
    private custom.JTextfieldRounded txBayar;
    private custom.JTextfieldRounded txDiskon;
    private custom.JTextfieldRounded txGrandTotal;
    private custom.JTextfieldRounded txKembali;
    public static custom.JTextfieldRounded txNoFaktur;
    public static custom.JTextfieldRounded txUkuran;
    public static custom.JTextfieldRounded txidPelanggan;
    public static custom.JTextfieldRounded txkode;
    public static custom.JTextfieldRounded txnama;
    public static custom.JTextfieldRounded txpelanggan;
    public static custom.JTextfieldRounded txqty;
    private custom.JTextfieldRounded txtotal2;
    // End of variables declaration//GEN-END:variables

    
   
}
