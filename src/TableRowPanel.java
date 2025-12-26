import javax.swing.*;
import java.awt.*;

public class TableRowPanel extends JPanel {

    private RowTextField site = new RowTextField();
    private RowTextField login = new RowTextField();
    private RowTextField password = new RowTextField();
    GridBagConstraints c;

    public TableRowPanel(PasswordEntry passwordEntry){
        this.setLayout(new GridBagLayout());
        this.setOpaque(false);
        c = new GridBagConstraints();
        c.weightx = 1;
        c.fill = GridBagConstraints.NONE;

        this.setBackground(Color.DARK_GRAY);
        int heightOfRow = 100;

        this.setPreferredSize(new Dimension(0, heightOfRow));
        this.setMinimumSize(new Dimension(0, heightOfRow));
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, heightOfRow));

        addRow(passwordEntry);
    }

    public TableRowPanel(){
        this.setLayout(new GridBagLayout());
        this.setOpaque(false);
        c = new GridBagConstraints();
        c.weightx = 1;
        c.fill = GridBagConstraints.NONE;

        this.setBackground(Color.DARK_GRAY);
        int heightOfRow = 50;

        this.setPreferredSize(new Dimension(0, heightOfRow));
        this.setMinimumSize(new Dimension(0, heightOfRow));
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, heightOfRow));

        InitializeTable();
    }

    public void InitializeTable(){
        c.insets = new Insets(5, 5, 10, 5);
        RowTextField siteHeader = new RowTextField();
        RowTextField loginHeader = new RowTextField();
        RowTextField passwordHeader = new RowTextField();

        siteHeader.setHorizontalAlignment(JTextField.CENTER);
        loginHeader.setHorizontalAlignment(JTextField.CENTER);
        passwordHeader.setHorizontalAlignment(JTextField.CENTER);

        siteHeader.setFont(new Font("Consolas",Font.PLAIN,35));
        loginHeader.setFont(new Font("Consolas",Font.PLAIN,35));
        passwordHeader.setFont(new Font("Consolas",Font.PLAIN,35));

        siteHeader.setForeground(Color.GREEN);
        loginHeader.setForeground(Color.GREEN);
        passwordHeader.setForeground(Color.GREEN);

        siteHeader.setBorder(BorderFactory.createEmptyBorder());
        loginHeader.setBorder(BorderFactory.createEmptyBorder());
        passwordHeader.setBorder(BorderFactory.createEmptyBorder());

        siteHeader.setOpaque(false);
        loginHeader.setOpaque(false);
        passwordHeader.setOpaque(false);

        siteHeader.setText("NAZWA STRONY");
        loginHeader.setText("LOGIN");
        passwordHeader.setText("HASŁO");

        c.gridx = 0;
        c.gridy = 0;

        this.add(siteHeader, c);

        c.gridx = 1;
        this.add(loginHeader, c);

        c.gridx = 2;
        this.add(passwordHeader, c);

    }
    private void addRow(PasswordEntry passwordEntry){
        c.insets = new Insets(5, 5, 5, 5);
        site.setText(passwordEntry.getSite());

        c.gridx = 0;
        c.gridy = 0;

        login.setText(passwordEntry.getLogin());

        password.setText(passwordEntry.getPassword());
        this.add(site, c);

        c.gridx = 1;
        this.add(login, c);

        c.gridx = 2;
        this.add(password, c);
    }
    public void editRow(PasswordEntry passwordEntry){
        site.setText(passwordEntry.getSite());
        login.setText(passwordEntry.getLogin());
        password.setText(passwordEntry.getPassword());
    }

    private class RowTextField extends JTextField{

        public RowTextField(){
            this.setColumns(25);
            this.setEditable(false);
            this.setPreferredSize(new Dimension(100,40));
            this.setMinimumSize(new Dimension(100,40));
            this.setMaximumSize(new Dimension(100,40));
        }
    }
}
