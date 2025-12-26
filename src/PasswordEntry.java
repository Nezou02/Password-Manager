public class PasswordEntry {
    private String site;
    private String login;
    private String password;

    public PasswordEntry(String site, String login, String password){
        this.site = site;
        this.login = login;
        this.password = password;
    }

    public String getPassword() { return password; }
    public String getLogin() { return login; }
    public String getSite() { return site; }
}
