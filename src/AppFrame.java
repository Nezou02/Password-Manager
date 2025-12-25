import javax.swing.*;

public class AppFrame extends JFrame {

    private ImageIcon appIcon = new ImageIcon(Main.class.getResource("/AppIconTitle.png"));

    public AppFrame(){
        this.setTitle("Menadżer Haseł");
        this.setIconImage(appIcon.getImage());

        InitliazeAppPanel();

        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void InitliazeAppPanel(){
        MainMenuPanel background = new MainMenuPanel();
        this.add(background);
    }
}
