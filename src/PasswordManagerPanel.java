import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordManagerPanel extends JPanel implements ActionListener {

    ImageIcon image = new ImageIcon(Main.class.getResource("/ManagerBackground.png"));
    CustomButton returnButton = new CustomButton("Powrót");
    CustomButton passwordsWiper = new CustomButton("Wyczyść Dane");
    AppManager appManager = AppManager.GetInstance();
    JPanel buttonsPanel = new JPanel();

    PasswordManagerPanel() {
        ButtonSetup();
        this.setLayout(new BorderLayout());

        this.add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void ButtonSetup(){
        returnButton.addActionListener(this);
        passwordsWiper.addActionListener(this);

        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.setOpaque(false);

        buttonsPanel.add(returnButton);
        buttonsPanel.add(Box.createHorizontalStrut(25));
        buttonsPanel.add(passwordsWiper);
        buttonsPanel.add(Box.createHorizontalGlue());
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == returnButton)
            appManager.showPanel(PanelsNames.MainMenuPanel);
    }
}
