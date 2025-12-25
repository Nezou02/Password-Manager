import javax.swing.*;
import java.awt.*;

public class AppManager {

    private static AppManager appManager;
    private CardLayout panelSwitcher;
    private JPanel mainPanel;

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
    }

    public void showPanel(String nameOfThePanel){
        panelSwitcher.show(mainPanel, nameOfThePanel);
    }
}
