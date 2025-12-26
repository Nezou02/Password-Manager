import javax.swing.*;
import java.awt.*;

public class TableRowPanel extends JPanel {

    private RowTextField site = new RowTextField();
    private RowTextField login = new RowTextField();
    private RowTextField password = new RowTextField();
    private JPanel buttonPanel = new JPanel();

    GridBagConstraints c;

    public TableRowPanel(PasswordEntry passwordEntry){
        this.setLayout(new GridBagLayout());
        this.setOpaque(false);
        c = new GridBagConstraints();
        c.weightx = 1;
        c.fill = GridBagConstraints.NONE;

        this.setBackground(Color.DARK_GRAY);
        int heightOfRow = 100;


        this.setPreferredSize(new Dimension(120, heightOfRow));
        this.setMinimumSize(new Dimension(120, heightOfRow));
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, heightOfRow));

        ButtonSetup();
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

    private void InitializeTable(){
        c.insets = new Insets(5, 7, 10, 7);
        RowTextField siteHeader = new RowTextField();
        RowTextField loginHeader = new RowTextField();
        RowTextField passwordHeader = new RowTextField();

        siteHeader.setHorizontalAlignment(JTextField.LEFT);
        loginHeader.setHorizontalAlignment(JTextField.LEFT);
        passwordHeader.setHorizontalAlignment(JTextField.LEFT);

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

        siteHeader.setText("STRONA");
        loginHeader.setText("LOGIN");
        passwordHeader.setText("HASŁO");

        c.gridx = 0;
        c.gridy = 0;

        this.add(siteHeader, c);

        c.gridx = 1;
        this.add(loginHeader, c);

        c.gridx = 2;
        this.add(passwordHeader, c);

        JPanel horizontalAligment = new JPanel();
        horizontalAligment.setLayout(new GridLayout(1, 2, 10, 0));
        c.gridx = 3;
        this.add(horizontalAligment, c);

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

        c.gridx = 3;
        this.add(buttonPanel);

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
            int width = 150;
            int height = 40;

            this.setPreferredSize(new Dimension(width,height));
            this.setMinimumSize(new Dimension(width,height));
            this.setMaximumSize(new Dimension(width,height));
        }
    }
    private void ButtonSetup(){
        CustomButton edit = new CustomButton("Edytuj");
        CustomButton delete = new CustomButton("Usuń");

        buttonPanel.setLayout(new GridLayout(1, 2, 10, 0));
        buttonPanel.setOpaque(false);

        buttonPanel.add(edit);
        buttonPanel.add(delete);
    }

}
