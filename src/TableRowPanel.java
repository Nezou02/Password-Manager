import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableRowPanel extends JPanel implements ActionListener {

    private RowTextField site = new RowTextField();
    private RowTextField login = new RowTextField();
    private RowTextField password = new RowTextField();
    private JPanel buttonPanel = new JPanel();
    private CustomButton editButton = new CustomButton("Edytuj");
    private CustomButton deleteButton = new CustomButton("Usuń");
    private PasswordCheckBox showPassword = new PasswordCheckBox();
    private PasswordEntry userRowData;
    private AppManager appManager = AppManager.GetInstance();

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
        appManager.addData(this);
    }
    public TableRowPanel(){
        this.setLayout(new GridBagLayout());
        this.setOpaque(false);
        c = new GridBagConstraints();
        c.weightx = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;

        this.setBackground(Color.DARK_GRAY);
        int heightOfRow = 50;

        this.setPreferredSize(new Dimension(0, heightOfRow));
        this.setMinimumSize(new Dimension(0, heightOfRow));
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, heightOfRow));

        InitializeTable();
    }


    private void addRow(PasswordEntry passwordEntry){
        c.insets = new Insets(5, 5, 5, 5);
        userRowData = new PasswordEntry(passwordEntry.getSite(), passwordEntry.getLogin(), passwordEntry.getPassword());


        site.setText(userRowData.getSite());

        c.gridx = 0;
        c.gridy = 0;

        login.setText(userRowData.getLogin());

        password.setText(userRowData.getPassword());
        this.add(site, c);

        c.gridx = 1;
        this.add(login, c);

        c.gridx = 2;
        this.add(password, c);

        c.gridx = 3;
        this.add(buttonPanel);
        handleCheckbox(showPassword);
    }
    public void editRow(PasswordEntry passwordEntry){
        userRowData = new PasswordEntry(passwordEntry.getSite(), passwordEntry.getLogin(), passwordEntry.getPassword());

        site.setText(userRowData.getSite());
        login.setText(userRowData.getLogin());
        password.setText(userRowData.getPassword());
        handleCheckbox(showPassword);
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
    private void handleCheckbox(PasswordCheckBox showPassword){
        if(showPassword.isSelected()){
            int passwordLength = userRowData.getPassword().length();
            String hiddenPassword = "";

            while (passwordLength > 0){
                hiddenPassword += "*";
                passwordLength--;
            }
            password.setText(hiddenPassword);
        }
        else{
            password.setText(userRowData.getPassword());
        }
    }


    private void InitializeTable(){
        c.insets = new Insets(5, 20, 5, 5);
        RowTextField siteHeader = new RowTextField();
        RowTextField loginHeader = new RowTextField();
        RowTextField passwordHeader = new RowTextField();
        passwordHeader.setOpaque(true);


        siteHeader.setHorizontalAlignment(JTextField.LEFT);
        loginHeader.setHorizontalAlignment(JTextField.LEFT);
        passwordHeader.setHorizontalAlignment(JTextField.LEFT);

        siteHeader.setFont(new Font("Arial",Font.PLAIN,30));
        loginHeader.setFont(new Font("Arial",Font.PLAIN,30));
        passwordHeader.setFont(new Font("Arial",Font.PLAIN,30));


        siteHeader.setForeground(Color.WHITE);
        loginHeader.setForeground(Color.WHITE);
        passwordHeader.setForeground(Color.WHITE);

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
        JPanel horizontalAlignment = new JPanel();
        horizontalAlignment.setLayout(new GridLayout(1, 3, 10, 0));
        c.gridx = 3;
        this.add(horizontalAlignment, c);

    }
    private void ButtonSetup(){

        editButton.addActionListener(this);
        deleteButton.addActionListener(this);
        showPassword.addActionListener(this);

        buttonPanel.setLayout(new GridLayout(1, 3, 10, 0));
        buttonPanel.setOpaque(false);

        buttonPanel.add(showPassword);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == editButton){
            handleEditButton();
        }
        else if (e.getSource() == deleteButton){
            appManager.removeData(this);
        }
        else if (e.getSource() == showPassword) {
            handleCheckbox(showPassword);
        }
    }

    private class RowTextField extends JTextField{

        public RowTextField(){
            this.setColumns(25);
            this.setEditable(false);
            int width = 150;
            int height = 40;

            this.setHorizontalAlignment(JTextField.CENTER);
            this.setFont(new Font("Consolas", Font.BOLD, 18));
            this.setPreferredSize(new Dimension(width,height));
            this.setMinimumSize(new Dimension(width,height));
            this.setMaximumSize(new Dimension(width,height));
        }
    }
    private class PasswordCheckBox extends JCheckBox{
        private PasswordCheckBox(){
            this.setOpaque(false);
            this.setHorizontalAlignment(JCheckBox.CENTER);
            this.setSelected(true);
        }
    }

    public PasswordEntry getUserRowData() { return userRowData; }
}
