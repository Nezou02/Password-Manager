import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame mainMenu = new JFrame();
        MainMenuPanel background = new MainMenuPanel();
        mainMenu.setTitle("Menadżer Haseł");
        ImageIcon appIcon = new ImageIcon(Main.class.getResource("/AppIconTitle.png"));
        mainMenu.setIconImage(appIcon.getImage());

        JPanel test = new JPanel();
        test.setOpaque(false);
        test.setLayout(new BoxLayout(test, BoxLayout.Y_AXIS));
        JButton button1 = new JButton("cos1");
        JButton button2 = new JButton("cos2");
        JButton button3 = new JButton("cos3");
        button1.setAlignmentX(JButton.CENTER_ALIGNMENT);
        button2.setAlignmentX(JButton.CENTER_ALIGNMENT);
        button3.setAlignmentX(JButton.CENTER_ALIGNMENT);



        test.add(Box.createVerticalGlue());
        test.add(button1);
        test.add(Box.createVerticalStrut(25));
        test.add(button2);
        test.add(Box.createVerticalStrut(25));
        test.add(button3);
        test.add(Box.createVerticalGlue());
        background.add(test, BorderLayout.CENTER);

        mainMenu.add(background);
        mainMenu.setSize(800, 600);
        mainMenu.setLocationRelativeTo(null);
        mainMenu.setVisible(true);
        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}