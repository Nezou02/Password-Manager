import javax.swing.*;

public class DataEnterPanel extends JPanel {
    private JTextField site = new JTextField(25);
    private JTextField login = new JTextField(25);
    private JTextField password = new JTextField(25);

    public DataEnterPanel(){
        this.setSize(800, 800);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        TextFieldSetup();
    }

    private void TextFieldSetup(){
        site.setText("nazwa strony");
        login.setText("login");
        password.setText("haslo");

        this.add(Box.createHorizontalGlue());
        this.add(Box.createHorizontalStrut(25));
        this.add(site);
        this.add(Box.createHorizontalStrut(25));
        this.add(login);
        this.add(Box.createHorizontalStrut(25));
        this.add(password);
        this.add(Box.createHorizontalGlue());
    }
    public String getSite() { return site.getText(); }
    public String getLogin() { return login.getText(); }
    public String getPassword() { return password.getText(); }
}