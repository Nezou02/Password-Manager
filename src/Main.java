import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        AppFrame mainMenu = new AppFrame();
        MainMenuPanel background = new MainMenuPanel();

        MainMenuButtonPanel mainMenuButtonPanel = new MainMenuButtonPanel();

        background.add(mainMenuButtonPanel);
        mainMenu.add(background);
        mainMenu.setSize(800, 600);
        mainMenu.setLocationRelativeTo(null);
        mainMenu.setVisible(true);
        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}