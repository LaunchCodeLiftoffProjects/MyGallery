package org.launchcode.mygallery;


import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Artist extends AbstractEntity {

    private Integer artistUserId;
    private String artistName;
    private String artistInfo;
    @OneToMany
    private List<Socials> socials = new ArrayList<>();


    public Artist(String artistName, String artistInfo, ArrayList socials, Integer artistUserId) {
        this.artistName = artistName;
        this.artistInfo = artistInfo;
        this.socials = socials;
        this.artistUserId = artistUserId;
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

    public Integer getArtistUserId() {
        return artistUserId;
    }

    public void setArtistUserId(Integer artistUserId) {
        this.artistUserId = artistUserId;
    }

    public List<Socials> getSocials() {
        return socials;
    }

    public void setSocials(List<Socials> socials) {
        this.socials = socials;
    }
}


