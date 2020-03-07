package org.launchcode.mygallery;

import com.mysql.cj.jdbc.Blob;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Artwork extends AbstractEntity {

// To be added with a One to Many relationship with the Artist table later
//    @ManyToOne
//    private Artist artist;
    private Blob artworkImage;
    private String title;
    private String description;
    private String medium;
    private String genre;
    private String size;

    public Artwork(Blob artworkImage, String title, String description, String medium, String genre, String size) {
        this.artworkImage = artworkImage;
        this.title = title;
        this.description = description;
        this.medium = medium;
        this.genre = genre;
        this.size = size;
    }

    public Blob getArtworkImage() {
        return artworkImage;
    }

    public void setArtworkImage(Blob artworkImage) {
        this.artworkImage = artworkImage;
    }

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
}
