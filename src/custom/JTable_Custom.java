package custom;

import javax.swing.JTable;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mahar
 */
public class JTable_Custom extends JTable {

    private int selectedRow = -1;
    private int hoveredRow = -1;
    private final Font font = new Font("Poppins", Font.PLAIN, 12);

    public JTable_Custom() {
        setFont(font);
        setFontKeSemuaKomponen(this, font);
        setRowHeight(30);
        setBackground(new Color(255, 255, 180));
        setForeground(Color.BLACK);
        setSelectionBackground(new Color(255, 204, 0));
        setSelectionForeground(Color.BLACK);

        setShowGrid(true);
        setGridColor(Color.BLACK);
        setShowHorizontalLines(true);
        setShowVerticalLines(true);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Setting untuk header tabel
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setFont(font);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                TablezHeader header = new TablezHeader(value.toString());
                header.setHorizontalAlignment(JLabel.CENTER);
                return header;
            }
        });

        // Renderer default untuk sel
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                com.setFont(font);

                if (isSelected) {
                    com.setBackground(new Color(255, 204, 0));
                    com.setForeground(Color.BLACK);
                } else if (row == hoveredRow) {
                    com.setBackground(new Color(255, 255, 200));
                    com.setForeground(Color.BLACK);
                } else {
                    com.setBackground(new Color(255, 255, 180));
                    com.setForeground(Color.BLACK);
                }

                setBorder(noFocusBorder);
                return com;
            }
        });

        // Event listener untuk efek hover dan klik
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = getSelectedRow();
                if (row == selectedRow) {
                    clearSelection();
                    selectedRow = -1;
                } else {
                    selectedRow = row;
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hoveredRow = -1;
                repaint();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int row = rowAtPoint(e.getPoint());
                if (row != hoveredRow) {
                    hoveredRow = row;
                    repaint();
                }
            }
        });
    }

    // Fungsi untuk menambah baris
    public void addRow(Object[] row) {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(row);
    }

    // Fungsi untuk menerapkan font ke semua komponen
    private void setFontKeSemuaKomponen(Component comp, Font font) {
        comp.setFont(font);
        if (comp instanceof java.awt.Container) {
            for (Component child : ((java.awt.Container) comp).getComponents()) {
                setFontKeSemuaKomponen(child, font);
            }
        }
    }

    // Custom header class
    private class TablezHeader extends JLabel {

        public TablezHeader(String text) {
            super(text);
            setOpaque(true);
            setBackground(new Color(255, 235, 150));
            setFont(font);
            setForeground(Color.BLACK);
            setBorder(new EmptyBorder(10, 5, 10, 5));
            setHorizontalAlignment(CENTER);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1); // Border bawah header
             g.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight());  // garis kanan
        }
    }
}
