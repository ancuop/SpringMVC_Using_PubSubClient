package com.example.simple_resful.form;

import com.example.simple_resful.constraint.FieldMatch;
import org.hibernate.validator.constraints.NotEmpty;

@FieldMatch.List({
        @FieldMatch(first = "username", second = "confirmUsername", message = "The username field must match"),
        @FieldMatch(first = "password", second = "confirmPassword", message = "The password field must match")
})
public class AccountForm {

    @NotEmpty
    private String username;
    @NotEmpty
    private String confirmUsername;
    @NotEmpty
    private String password;
    @NotEmpty
    private String confirmPassword;
    // ROLE USER: all person who login page is role person
    // ROLE ADMIN: only one, who has privilege to control, monitoring, shutdown app
    // ROLE ACTUATOR: manage actuator
    private String role = "ROLE_ADMIN"; // default who register will be admin

    public void setConfirmUsername(String confirmUsername) {
        this.confirmUsername = confirmUsername;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getConfirmUsername() {
        return confirmUsername;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}