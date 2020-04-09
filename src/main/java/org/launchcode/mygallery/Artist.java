package org.launchcode.mygallery;


import org.launchcode.mygallery.data.ArtistRepository;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Artist extends AbstractEntity {

    private String artistName;
    private String artistInfo;
    private String socialLinks;

    @OneToMany(mappedBy = "artist")
    private final List<Artwork> artwork = new ArrayList<>();

    @OneToMany(mappedBy = "artist")
    private final List<Socials> socials = new ArrayList<>();

    @ManyToOne
    private GeneralUser connectedUser;

    public Artist(String artistName, String artistInfo, String socialLinks) {
        this.artistName = artistName;
        this.artistInfo = artistInfo;
        this.socialLinks = socialLinks;
    }

    public Artist(){}

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistInfo() {
        return artistInfo;
    }

    public void setArtistInfo(String artistInfo) {
        this.artistInfo = artistInfo;
    }

    public String getSocialLinks() {
        return socialLinks;
    }

    public void setSocialLinks(String socialLinks) {
        this.socialLinks = socialLinks;
    }

    public List<Artwork> getArtwork() {
        return artwork;
    }

    public GeneralUser getConnectedUser() {
        return connectedUser;
    }

    public void setConnectedUser(GeneralUser connectedUser) {
        this.connectedUser = connectedUser;
    }

    public List<Socials> getSocials() {
        return socials;
    }
}


