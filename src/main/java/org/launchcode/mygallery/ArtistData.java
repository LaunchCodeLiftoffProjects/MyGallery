package org.launchcode.mygallery;
//written by Jen Buck

//code for searching the Artist data by search term.

import java.util.ArrayList;

public class ArtistData {
    /**
     * Returns the results of searching the Artwork data by field and search term.
     * @param column = attribute field that should be searched(name, social links)
     * @param value = the value of the field to search for
     * @param allArtists = the list of artwork to search
     * @return list of all artwork that matches the search criteria
     */
    public static ArrayList<Artist> findByColumnAndValue(String column, String value, Iterable<Artist> allArtists) {

        ArrayList<Artist> artistResults = new ArrayList<>();// makes a list to hold the search results

        if (value.toLowerCase().equals("all")){
            return (ArrayList<Artist>) allArtists;
        }

        if (column.equals("all")){
            artistResults = findByValue(value, allArtists);
            return artistResults;
        }

        for (Artist artist : allArtists) { //iterates through each artist
            String aValue = getFieldValue(artist, column); //this will call the method I'm creating below...

            if(aValue != null && aValue.toLowerCase().contains(value.toLowerCase())){
                artistResults.add(artist);
            }
        }
        return artistResults;
    }

    public static String getFieldValue(Artist artist, String attributeName) {
        String theValue;
        if (attributeName.equals("name")){
            theValue = artist.getArtistName();
        }else(attributeName.equals("social links")){
            theValue = artist.getSocialLinks();
        }
        return theValue;
    }

    /**Searches all attribute fields for the given term
     * @param value = the value of the field to search for
     * @param allArtists = the list of artists to search
     * @return List of all artists with at least one field containing the value
     */

    public static ArrayList<Artist> findByValue(String value, Iterable<Artist> allArtists) {

        ArrayList<Artist> artistResults = new ArrayList<>();// makes a list to hold the search results

        for (Artist artist : allArtists) {
            if (artist.getArtistName().toLowerCase().contains(value.toLowerCase())) {
                artistResults.add(artist);
            }else if (artist.getSocialLinks().toLowerCase().contains(value.toLowerCase())) {
                artistResults.add(artist);
            }

        }
        return artistResults;
    }

}
