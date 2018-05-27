package com.reco;

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {

	public ArrayList<Movie> loadMovies(String filename) {
		ArrayList<Movie> list = new ArrayList<Movie>();
		FileResource fr = new FileResource(filename);
		CSVParser parser = fr.getCSVParser();
		for (CSVRecord rec : parser) {
			Movie m = new Movie(rec.get("id"), rec.get("title"), rec.get("year"), rec.get("genre"), rec.get("director"),
					rec.get("country"), rec.get("poster"), Integer.parseInt(rec.get("minutes")));
			list.add(m);
		}
		return list;
	}

	public void testLoadMovies() {
		String filename = "data/ratedmovies_short.csv";
		// String filename = "data/ratedmoviesfull.csv";
		ArrayList<Movie> movieList = loadMovies(filename);
		System.out.println("Total number of movies = " + movieList.size());

		// code to determine how many movies include the Comedy genre.
		System.out.println("Movies with Comedy Genre: ");
		for (Movie m : movieList) {
			if (m.getGenres().toLowerCase().contains("comedy")) {
				System.out.println(m.getTitle());
			}
		}

		// code to determine how many movies are greater than 150 minutes in length
		System.out.println("Movies greater than 150 minutes in length: ");
		for (Movie m : movieList) {
			if (m.getMinutes() > 150) {
				System.out.println(m.getTitle());
			}
		}

		// code to determine the maximum number of movies by any director, and who the
		// directors are
		HashMap<String, Integer> listDirectors = new HashMap<String, Integer>();
		for (Movie m : movieList) {
			if (listDirectors.containsKey(m.getDirector())) {
				listDirectors.put(m.getDirector(), listDirectors.get(m.getDirector()) + 1);
			} else {
				listDirectors.put(m.getDirector(), 1);
			}
		}

		int maxDirector = 0;
		String directorName = null;
		for (String s : listDirectors.keySet()) {
			if (listDirectors.get(s) > maxDirector) {
				maxDirector = listDirectors.get(s);
				directorName = s;
			}
		}
		System.out.println("Maximum number of movies by any director = " + maxDirector + " by " + directorName);

	}

	public ArrayList<Rater> loadRaters(String filename) {
		ArrayList<Rater> raters = new ArrayList<Rater>();
		FileResource fr = new FileResource(filename);
		CSVParser parser = fr.getCSVParser();
		int index = 0;
		for (CSVRecord rec : parser) {
			if (index != 0 && raters.get(index - 1).getID().equals(rec.get("rater_id"))) {
				raters.get(index - 1).addRating(rec.get("movie_id"), Integer.parseInt(rec.get("rating")));
			} else {
				Rater rate = new Rater(rec.get("rater_id"));
				rate.addRating(rec.get("movie_id"), Integer.parseInt(rec.get("rating")));
				raters.add(rate);
				index++;
			}
		}
		return raters;
	}

	public void testloadRaters() {
		String filename = "data/ratings_short.csv";
		// String filename = "data/ratedmoviesfull.csv";
		ArrayList<Rater> raterList = loadRaters(filename);
		System.out.println("Total number of raters = " + raterList.size());

		String raterid = "2";
		int numOfRatings = 0;
		// code to find the number of ratings for a particular rater
		for (Rater rater : raterList) {
			if (rater.getID().equals(raterid)) {
				numOfRatings++;
			}
		}
		System.out.println("Total number of ratings for a particular rater = " + numOfRatings);

		// code to find the maximum number of ratings by any rater.
		HashMap<String, Integer> listRaters = new HashMap<String, Integer>();
		for (Rater rater : raterList) {
			if (listRaters.containsKey(rater.getID())) {
				listRaters.put(rater.getID(), listRaters.get(rater.getID()) + 1);
			} else {
				listRaters.put(rater.getID(), 1);
			}
		}

		int maxRater = 0;
		String maxRaterID = null;
		for (String s : listRaters.keySet()) {
			if (listRaters.get(s) > maxRater) {
				maxRater = listRaters.get(s);
				maxRaterID = s;
			}
		}
		System.out.println("Maximum number of ratings by any rater = " + maxRater + " whose ID is " + maxRaterID);
		// code to find the number of ratings a particular movie has
		String movieId = "1798709";
		int numOfMovieRatings = 0;
		for (Rater rater : raterList) {
			for (String movie : rater.getItemsRated()) {
				if (movieId.equals(movie)) {
					numOfMovieRatings++;
				}
			}
		}
		System.out.println("Total number of ratings a particular movie = " + numOfMovieRatings);
		
		//code to determine how many different movies have been rated by all these raters
		ArrayList<String> uniqueMovies = new ArrayList<>();
		for (Rater rater : raterList) {
			for (String movie : rater.getItemsRated()) {
				if (!uniqueMovies.contains(movie)) {
					uniqueMovies.add(movie);
				}
			}
		}
		System.out.println("Total number of different movies have been rated by all these raters = " + uniqueMovies.size());
	}

	public static void main(String[] args) {
		FirstRatings fr = new FirstRatings();
		fr.testLoadMovies();
		fr.testloadRaters();
	}
}
