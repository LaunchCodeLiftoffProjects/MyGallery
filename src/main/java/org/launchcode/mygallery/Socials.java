package org.launchcode.mygallery;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Socials extends AbstractEntity{

    @ManyToOne
    private Artist artist;

    private String name;

    public Socials(Artist artist, String name){
        this.artist = artist;
        this.name = name;
    }

    public Socials(){};

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
