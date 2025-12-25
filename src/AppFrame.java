import javax.swing.*;

public class AppFrame extends JFrame {
    ImageIcon appIcon = new ImageIcon(Main.class.getResource("/AppIconTitle.png"));

    AppFrame(){
        this.setTitle("Menadżer Haseł");
        this.setIconImage(appIcon.getImage());

    }
}
