import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {

    // Panels used by the app
    MainMenuPanel mainMenuPanel = new MainMenuPanel();
    AuthorPanel authorPanel = new AuthorPanel();
    LoginPanel loginPanel = new LoginPanel();
    //
    private ImageIcon appIcon = new ImageIcon(Main.class.getResource("/AppIconTitle.png"));
    private JPanel mainPanel = new JPanel();
    private CardLayout cardLayout = new CardLayout();

    public AppFrame(){
        mainPanel.setLayout(cardLayout);
        this.add(mainPanel);

        InitializeAppPanels();
        InitializeAppFrameParameters();
    }

    private void InitializeAppPanels(){
        mainPanel.add(mainMenuPanel, PanelsNames.MainMenuPanel);
        mainPanel.add(authorPanel, PanelsNames.AuthorPanel);
        mainPanel.add(loginPanel, PanelsNames.LoginPanel);
        // TODO
    }
    private void InitializeAppFrameParameters(){
        this.setTitle("Menadżer Haseł");
        this.setIconImage(appIcon.getImage());
        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public CardLayout getCardLayout() { return cardLayout; }
    public JPanel getMainPanel() { return mainPanel; }
}
