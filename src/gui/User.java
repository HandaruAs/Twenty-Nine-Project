/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package gui;

import control.MasterBarang;
import control.control_user;
import control.utama;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.Component;
import java.awt.Container;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.Timer;

/**
 *
 * @author handa
 */
public class User extends javax.swing.JInternalFrame {
    utama ut;
    control_user ur;
    StringBuilder rfidBuilder = new StringBuilder();
    /**
     * Creates new form User
     */
    public User() {
        initComponents();

        
    Font font = new Font("Poppins", Font.PLAIN, 11);
    setFontKeSemuaKomponen(this, font);

        
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI ui=(BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
        ut = new utama();
        ur = new control_user();
        tampil();
       
        txKategori.setEditable(false);
        txUser.setEditable(false);
        txPass.setEditable(false);
        txNama.setEditable(false);
        txNohp.setEditable(false);
        txRFID.setEditable(false);


        btnSimpan.setEnabled(false);
        btnEdit.setEnabled(true);
        btnHapus.setEnabled(true);
        btnCancel.setEnabled(false);

       
       // Tangkap semua input RFID dari scanner lewat KeyboardFocusManager
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            public boolean dispatchKeyEvent(KeyEvent evt) {
                if (evt.getID() == KeyEvent.KEY_PRESSED) {
                    if (txRFID.isFocusOwner()) {
                        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                            String hasilRFID = rfidBuilder.toString().replaceAll("[^\\p{Print}]", "").trim();
                            txRFID.setText(hasilRFID);
                            rfidBuilder.setLength(0);
                        } else {
                            rfidBuilder.append(evt.getKeyChar());
                        }
                    }
                }
                return false;
            }
        });

        setFocusable(true);
        requestFocusInWindow();

    }
    
    public void tampil(){
         tabelUser.setModel(ur.model);
        ur.model.setRowCount(0);
        ur.tampil();
    }
    public void clear(){
        txNohp.setText("");
        txRFID.setText("");
        txUser.setText("");
        txNama.setText("");
        txPass.setText("");
        txKategori.setText("");
        tabelUser.clearSelection();
    }
    public String hashPassword(String password) {
    try {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(password.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException(e);
    }
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
        PanelForm = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txKategori = new custom.JTextfieldRounded();
        txUser = new custom.JTextfieldRounded();
        txNama = new custom.JTextfieldRounded();
        txNohp = new custom.JTextfieldRounded();
        txPass = new custom.JPasswordFieldRounded();
        checkpass = new javax.swing.JCheckBox();
        txRFID = new custom.JTextfieldRounded();
        jLabel6 = new javax.swing.JLabel();
        kGradientPanel11 = new keeptoo.KGradientPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelUser = new custom.JTable_Custom();
        btnNew = new custom.Custom_ButtonRounded();
        btnSimpan = new custom.Custom_ButtonRounded();
        btnHapus = new custom.Custom_ButtonRounded();
        btnCancel = new custom.Custom_ButtonRounded();
        btnEdit = new custom.Custom_ButtonRounded();

        setPreferredSize(new java.awt.Dimension(880, 490));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jPanel3.setBackground(new java.awt.Color(102, 0, 0));

        PanelForm.setBackground(new java.awt.Color(255, 255, 204));
        PanelForm.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ENTRY USER", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel1.setText("PASSWORD");

        jLabel2.setText("USERNAME");

        jLabel3.setText("NAMA");

        jLabel4.setText("NO HP");

        jLabel5.setText("RFID");

        txKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txKategoriActionPerformed(evt);
            }
        });

        txUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txUserActionPerformed(evt);
            }
        });

        txNohp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txNohpActionPerformed(evt);
            }
        });

        txPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txPassActionPerformed(evt);
            }
        });

        checkpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkpassActionPerformed(evt);
            }
        });

        txRFID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txRFIDActionPerformed(evt);
            }
        });

        jLabel6.setText("Kategori");

        javax.swing.GroupLayout PanelFormLayout = new javax.swing.GroupLayout(PanelForm);
        PanelForm.setLayout(PanelFormLayout);
        PanelFormLayout.setHorizontalGroup(
            PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelFormLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelFormLayout.createSequentialGroup()
                        .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(txUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txNama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txNohp, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                            .addComponent(txPass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkpass, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                    .addGroup(PanelFormLayout.createSequentialGroup()
                        .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txRFID, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PanelFormLayout.setVerticalGroup(
            PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelFormLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txRFID, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkpass, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txNohp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        kGradientPanel11.setkBorderRadius(0);
        kGradientPanel11.setkEndColor(new java.awt.Color(255, 204, 0));
        kGradientPanel11.setkStartColor(new java.awt.Color(255, 204, 0));

        tabelUser.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelUserMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelUser);

        javax.swing.GroupLayout kGradientPanel11Layout = new javax.swing.GroupLayout(kGradientPanel11);
        kGradientPanel11.setLayout(kGradientPanel11Layout);
        kGradientPanel11Layout.setHorizontalGroup(
            kGradientPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
        );
        kGradientPanel11Layout.setVerticalGroup(
            kGradientPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        btnNew.setText("Baru");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(PanelForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(kGradientPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PanelForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kGradientPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        ut.tampilCountBrg();
        ut.tampilCountPlg();
        ut.tampilCountTrx();
    }//GEN-LAST:event_formInternalFrameClosing

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
    
    edit = false;    
    txKategori.setEditable(true);        
    txRFID.setEditable(false);        
    txUser.setEditable(true);
    txPass.setEditable(true);
    txNama.setEditable(true);
    txNohp.setEditable(true);

    btnSimpan.setEnabled(true);
    btnCancel.setEnabled(true);
    btnNew.setEnabled(false);
    
    txRFID.requestFocus();
    
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
   String rfid = txRFID.getText();
    String role = txKategori.getText().toLowerCase();
    String user = txUser.getText();
    String pass = txPass.getText();
    String nama = txNama.getText();
    String nohp = txNohp.getText();

    if (rfid.isEmpty() || role.isEmpty() || user.isEmpty() || pass.isEmpty() || nama.isEmpty() || nohp.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Semua kolom harus diisi!");
        return;
    }

    if (!role.equals("admin") && !role.equals("user")) {
        JOptionPane.showMessageDialog(this, "Role hanya boleh 'admin' atau 'user'", "Peringatan", JOptionPane.WARNING_MESSAGE);
        return;
    }

    if (pass.length() < 6) {
        JOptionPane.showMessageDialog(this, "Password minimal harus 6 karakter!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        return; 
    }

    String hashedPass = hashPassword(pass); 

    if (edit == true) {
        try {
            ur.edit(rfid, role, user, hashedPass, nama, nohp); 
            JOptionPane.showMessageDialog(rootPane, "DATA BERHASIL DI UPDATE");
            clear();  
            tampil();
            txUser.setEditable(false);
            txPass.setEditable(false);
            txNama.setEditable(false);
            txNohp.setEditable(false);
            txKategori.setEditable(false);
            txRFID.setEditable(false);
            btnSimpan.setEnabled(false);
            btnCancel.setEnabled(false);
            btnNew.setEnabled(true);
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else {
      
        if (ur.cekDuplikatRfid(rfid)) {
            JOptionPane.showMessageDialog(this, "RFID sudah terdaftar! Gunakan RFID yang lain.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            ur.simpan(rfid, role, user, hashedPass, nama, nohp); 
            JOptionPane.showMessageDialog(rootPane, "DATA BERHASIL DISIMPAN");
            tampil();
            clear();
            btnSimpan.setEnabled(false);
            btnCancel.setEnabled(false);
            btnNew.setEnabled(true);

            txUser.setEditable(false);
            txPass.setEditable(false);
            txNama.setEditable(false);
            txNohp.setEditable(false);
            txKategori.setEditable(false);
            txRFID.setEditable(false);
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
   String id = txRFID.getText();
        try {
            ur.hapus(id);
                   JOptionPane.showMessageDialog(rootPane, "DATA BERHASIL DI HAPUS");
            tampil();
            clear();
           
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        txKategori.setEditable(false);
        txUser.setEditable(false);
        txPass.setEditable(false);
        txNama.setEditable(false);
        txRFID.setEditable(false);
        btnHapus.setEnabled(false);
        btnEdit.setEnabled(false);
        txNohp.setEditable(false);
        btnSimpan.setEnabled(false);
        btnCancel.setEnabled(false);
        btnNew.setEnabled(true);
        clear();
        txRFID.setText(""); 
        tabelUser.clearSelection();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
    
        edit = true;
        btnEdit.setEnabled(false);
        btnHapus.setEnabled(false);
        btnSimpan.setEnabled(true);
        txKategori.setEditable(true);
        txRFID.setEditable(false);
        txUser.setEditable(true);
        txPass.setEditable(true);
        txNama.setEditable(true);
        txNohp.setEditable(true);
        txRFID.requestFocus();
    }//GEN-LAST:event_btnEditActionPerformed
boolean edit = false;
    private void txUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txUserActionPerformed

    private void txNohpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txNohpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txNohpActionPerformed

    private void checkpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkpassActionPerformed
        if (checkpass.isSelected()) {
        txPass.setEchoChar((char) 0);
    } else {
        txPass.setEchoChar('*'); 
    }
    }//GEN-LAST:event_checkpassActionPerformed

    private void txPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txPassActionPerformed

    private void tabelUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelUserMouseClicked
        int row = tabelUser.getSelectedRow();
        txRFID.setText(tabelUser.getValueAt(row, 0).toString());      
        txUser.setText(tabelUser.getValueAt(row, 1).toString());
        txNama.setText(tabelUser.getValueAt(row, 2).toString());
        txNohp.setText(tabelUser.getValueAt(row, 3).toString());
        txKategori.setText(tabelUser.getValueAt(row, 4).toString());
        
        btnEdit.setEnabled(true);
        btnHapus.setEnabled(true);
        btnCancel.setEnabled(true);
    }//GEN-LAST:event_tabelUserMouseClicked

    private void txKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txKategoriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txKategoriActionPerformed

    private void txRFIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txRFIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txRFIDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelForm;
    private custom.Custom_ButtonRounded btnCancel;
    private custom.Custom_ButtonRounded btnEdit;
    private custom.Custom_ButtonRounded btnHapus;
    private custom.Custom_ButtonRounded btnNew;
    private custom.Custom_ButtonRounded btnSimpan;
    private javax.swing.JCheckBox checkpass;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private keeptoo.KGradientPanel kGradientPanel11;
    private custom.JTable_Custom tabelUser;
    private custom.JTextfieldRounded txKategori;
    private custom.JTextfieldRounded txNama;
    private custom.JTextfieldRounded txNohp;
    private custom.JPasswordFieldRounded txPass;
    private custom.JTextfieldRounded txRFID;
    private custom.JTextfieldRounded txUser;
    // End of variables declaration//GEN-END:variables

   private void setFontKeSemuaKomponen(Container container, Font font) {
    for (Component comp : container.getComponents()) {
        comp.setFont(font);
        if (comp instanceof Container) {
            setFontKeSemuaKomponen((Container) comp, font);
        }
    }
}

}
