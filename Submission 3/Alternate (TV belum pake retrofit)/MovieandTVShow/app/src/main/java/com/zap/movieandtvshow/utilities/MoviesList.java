package com.zap.movieandtvshow.utilities;

import com.zap.movieandtvshow.model.Movies;
import com.zap.movieandtvshow.movie.MovieItems;

import java.util.ArrayList;

public class MoviesList {

    private int total_pages;

    public int getTotal_pages() {
        return total_pages;
    }

    private ArrayList<Movies> results;

    public ArrayList<Movies> getResults() {
        return results;
    }
}
