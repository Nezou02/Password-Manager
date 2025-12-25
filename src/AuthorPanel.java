import javax.swing.*;
import java.awt.*;

public class AuthorPanel extends JPanel{

    private ImageIcon image = new ImageIcon(Main.class.getResource("/AuthorBackground.png"));
    private JLabel authorLabel = new JLabel();

    public AuthorPanel(){
        LabelSetup();
        this.setBackground(new Color(0x123456));
        this.setLayout(new BorderLayout());
        this.add(authorLabel);
    }

    private void LabelSetup(){
        String authorTextToDisplay = "Autorem tej wspaniałej aplikacji jest Wielki Programista Kamil Przęczek";
        authorLabel.setText(authorTextToDisplay);
        authorLabel.setFont(new Font("Comic Sans",Font.BOLD,25));
        authorLabel.setForeground(Color.BLACK);
        authorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        authorLabel.setVerticalAlignment(SwingConstants.CENTER);
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
