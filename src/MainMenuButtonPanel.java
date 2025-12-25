import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuButtonPanel extends JPanel implements ActionListener {

    private AppManager appManager = AppManager.GetInstance();
    private JButton enterButton = new JButton("Zacznij Korzystać");
    private JButton authorButton = new JButton("Autor Aplikacji");

    public MainMenuButtonPanel(){
        this.setOpaque(false);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        ButtonsSetup();
    }

    private void ButtonsSetup(){
        
        enterButton.setFont(new Font("", Font.BOLD, 18));
        authorButton.setFont(new Font("", Font.BOLD, 18));

        enterButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        authorButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

        enterButton.setFocusPainted(false);
        authorButton.setFocusPainted(false);

        enterButton.setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 3),new EmptyBorder(5, 5, 5, 5)));
        authorButton.setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 3),new EmptyBorder(5, 5, 5, 5)));

        enterButton.addActionListener(this);
        authorButton.addActionListener(this);
        
        this.add(Box.createVerticalGlue());
        this.add(enterButton);
        this.add(Box.createVerticalStrut(25));
        this.add(authorButton);
        this.add(Box.createVerticalGlue());
    }

    // TODO
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enterButton) {
            appManager.showPanel(PanelsNames.PasswordManagerPanel);
        }
        else if (e.getSource() == authorButton) {
            appManager.showPanel(PanelsNames.AuthorPanel);
        }
    }
}
