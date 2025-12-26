import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class AppManager {

    private static AppManager appManager;
    private CardLayout panelSwitcher;
    private JPanel mainPanel;
    private PasswordManagerPanel passwordManagerPanel;
    private PasswordTablePanel passwordTablePanel;
    private List<TableRowPanel> userData = new ArrayList<>();


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
    public void clearManager(){
        passwordTablePanel.ClearTable();
    }
    public void removeData(TableRowPanel tableRowPanel){
        if(userData.contains(tableRowPanel)){
            userData.remove(tableRowPanel);
            passwordTablePanel.RemoveRow(tableRowPanel);

            System.out.println("Liczba elementów w liście po usunieciu: " + userData.size());
        }
    }
    public void addData(TableRowPanel tableRowPanel){
        userData.add(tableRowPanel);
        System.out.println("Liczba elementów w liście po dodaniu: " + userData.size());
    }
}
