import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableRowPanel extends JPanel implements ActionListener {

    private RowTextField site = new RowTextField();
    private RowTextField login = new RowTextField();
    private RowTextField password = new RowTextField();
    private JPanel buttonPanel = new JPanel();
    CustomButton editButton = new CustomButton("Edytuj");
    CustomButton deleteButton = new CustomButton("Usuń");

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
    private void handleEditButton(){
        DataEnterPanel userData = new DataEnterPanel();
        int result = JOptionPane.showOptionDialog(
                this, userData, "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null );

        if (result == 0){
            String site = userData.getSite();
            String login = userData.getLogin();
            String password = userData.getPassword();

            PasswordEntry passwordEntry = new PasswordEntry(site, login, password);
            editRow(passwordEntry);
        }
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
    private void ButtonSetup(){
        editButton.addActionListener(this);
        deleteButton.addActionListener(this);

        buttonPanel.setLayout(new GridLayout(1, 2, 10, 0));
        buttonPanel.setOpaque(false);

        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == editButton){
            handleEditButton();
        }
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
}
