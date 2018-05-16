package com.reco;
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
public class FirstRatings {

	public ArrayList<Movie> loadMovies(String filename){
		ArrayList<Movie> list = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord rec: parser) {
            Movie m = new Movie(rec.get("id"), rec.get("title"), rec.get("year"), rec.get("genre"), rec.get("director"),rec.get("country"), rec.get("poster"), Integer.parseInt(rec.get("minutes")));
            list.add(m);
        }
        return list;
	}
	
	public void testLoadMovies(){
        //String filename = "data/ratedmovies_short.csv";
        String filename = "data/ratedmoviesfull.csv";
		ArrayList<Movie> movieList = loadMovies(filename);
        System.out.println("Total number of movies = " + movieList.size());
        /*for(Movie m : movieList){
            System.out.println(m.toString());
        }*/
       /*HashMap<String, Integer> map = new HashMap<String, Integer>();
       int genreCount = 0;
       int minCount = 0;
       int maxDirector = 0;
       String directorName = " ";
       for(Movie m : movieList){
           if(m.getGenres ().contains("Comedy")) 
           genreCount++;   
           if(m.getMinutes() > 150)
           minCount++;
           
           if(map.containsKey(m.getDirector()))
				map.put(m.getDirector(), map.get(m.getDirector()) + 1);
			else
				map.put(m.getDirector(), 1);
           
       }
       System.out.println("Total number of movies with Genre comedy = " + genreCount);
       System.out.println("Total number of movies with length greater than 150mins = " + minCount);
       for(String s : map.keySet()){
           if(map.get(s) > maxDirector){
                maxDirector = map.get(s);
                directorName = s;
            }
        }
       System.out.println("Maximum number of movies by any director = " + maxDirector + " by " + directorName);*/    
    }
	
	public static void main(String [] args) {
		FirstRatings fr =  new FirstRatings();
		fr.testLoadMovies();
	}
}
