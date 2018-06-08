package com.reco;
/**
 * Write a description of SecondRatings here.
 * 
 * @author Shreyam
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }

	public SecondRatings(String moviefile, String ratingsfile) {
		FirstRatings frObject = new FirstRatings();
		myMovies = frObject.loadMovies(moviefile);
		myRaters = frObject.loadRaters(ratingsfile);
	}
	
	public int getMovieSize() {
		return myMovies.size();
	}
	
	public int getRaterSize() {
		return myRaters.size();
	}
    
}
