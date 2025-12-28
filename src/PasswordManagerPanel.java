import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;
//glowne ui od managera
public class PasswordManagerPanel extends JPanel implements ActionListener {

    private ImageIcon image = new ImageIcon(Main.class.getResource("/ManagerBackground.png"));
    private CustomButton returnButton = new CustomButton("Powrót");
    private CustomButton passwordsWiper = new CustomButton("Wyczyść Dane");
    private CustomButton addButton = new CustomButton("Dodaj Dane");
    private SortingTextField sortingTextField = new SortingTextField("Sortowanie");
    private JComboBox<String> sortingOptions;
    private CustomButton saveToFileButton = new CustomButton("Zapis Do Pliku");
    private CustomButton readFileButton = new CustomButton("Odczyt haseł z pliku szyfrowanego");
    private CheckBox checkBox = new CheckBox();
    private ComboBox numeberOfCharacters = new ComboBox();

    private AppManager appManager = AppManager.GetInstance();
    private JPanel buttonsPanel = new JPanel();
    private PasswordTablePanel passwordTablePanel = new PasswordTablePanel();

    public PasswordManagerPanel() {
        ButtonSetup();
        this.setLayout(new BorderLayout());

        this.add(buttonsPanel, BorderLayout.SOUTH);
        this.add(passwordTablePanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == returnButton)
            appManager.showPanel(PanelsNames.MainMenuPanel);
        else if (e.getSource() == addButton){
            HandleAddButton();
        }
        else if (e.getSource() == passwordsWiper){
            appManager.clearManager();
        }
        else if (e.getSource() == saveToFileButton){
            handleSaveToFile();
        }
        else if (e.getSource() == readFileButton){
            appManager.readDataFromEncryptedFile();
        }
        else if (e.getSource() == sortingOptions){
            handleSorting();
        }
    }

