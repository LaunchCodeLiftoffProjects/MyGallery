package org.launchcode.mygallery;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


//Written by Kevin Buss

@Entity
public class GeneralUser extends AbstractEntity {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @OneToMany(mappedBy = "connectedUser")
    private final List<Artist> artists = new ArrayList<>();

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

    public List<Artist> getArtists() {
        return artists;
    }
}
