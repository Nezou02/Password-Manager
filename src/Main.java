import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame mainMenu = new JFrame();
        MainMenuPanel background = new MainMenuPanel();

        mainMenu.add(background);
        mainMenu.setSize(800, 600);
        mainMenu.setLocationRelativeTo(null);
        mainMenu.setVisible(true);
        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}