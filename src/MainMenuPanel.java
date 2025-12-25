import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {
    private ImageIcon image = new ImageIcon(Main.class.getResource("/background.png"));

    public MainMenuPanel(){
        this.setBackground(new Color(0xF3748F)); //0xF3748F kolor tła obrazka
        this.setLayout(new BorderLayout());

        MainMenuButtonPanel mainMenuButtonPanel = new MainMenuButtonPanel();
        this.add(mainMenuButtonPanel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // usuwa poprzednie tlo i setuje background kolor

        Image background = image.getImage();
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        int backgroundWidth = background.getWidth(this);
        int backgroundHeight = background.getHeight(this);

        int x = (panelWidth - backgroundWidth) / 2;
        int y = (panelHeight - backgroundHeight) / 2;
        g.drawImage(background, x, y, this);
    }
}
