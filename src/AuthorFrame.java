import javax.swing.*;
import java.awt.*;

public class AuthorFrame extends JFrame {

    private ImageIcon appIcon = new ImageIcon(Main.class.getResource("/AppIconTitle.png"));
    private JLabel authorLabel = new JLabel();

    AuthorFrame(){
        LabelSetup();
        this.setTitle("Menadżer Haseł - Autor");
        this.setIconImage(appIcon.getImage());

        AuthorPanel authorPanel = new AuthorPanel();
        authorPanel.add(authorLabel);

        this.add(authorPanel);
        this.setSize(900, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void LabelSetup(){
        String authorTextToDisplay = "Autorem tej wspaniałej aplikacji jest Wielki Programista Kamil Przęczek";
        authorLabel.setText(authorTextToDisplay);
        authorLabel.setFont(new Font("Comic Sans",Font.BOLD,25));
        authorLabel.setForeground(Color.BLACK);
        authorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        authorLabel.setVerticalAlignment(SwingConstants.CENTER);
    }
}
