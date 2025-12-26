import javax.swing.*;
import java.awt.*;

public class TableRowPanel extends JPanel {

    private JTextField site = new JTextField(20);
    private JTextField login = new JTextField(20);
    private JTextField password = new JTextField(20);

    public TableRowPanel(PasswordEntry passwordEntry){
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 0));
        this.setBackground(Color.DARK_GRAY);
        int heightOfRow = 100;

        this.setPreferredSize(new Dimension(0, heightOfRow));
        this.setMinimumSize(new Dimension(0, heightOfRow));
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, heightOfRow));

        addRow(passwordEntry);
    }

    private void addRow(PasswordEntry passwordEntry){
        site.setText(passwordEntry.getSite());
        site.setEditable(false);
        site.setPreferredSize(new Dimension(250,40));

        login.setText(passwordEntry.getLogin());
        login.setEditable(false);
        login.setPreferredSize(new Dimension(250,40));

        password.setText(passwordEntry.getPassword());
        password.setEditable(false);
        password.setPreferredSize(new Dimension(250,40));

        this.add(site);
        this.add(login);
        this.add(password);
    }
    public void editRow(PasswordEntry passwordEntry){
        site.setText(passwordEntry.getSite());
        login.setText(passwordEntry.getLogin());
        password.setText(passwordEntry.getPassword());
    }

}
