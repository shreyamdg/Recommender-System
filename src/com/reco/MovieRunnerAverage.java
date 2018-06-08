package com.reco;

public class MovieRunnerAverage {
	
	public void printAverageRatings() {
		SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
		System.out.println("Number of Movies: " + sr.getMovieSize());
		System.out.println("Number of Raters: " + sr.getRaterSize());
	}
	
	public static void main(String[] args) {
		MovieRunnerAverage obj = new MovieRunnerAverage();
		obj.printAverageRatings();
	}
}
