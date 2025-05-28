/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package custom;
import javax.swing.JPasswordField;
import javax.swing.plaf.basic.BasicPasswordFieldUI;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.border.EmptyBorder;
/**
 *
 * @author handa
 */
public class JPasswordFieldRounded extends JPasswordField {

    private PasswordFieldUI passwordUI;

    public JPasswordFieldRounded() {
        passwordUI = new PasswordFieldUI(this);
        setUI(passwordUI);
    }

    private class PasswordFieldUI extends BasicPasswordFieldUI {

        private JPasswordField passwordField;
        private Border border;
        private int round = 15;

        public int getRound() {
            return round;
        }

        public void setRound(int round) {
            this.round = round;
            border.setRound(round);
            passwordField.repaint();
        }

        public PasswordFieldUI(JPasswordField passwordField) {
            this.passwordField = passwordField;
            border = new Border(10);
            border.setRound(round);

            Color boldYellow = new Color(255, 204, 0);

            border.setColor(boldYellow);
            border.setFocusColor(boldYellow);

            passwordField.setBorder(border);
            passwordField.setOpaque(false);
            passwordField.setSelectionColor(Color.gray);
            passwordField.setSelectedTextColor(Color.white);

            passwordField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    border.setColor(boldYellow);
                    passwordField.repaint();
                }

                @Override
                public void focusLost(FocusEvent e) {
                    border.setColor(boldYellow);
                    passwordField.repaint();
                }
            });
        }

        @Override
        protected void paintBackground(Graphics g) {
            if (passwordField.isOpaque()) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(passwordField.getBackground());
                g2.fillRoundRect(0, 0, passwordField.getWidth(), passwordField.getHeight(), round, round);
                g2.dispose();
            }
        }

        private class Border extends EmptyBorder {

            private Color focusColor = new Color(255, 204, 0);
            private Color color = new Color(255, 204, 0);
            private int round;

            public Color getFocusColor() {
                return focusColor;
            }

            public void setFocusColor(Color focusColor) {
                this.focusColor = focusColor;
            }

            public Color getColor() {
                return color;
            }

            public void setColor(Color color) {
                this.color = color;
            }

            public int getRound() {
                return round;
            }

            public void setRound(int round) {
                this.round = round;
            }

            public Border(int border) {
                super(border, border, border, border);
            }

            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setStroke(new BasicStroke(2.5f));
                g2.setColor(c.isFocusOwner() ? focusColor : color);
                g2.drawRoundRect(x, y, width - 1, height - 1, round, round);
                g2.dispose();
            }
        }
    }
}