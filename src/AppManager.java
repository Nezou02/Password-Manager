import javax.swing.*;
import java.awt.*;

public class AppManager {

    private static AppManager appManager;
    private CardLayout panelSwitcher;
    private JPanel mainPanel;
    private PasswordManagerPanel passwordManagerPanel;
    private PasswordTablePanel passwordTablePanel;

    private AppManager(){}

    public static AppManager GetInstance(){
           if (appManager == null)
               appManager = new AppManager();

           return appManager;
    }

    public void startApplication(){
        AppFrame appFrame = new AppFrame();
        panelSwitcher = appFrame.getCardLayout();
        mainPanel = appFrame.getMainPanel();
        passwordManagerPanel = appFrame.getPasswordManagerPanel();
        passwordTablePanel = passwordManagerPanel.getPasswordTablePanel();
    }

    public void showPanel(String nameOfThePanel){
        panelSwitcher.show(mainPanel, nameOfThePanel);
    }
    public void addRowToTheManager(PasswordEntry passwordEntry){
        TableRowPanel tableRowPanel = new TableRowPanel(passwordEntry);
        passwordTablePanel.AddRow(tableRowPanel);
    }
}
