import javax.swing.*;
import java.awt.*;

public class AuthorFrame extends JFrame {

    private ImageIcon appIcon = new ImageIcon(Main.class.getResource("/AppIconTitle.png"));
    private JLabel authorLabel = new JLabel();

    AuthorFrame(){
        this.setTitle("Menadżer Haseł - Autor");
        this.setIconImage(appIcon.getImage());
        AuthorPanel authorPanel = new AuthorPanel();

        this.add(authorPanel);
        this.setSize(900, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
