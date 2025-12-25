import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    private final LeftBackgroundPanel leftBackgroundPanel = new LeftBackgroundPanel();
    private final RightBackgroundPanel rightBackgroundPanel = new RightBackgroundPanel();

    LoginPanel() {
        this.setLayout(new GridLayout(1, 2));
        this.setOpaque(false);
        this.add(leftBackgroundPanel);
        this.add(rightBackgroundPanel);
    }






    private class LeftBackgroundPanel extends JPanel {
        private ImageIcon image = new ImageIcon(Main.class.getResource("/LoginBackgroundLeft.png"));

        private LeftBackgroundPanel() {
            this.setLayout(new BorderLayout());
            this.setBackground(Color.white);
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int panelWidth = getWidth();
            int panelHeight = getHeight();

            Image background = image.getImage();
            int backgroundWidth = background.getWidth(this);
            int backgroundHeight = background.getHeight(this);
            int x = (panelWidth - backgroundWidth) / 2;
            int y = (panelHeight - backgroundHeight) / 2;

            g.drawImage(background, x, y,this);
        }
    }
    private class RightBackgroundPanel extends JPanel {
        private ImageIcon image = new ImageIcon(Main.class.getResource("/LoginBackgroundRight.png"));

        private RightBackgroundPanel() {
            this.setLayout(new BorderLayout());
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Image background = image.getImage();
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
