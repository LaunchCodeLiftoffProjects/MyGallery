package org.launchcode.mygallery;


import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Artist extends AbstractEntity {

    private String artistName;
    private String artistInfo;
    private String socialLinks;

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
}