    private void handleSorting(){
        passwordTablePanel.ClearTable();

        List<TableRowPanel> userData = appManager.getUserData();
        if (userData.isEmpty())
            return;

        if (sortingOptions.getSelectedItem() == SortingNames.sortingOption1)
        {
            for (int i = 0 ; i <= userData.size() - 1; i++){
                passwordTablePanel.AddRow(userData.get(i));
            }
        }
        else if (sortingOptions.getSelectedItem() == SortingNames.sortingOption2)
        {
            for (int i = userData.size() - 1; i >= 0; i--){
                passwordTablePanel.AddRow(userData.get(i));
            }
        }
        this.revalidate();
        this.repaint();
    }
    private void ButtonSetup(){
        String[] sortingMethods = {SortingNames.sortingOption1, SortingNames.sortingOption2};
        sortingOptions = new JComboBox<>(sortingMethods);
        sortingOptions.setPreferredSize(new Dimension(150, 30));
        sortingOptions.setMaximumSize(new Dimension(150, 30));
        sortingOptions.setMinimumSize(new Dimension(150, 30));


        returnButton.addActionListener(this);
        passwordsWiper.addActionListener(this);
        addButton.addActionListener(this);;
        saveToFileButton.addActionListener(this);
        readFileButton.addActionListener(this);
        sortingOptions.addActionListener(this);


        JPanel sortingPanel = new JPanel();
        GridBagConstraints c = new GridBagConstraints();
        sortingPanel.setOpaque(false);

        sortingPanel.setLayout(new BoxLayout(sortingPanel, BoxLayout.Y_AXIS));
        sortingPanel.add(sortingTextField);
        sortingPanel.add(Box.createVerticalStrut(5));
        sortingPanel.add(sortingOptions);
        sortingPanel.add(Box.createVerticalStrut(10));

        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.setOpaque(false);

        buttonsPanel.add(returnButton);
        buttonsPanel.add(Box.createHorizontalStrut(25));
        buttonsPanel.add(passwordsWiper);
        buttonsPanel.add(Box.createHorizontalStrut(25));
        buttonsPanel.add(addButton);
        buttonsPanel.add(Box.createHorizontalStrut(25));
        buttonsPanel.add(sortingPanel);
        buttonsPanel.add(Box.createHorizontalStrut(25));
        buttonsPanel.add(saveToFileButton);
        buttonsPanel.add(Box.createHorizontalStrut(25));
        buttonsPanel.add(readFileButton);
        buttonsPanel.add(Box.createHorizontalGlue());
    }
    private void handleSaveToFile() {
        MessageToUser message = new MessageToUser("Podaj hasło którym plik będzie szyfrowany (będzie potrzebne do odczytu)");
        JTextField passwordTextField = new JTextField(25);
        JPanel textFieldCenterPanel = new JPanel(new FlowLayout());
        textFieldCenterPanel.add(passwordTextField);

        JPanel popUpWindowsPanel = new JPanel();
        popUpWindowsPanel.setLayout(new BoxLayout(popUpWindowsPanel, BoxLayout.Y_AXIS));

        popUpWindowsPanel.add(Box.createVerticalGlue());
        popUpWindowsPanel.add(Box.createVerticalStrut(25));
        popUpWindowsPanel.add(message);
        popUpWindowsPanel.add(Box.createVerticalStrut(25));
        popUpWindowsPanel.add(textFieldCenterPanel);
        popUpWindowsPanel.add(Box.createVerticalGlue());

        int result = JOptionPane.showOptionDialog(
                this, popUpWindowsPanel, "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null );


        if (result == 0){
            if (passwordTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Hasło jest obowiązkowe!!", "Zapis Pliku", JOptionPane.WARNING_MESSAGE);
                return;
            }

            appManager.saveDataToEncryptedFile(passwordTextField.getText());
        }
    }
    private String GenerateRandomPassword(int charactersNumber){
        String randomPassword ="";
        Random random = new Random();

        while (charactersNumber > 0){
            char c = (char)(random.nextInt(93 + 1) + 33);
            randomPassword += c;
            charactersNumber--;
        }
        return randomPassword;
    }
    private void HandleAddButton(){
        DataEnterPanel userData = new DataEnterPanel();
        JPanel popUpWindowsPanel = new JPanel();
        MessageToUser messageToUser = new MessageToUser("Jeśli chcesz wygenerować losowe hasło, zaznacz pole wyboru \"ptaszkiem\" oraz wybierz liczbę znaków");
        popUpWindowsPanel.setLayout(new BoxLayout(popUpWindowsPanel, BoxLayout.Y_AXIS));

        popUpWindowsPanel.add(Box.createVerticalStrut(20));
        popUpWindowsPanel.add(messageToUser);
        popUpWindowsPanel.add(Box.createVerticalStrut(20));

        userData.add(checkBox);
        userData.add(Box.createHorizontalStrut(25));
        userData.add(numeberOfCharacters);
        userData.add(Box.createHorizontalStrut(25));
        popUpWindowsPanel.add(userData);

        checkBox.setSelected(false);
        numeberOfCharacters.setSelectedIndex(0);

        int result = JOptionPane.showOptionDialog(
                this, popUpWindowsPanel, "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null );

        if (result == 0){

            String site = userData.getSite();
            String login = userData.getLogin();
            String password = userData.getPassword();

            if (password.isEmpty() && !checkBox.isSelected()){
                JOptionPane.showMessageDialog(this, "Hasło nie może być puste!!", "ERROR" ,JOptionPane.WARNING_MESSAGE);
                return;
            }

            if(checkBox.isSelected()) {
                int numberOfCharacters = (Integer) numeberOfCharacters.getSelectedItem();
                password = GenerateRandomPassword(numberOfCharacters);
            }

            PasswordEntry passwordEntry = new PasswordEntry(site, login, password);
            appManager.addRowToTheManager(passwordEntry);
            handleSorting();
        }
    }
    public PasswordTablePanel getPasswordTablePanel() { return passwordTablePanel; }
    private class CheckBox extends JCheckBox{
        private CheckBox(){
            this.setOpaque(false);
            this.setHorizontalAlignment(JCheckBox.CENTER);
            this.setSelected(false);
        }
    }
    private class ComboBox extends JComboBox<Integer>{

        ComboBox(){
            for (int i = 1; i <= 26; i++) {
                this.addItem(i);
            }
        }
    }
    private class SortingTextField extends JTextField{
        public SortingTextField(String textInside) {
            this.setFont(new Font("", Font.BOLD, 14));
            this.setPreferredSize(new Dimension(150, 30));
            this.setMaximumSize(new Dimension(150, 30));
            this.setMinimumSize(new Dimension(150, 30));
            this.setText(textInside);
            this.setForeground(new Color(0xCCCCCC));
            this.setHorizontalAlignment(JTextField.CENTER);
            this.setFocusable(false);
            this.setEditable(false);
            this.setOpaque(false);
            this.setBorder(null);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Image background = image.getImage();
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        int backgroundWidth = background.getWidth(this);
        int backgroundHeight = background.getHeight(this);

        int x = (panelWidth - backgroundWidth) / 2;
        int y = (panelHeight - backgroundHeight) / 2;
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
}
