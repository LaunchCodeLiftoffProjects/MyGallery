package org.launchcode.mygallery;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotNull;


//Written by Kevin Buss


public class GeneralUser extends AbstractEntity {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @NotNull
    private String username;

    @NotNull
    private String pwHash;

    @NotNull
    private String role;

    public GeneralUser() {}

    public GeneralUser(String username, String password, String role) {
        this.username = username;
        this.pwHash = encoder.encode(password);
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

}
