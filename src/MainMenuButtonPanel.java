import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuButtonPanel extends JPanel implements ActionListener {

    private AppManager appManager = AppManager.GetInstance();
    private JButton loginButton = new JButton("Zaloguj się");
    private JButton registerButton = new JButton("Zarejestruj się");
    private JButton authorButton = new JButton("Autor Aplikacji");

    public MainMenuButtonPanel(){
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

        loginButton.setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 3),new EmptyBorder(5, 5, 5, 5)));
        authorButton.setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 3),new EmptyBorder(5, 5, 5, 5)));
        registerButton.setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 3),new EmptyBorder(5, 5, 5, 5)));

        loginButton.addActionListener(this);
        authorButton.addActionListener(this);
        registerButton.addActionListener(this);
        
        this.add(Box.createVerticalGlue());
        this.add(loginButton);
        this.add(Box.createVerticalStrut(25));
        this.add(registerButton);
        this.add(Box.createVerticalStrut(25));
        this.add(authorButton);
        this.add(Box.createVerticalGlue());
    }

    // TODO
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            appManager.showPanel(PanelsNames.LoginPanel);
        }
        else if (e.getSource() == registerButton) {
            System.out.println("Kliknięto Zarejestruj się");
        }
        else if (e.getSource() == authorButton) {
            appManager.showPanel(PanelsNames.AuthorPanel);
        }
    }
}
