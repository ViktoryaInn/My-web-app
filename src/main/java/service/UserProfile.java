package service;

public class UserProfile {

    private final String email;
    private final String login;
    private final String password;

    public UserProfile(String email, String login, String password){
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
