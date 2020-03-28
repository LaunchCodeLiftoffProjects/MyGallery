package org.launchcode.mygallery;

import com.mysql.cj.jdbc.Blob;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

//Coded by Kevin Buss

@Entity
public class Artwork extends AbstractEntity {

    @ManyToOne
    @NotNull(message = "Artist is required")
    private Artist artist;

    private String title;
    private String description;
    private String medium;
    private String genre;
    private String size;
    private String artLink;

    public Artwork(Artist artist, String title, String description, String medium, String genre, String size, String artLink) {
        this.artist=artist;
        this.title = title;
        this.description = description;
        this.medium = medium;
        this.genre = genre;
        this.size = size;
        this.artLink = artLink;
    }

    public Artwork() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getArtLink() {
        return artLink;
    }

    public void setArtLink(String artLink) {
        this.artLink = artLink;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
