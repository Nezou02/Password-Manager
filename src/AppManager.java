import javax.crypto.Cipher;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.ArrayList;
import java.security.MessageDigest;
import javax.crypto.spec.SecretKeySpec;

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
    public void readDataFromEncryptedFile(){
        // TODO
    }
    public void saveDataToEncryptedFile(String passwordToFile){
        byte[] dataToSave;

        if(userData.size() >= 1){
            dataToSave = encryptBytes(passwordToFile);
            selectFilePath(dataToSave);
        }
        else{
            JOptionPane.showMessageDialog(mainPanel, "Nie można zapisać pustej listy", "Zapis Pliku", JOptionPane.WARNING_MESSAGE);
        }
    }
    private byte[] converDataToBytes(List<TableRowPanel> data){
        PasswordEntry rowData = data.get(0).getUserRowData();
        String stringData = rowData.getSite();
        stringData += "\n";
        stringData += rowData.getLogin();
        stringData += "\n";
        stringData += rowData.getPassword();

        for(int i = 1; i < data.size(); i++){
            rowData = data.get(i).getUserRowData();

            stringData += "\n";
            stringData += rowData.getSite();
            stringData += "\n";
            stringData += rowData.getLogin();
            stringData += "\n";
            stringData += rowData.getPassword();
        }

        byte[] dataBytes = stringData.getBytes(StandardCharsets.UTF_8);
        return dataBytes;
    }
    private SecretKeySpec createKey(String password) throws Exception {
        MessageDigest sha = MessageDigest.getInstance("SHA-256");

        byte[] keyBytes = sha.digest(password.getBytes(StandardCharsets.UTF_8));

        return new SecretKeySpec(keyBytes, 0, 16, "AES");
    }
    private byte[] encryptBytes(String passwordToFile){

        byte[] dataToSave = converDataToBytes(userData);
        byte[] encrypted = new byte[0];

        try{
            SecretKeySpec key = createKey(passwordToFile);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            encrypted = cipher.doFinal(dataToSave);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return encrypted;
    }
    private void selectFilePath(byte[] encryptedData){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Wybierz miejsce do zapisania pliku");
        int userSelection = fileChooser.showSaveDialog(mainPanel);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try {
                Files.write(fileToSave.toPath(), encryptedData);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Błąd zapisu pliku!");
            }
        }
    }
}
