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

    public JTable_Custom() {
        setShowHorizontalLines(true);
        setShowVerticalLines(false); // Untuk tampilan yang lebih bersih
        setGridColor(new Color(230, 230, 230));
        setRowHeight(40);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Setting untuk header tabel
        getTableHeader().setReorderingAllowed(false);
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
                com.setFont(new Font("SansSerif", Font.PLAIN, 12));
                
                if (isSelected) {
                    com.setBackground(new Color(204, 229, 255));  // Warna saat dipilih
                    com.setForeground(new Color(0, 76, 153));
                } else if (row == hoveredRow) {
                    com.setBackground(new Color(245, 245, 245)); // Warna saat hover
                    com.setForeground(new Color(102, 102, 102));
                } else {
                    com.setBackground(Color.WHITE);
                    com.setForeground(new Color(102, 102, 102));
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

    // Custom header class
    private class TablezHeader extends JLabel {

        public TablezHeader(String text) {
            super(text);
            setOpaque(true);
            setBackground(new Color(240, 240, 240));
            setFont(new Font("SansSerif", Font.BOLD, 13));
            setForeground(new Color(102, 102, 102));
            setBorder(new EmptyBorder(10, 5, 10, 5));
            setHorizontalAlignment(CENTER);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(200, 200, 200));
            g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1); // Border bawah header
        }
    }
}
