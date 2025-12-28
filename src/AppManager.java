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
        this.addData(tableRowPanel);
    }
    public void clearManager(){
        passwordTablePanel.ClearTable();
        userData.clear();
    }
    public void removeData(TableRowPanel tableRowPanel){
        if(userData.contains(tableRowPanel)){

            userData.remove(tableRowPanel);
            passwordTablePanel.RemoveRow(tableRowPanel);
        }
    }
    public void addData(TableRowPanel tableRowPanel){
        userData.add(tableRowPanel);
    }
    public void readDataFromEncryptedFile(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Wybierz zaszyfrowany plik do odczytania");
        int userSelection = fileChooser.showOpenDialog(mainPanel);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            MessageToUser message = new MessageToUser("Podaj hasło którym plik jets szyfrowany");
            JTextField passwordTextField = new JTextField();

            JPanel popUpWindowsPanel = new JPanel();
            popUpWindowsPanel.setLayout(new BoxLayout(popUpWindowsPanel, BoxLayout.Y_AXIS));

            popUpWindowsPanel.add(Box.createVerticalGlue());
            popUpWindowsPanel.add(Box.createVerticalStrut(25));
            popUpWindowsPanel.add(message);
            popUpWindowsPanel.add(Box.createVerticalStrut(25));
            popUpWindowsPanel.add(passwordTextField);
            popUpWindowsPanel.add(Box.createVerticalGlue());

            int result = JOptionPane.showOptionDialog(
                    mainPanel, popUpWindowsPanel, "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null );

            if (passwordTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(mainPanel, "Hasło jest obowiązkowe do odczytania pliku!!", "Odczyt Pliku", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (result == 0){
                DecryptFile(passwordTextField.getText(), selectedFile);
            }
        }
    }
    private void DecryptFile(String passwordGivenByUser, File file) {
        try{

            byte[] encrypted = Files.readAllBytes(file.toPath());

            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            byte[] keyBytes = sha.digest(passwordGivenByUser.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec key = new SecretKeySpec(keyBytes, 0, 16, "AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);

            byte[] decrypted = cipher.doFinal(encrypted);
            String originalText = new String(decrypted, StandardCharsets.UTF_8);

            addDataToManager(originalText);

        } catch (javax.crypto.BadPaddingException e) {
            JOptionPane.showMessageDialog(mainPanel, "Złe hasło lub brakuje danych w pliku!", "Błąd odszyfrowania", JOptionPane.ERROR_MESSAGE);
        } catch (javax.crypto.IllegalBlockSizeException e) {
            JOptionPane.showMessageDialog(mainPanel, "Nie jest to zaszyfrowany plik AES", "Błąd odszyfrowania", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(mainPanel, "Inny błąd podczas odszyfrowywania!", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void addDataToManager(String originalText) {
        String[] data = originalText.split("\n");

        for (int i = 0; i < data.length; i += 3){
            if (i + 2 < data.length) {
                String site = data[i];
                String login = data[i + 1];
                String password = data[i + 2];
                PasswordEntry rowData = new PasswordEntry(site, login, password);
                addRowToTheManager(rowData);
            }
        }
    }
    public void saveDataToEncryptedFile(String passwordToFile){
        byte[] dataToSave;

        if(userData.size() >= 1){
            dataToSave = encryptBytes(passwordToFile);
            selectFilePathAndSave(dataToSave);
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
    private void selectFilePathAndSave(byte[] encryptedData){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Wybierz miejsce do zapisania pliku");
        int userSelection = fileChooser.showSaveDialog(mainPanel);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try {
                Files.write(fileToSave.toPath(), encryptedData);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(mainPanel, "Błąd zapisu pliku!", "Błąd" ,JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    public List<TableRowPanel> getUserData (){ return userData; }
}
