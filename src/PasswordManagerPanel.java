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
            PasswordEntry passwordEntry = new PasswordEntry("site", "login", "haslo");
            appManager.addRowToTheManager(passwordEntry);
        }

    }
}
