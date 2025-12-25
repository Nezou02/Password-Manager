import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuButtonPanel extends JPanel implements ActionListener {
    JButton loginButton = new JButton("Zaloguj się");
    JButton registerButton = new JButton("Zarejestruj się");
    JButton authorButton = new JButton("Autor Aplikacji");

    MainMenuButtonPanel(){
        this.setOpaque(false);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        ButtonsSetup();
    }

    private void ButtonsSetup(){
        
        loginButton.setFont(new Font("", Font.BOLD, 18));
        authorButton.setFont(new Font("", Font.BOLD, 18));
        registerButton.setFont(new Font("", Font.BOLD, 18));

        loginButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        authorButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        registerButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

        loginButton.setFocusPainted(false);
        authorButton.setFocusPainted(false);
        registerButton.setFocusPainted(false);
        
        this.add(Box.createVerticalGlue());
        this.add(loginButton);
        this.add(Box.createVerticalStrut(25));
        this.add(registerButton);
        this.add(Box.createVerticalStrut(25));
        this.add(authorButton);
        this.add(Box.createVerticalGlue());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            System.out.println("Kliknięto Zaloguj się");
        }
        else if (e.getSource() == registerButton) {
            System.out.println("Kliknięto Zarejestruj się");
        }
        else if (e.getSource() == authorButton) {
            System.out.println("Kliknięto Autor Aplikacji");
        }
    }
}
