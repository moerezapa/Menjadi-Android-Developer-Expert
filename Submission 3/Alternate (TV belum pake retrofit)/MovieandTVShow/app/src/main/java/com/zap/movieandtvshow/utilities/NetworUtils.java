package com.zap.movieandtvshow.utilities;

import android.util.Log;

import com.zap.movieandtvshow.movie.Movie;
import com.zap.movieandtvshow.tvshow.TV;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class NetworUtils {

    public static ArrayList<Movie> fetchData(String url) throws IOException {
        // url The URL to fetch the HTTP response from.
        ArrayList<Movie> movies = new ArrayList<Movie>();
        try {

            URL new_url = new URL(url); //create a url from a String
            HttpURLConnection connection = (HttpURLConnection) new_url.openConnection(); //Opening a http connection  to the remote object
            connection.connect();

            InputStream inputStream = connection.getInputStream(); //reading from the object
            String results = IOUtils.toString(inputStream);  //IOUtils to convert inputstream objects into Strings type

            parseJsonMovie(results,movies);
            inputStream.close();
        }
            catch (IOException e) {
                e.printStackTrace();
            }

        return movies;
    }
    private static void parseJsonMovie(String data, ArrayList<Movie> movieList) {
        try {
            JSONObject mainObject = new JSONObject(data);

            JSONArray resArray = mainObject.getJSONArray("results"); //Getting the results object
            for (int i = 0; i < resArray.length(); i++) {
                JSONObject jsonObject = resArray.getJSONObject(i);
                Movie movie = new Movie();
                movie.setId(jsonObject.getInt("id"));
                movie.setVoteAverage(jsonObject.getInt("vote_average"));
                movie.setVoteCount(jsonObject.getInt("vote_count"));
                movie.setOriginalTitle(jsonObject.getString("original_title"));
                movie.setTitle(jsonObject.getString("title"));
                movie.setPopularity(jsonObject.getDouble("popularity"));
                movie.setBackdropPath(jsonObject.getString("backdrop_path"));
                movie.setOverview(jsonObject.getString("overview"));
                movie.setReleaseDate(jsonObject.getString("release_date"));
                movie.setPosterPath(jsonObject.getString("poster_path"));

                //Adding a new movie object into ArrayList
                movieList.add(movie);
            }
        }
            catch (JSONException e) {
                e.printStackTrace();
                Log.e(TAG, "Error occurred during JSON Parsing", e);
            }
    }

    public static ArrayList<TV> fetchTVData(String url) throws IOException {
        // url The URL to fetch the HTTP response from.
        ArrayList<TV> tvArrayList = new ArrayList<TV>();
        try {

            URL new_url = new URL(url); //create a url from a String
            HttpURLConnection connection = (HttpURLConnection) new_url.openConnection(); //Opening a http connection  to the remote object
            connection.connect();

            InputStream inputStream = connection.getInputStream(); //reading from the object
            String results = IOUtils.toString(inputStream);  //IOUtils to convert inputstream objects into Strings type

            parseJsonTV(results,tvArrayList);
            inputStream.close();
        }
            catch (IOException e) {
                e.printStackTrace();
            }

        return tvArrayList;
    }

    private static void parseJsonTV(String data, ArrayList<TV> tvArrayList) {
        try {
            JSONObject mainObject = new JSONObject(data);

            JSONArray resArray = mainObject.getJSONArray("results"); //Getting the results object
            for (int i = 0; i < resArray.length(); i++) {
                JSONObject jsonObject = resArray.getJSONObject(i);
                TV tv = new TV();
                tv.setId(jsonObject.getInt("id"));
                tv.setVoteAverage(jsonObject.getInt("vote_average"));
                tv.setVoteCount(jsonObject.getInt("vote_count"));
                tv.setOriginalName(jsonObject.getString("original_name"));
                tv.setName(jsonObject.getString("name"));
                tv.setPopularity(jsonObject.getDouble("popularity"));
                tv.setBackdropPath(jsonObject.getString("backdrop_path"));
                tv.setOverview(jsonObject.getString("overview"));
                tv.setFirstAirDate(jsonObject.getString("first_air_date"));
                tv.setPosterPath(jsonObject.getString("poster_path"));

                //Adding a new movie object into ArrayList
                tvArrayList.add(tv);
            }
        }
            catch (JSONException e) {
                e.printStackTrace();
                Log.e(TAG, "Error occurred during JSON Parsing", e);
            }
    }
}

