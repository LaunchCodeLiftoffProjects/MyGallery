package org.launchcode.mygallery;
//written by Jen Buck

//code for searching the Artwork data by search term.

import java.util.ArrayList;

public class ArtworkData {
    /**
     * Returns the results of searching the Artwork data by field and search term.
     * @param column = attribute field that should be searched(description, medium, genre, size)
     * @param value = the value of the field to search for
     * @param allArtworks = the list of artwork to search
     * @return list of all artwork that matches the search criteria
     */
    public static ArrayList<Artwork> findByColumnAndValue(String column, String value, Iterable<Artwork> allArtworks) {

        ArrayList<Artwork> artworkResults = new ArrayList<>();// makes a list to hold the search results

        if (value.toLowerCase().equals("all")){
            return (ArrayList<Artwork>) allArtworks;
        }

        if (column.equals("all")){
            artworkResults = findByValue(value, allArtworks);
            return artworkResults;
        }

        for (Artwork artwork : allArtworks) { //iterates through each artwork
            String aValue = getFieldValue(artwork, column); //this will call the method I'm creating below...

            if(aValue != null && aValue.toLowerCase().contains(value.toLowerCase())){
                artworkResults.add(artwork);
            }
        }
        return artworkResults;
    }

    public static String getFieldValue(Artwork artwork, String attributeName) {
        String theValue;
        if(attributeName.equals("title")){
            theValue = artwork.getTitle();
        }else if(attributeName.equals("description")){
            theValue = artwork.getDescription();
        } else if (attributeName.equals("medium")){
            theValue = artwork.getMedium();
        } else if (attributeName.equals("genre")){
            theValue = artwork.getGenre();
        } else {
            theValue = artwork.getSize();
        }
        return theValue;
    }

    /**Searches all attribute fields for the given term
     * @param value = the value of the field to search for
     * @param allArtworks = the list of artwork to search
     * @return List of all artwork with at least one field containing the value
     */

    public static ArrayList<Artwork> findByValue(String value, Iterable<Artwork> allArtworks) {

        ArrayList<Artwork> artworkResults = new ArrayList<>();// makes a list to hold the search results

        for (Artwork artwork : allArtworks) {
            if(artwork.getTitle().toLowerCase().contains(value.toLowerCase())){
                artworkResults.add(artwork);
            }else if (artwork.getDescription().toLowerCase().contains(value.toLowerCase())) {
                artworkResults.add(artwork);
            }else if (artwork.getMedium().toLowerCase().contains(value.toLowerCase())) {
                artworkResults.add(artwork);
            }else if (artwork.getGenre().toLowerCase().contains(value.toLowerCase())) {
                artworkResults.add(artwork);
            }else if (artwork.getSize().toLowerCase().contains(value.toLowerCase())) {
                artworkResults.add(artwork);
            }

        }
        return artworkResults;
    }

}
