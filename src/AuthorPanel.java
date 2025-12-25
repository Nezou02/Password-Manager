import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthorPanel extends JPanel implements ActionListener {

    private AppManager appManager = AppManager.GetInstance();
    private ImageIcon image = new ImageIcon(Main.class.getResource("/AuthorBackground.png"));
    private JLabel authorLabel = new JLabel();
    private JButton returnButton = new JButton("Powrót");

    public AuthorPanel(){
        LabelSetup();
        ButtonSetup();
        this.setBackground(new Color(0x123456));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(Box.createVerticalGlue());
        this.add(authorLabel);
        this.add(Box.createVerticalStrut(25));
        this.add(returnButton);
        this.add(Box.createVerticalGlue());
    }

    private void LabelSetup(){
        String authorTextToDisplay = "Autorem tej wspaniałej aplikacji jest Wielki Programista Kamil Przęczek";
        authorLabel.setText(authorTextToDisplay);
        authorLabel.setFont(new Font("Comic Sans",Font.BOLD,25));
        authorLabel.setForeground(Color.WHITE);
        authorLabel.setAlignmentX(JButton.CENTER_ALIGNMENT);
        authorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        authorLabel.setVerticalAlignment(SwingConstants.CENTER);
    }
    private void ButtonSetup(){
        returnButton.setFont(new Font("", Font.BOLD, 18));
        returnButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        returnButton.setFocusPainted(false);
        returnButton.setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 3),new EmptyBorder(5, 5, 5, 5)));
        returnButton.addActionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // usuwa poprzednie tlo i setuje background kolor

        Image background = image.getImage();
        int panelWidth = getWidth();

        int backgroundWidth = background.getWidth(this);

        int x = (panelWidth - backgroundWidth) / 2;

        g.drawImage(background, x, 0, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == returnButton)
            appManager.showPanel(PanelsNames.MainMenuPanel);

    }
}
