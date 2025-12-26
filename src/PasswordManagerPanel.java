import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//glowne ui od managera
public class PasswordManagerPanel extends JPanel implements ActionListener {

    private ImageIcon image = new ImageIcon(Main.class.getResource("/ManagerBackground.png"));
    private CustomButton returnButton = new CustomButton("Powrót");
    private CustomButton passwordsWiper = new CustomButton("Wyczyść Dane");
    private CustomButton addButton = new CustomButton("Dodaj Dane");
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

    private void ButtonSetup(){
        returnButton.addActionListener(this);
        passwordsWiper.addActionListener(this);
        addButton.addActionListener(this);

        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.setOpaque(false);

        buttonsPanel.add(returnButton);
        buttonsPanel.add(Box.createHorizontalStrut(25));
        buttonsPanel.add(passwordsWiper);
        buttonsPanel.add(Box.createHorizontalStrut(25));
        buttonsPanel.add(addButton);
        buttonsPanel.add(Box.createHorizontalGlue());
    }


    public PasswordTablePanel getPasswordTablePanel() { return passwordTablePanel; }

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

        int result = JOptionPane.showOptionDialog(
                this, popUpWindowsPanel, "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null );

        if (result == 0){
            String site = userData.getSite();
            String login = userData.getLogin();
            String password = userData.getPassword();

            PasswordEntry passwordEntry = new PasswordEntry(site, login, password);
            appManager.addRowToTheManager(passwordEntry);
        }
    }
    private class MessageToUser extends JTextField{

        MessageToUser(String message){
            this.setText(message);
            this.setOpaque(false);
            this.setEditable(false);
            this.setFocusable(false);
            this.setFont(new Font("Ariel", Font.BOLD, 18));
            this.setBorder(BorderFactory.createEmptyBorder());
            this.setHorizontalAlignment(JTextField.CENTER);
        }
    }
    private class CheckBox extends JCheckBox{
        private CheckBox(){
            this.setOpaque(false);
            this.setHorizontalAlignment(JCheckBox.CENTER);
            this.setSelected(false);
        }
    }
    private class ComboBox extends JComboBox<Integer>{

        ComboBox(){
            for (int i = 0; i <= 25; i++) {
                this.addItem(i);
            }
        }
    }
}
