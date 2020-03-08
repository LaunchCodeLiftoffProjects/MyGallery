package org.launchcode.mygallery;


import java.util.ArrayList;
import java.util.List;

public class Artist extends AbstractEntity {

    private String artistName;
    private String artistInfo;
    private List<String> socialLinks = new ArrayList<>();

    public Artist(String artistName, String artistInfo, List<String> socialLinks) {
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

    public List<String> getSocialLinks() {
        return socialLinks;
    }

    public void setSocialLinks(List<String> socialLinks) {
        this.socialLinks = socialLinks;
    }
}


