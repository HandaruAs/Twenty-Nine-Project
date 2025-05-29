/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author handa
 */
public class ha {
    public void ha(String c) {
        JFrame f = new JFrame();
        f.setSize(400,200);
        f.setVisible(true);
        
        JLabel l = new JLabel();
        l.setIcon(new ImageIcon(c));
        
        f.add(l);
}
}