import javax.swing.*;

public class AppFrame extends JFrame {

    private ImageIcon appIcon = new ImageIcon(Main.class.getResource("/AppIconTitle.png"));

    AppFrame(){
        this.setTitle("Menadżer Haseł");
        this.setIconImage(appIcon.getImage());

        MainMenuPanel background = new MainMenuPanel();
        this.add(background);

        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
