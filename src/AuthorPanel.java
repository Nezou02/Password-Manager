import javax.swing.*;
import java.awt.*;

public class AuthorPanel extends JPanel{
    private ImageIcon image = new ImageIcon(Main.class.getResource("/AuthorBackground.png"));

    AuthorPanel(){
        this.setBackground(new Color(0x123456));
        this.setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // usuwa poprzednie tlo i setuje background kolor

        Image background = image.getImage();
        int panelWidth = getWidth();

        int backgroundWidth = background.getWidth(this);

        int x = (panelWidth - backgroundWidth) / 2;
        g.drawImage(background, x, 0, this);
    }
}
