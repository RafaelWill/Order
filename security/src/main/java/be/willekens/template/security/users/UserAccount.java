package be.willekens.template.security.users;

import java.util.List;

public class UserAccount {

    private String username;
    private String password;
    private List<String> roles;

    public static UserAccount userAccount(){
        return new UserAccount();
    }

    public UserAccount withUsername(String username) {
        this.username = username;
        return this;
    }

    public UserAccount withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserAccount withRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getRoles() {
        return roles;
    }
}
