package org.launchcode.mygallery;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class ArtistUser extends AbstractEntity {

    //TO BE DELETED

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @NotNull
    private String username;

    @NotNull
    private String pwHash;

    @NotNull
    private String role;

    public ArtistUser () {}

    public ArtistUser(String username, String password, String role) {
        this.username = username;
        this.pwHash = encoder.encode(password);
        this.role = role;
    }
    public ArtistUser(String username, String password) { super();
    }

    public String getUsername() {
        return username;
    }
    public String getRole(){
        return role;
    }
    public Boolean isMatchingPassword(String password) {return encoder.matches(password, pwHash);
    }

}
