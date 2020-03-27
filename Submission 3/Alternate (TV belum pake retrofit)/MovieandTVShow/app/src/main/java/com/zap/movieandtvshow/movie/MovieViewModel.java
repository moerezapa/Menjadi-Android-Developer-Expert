package com.zap.movieandtvshow.movie;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.zap.movieandtvshow.MainActivity;
import com.zap.movieandtvshow.model.Movies;
import com.zap.movieandtvshow.model.Response;
import com.zap.movieandtvshow.utilities.API;
import com.zap.movieandtvshow.utilities.MoviesList;
import com.zap.movieandtvshow.utilities.RetrofitClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import retrofit2.Call;
import retrofit2.Callback;

import static android.support.constraint.Constraints.TAG;

/*
    NOTE
    Dengan menambahkan turunan kelas ViewModel ke kelas MainViewModel menandakan bahwa
    kelas tersebut sebagai kelas ViewModel.

    Segala sesuatu yang ada di kelas tersebut akan terjaga selama Activity masih dalam keadaan aktif.
    Pada kelas MainViewModel, listWeathres akan selalu dipertahankan selama kelas tersebut
    masih terikat dengan Activity.
 */

public class MovieViewModel extends ViewModel {
    private ArrayList<Movies> listMovie = new ArrayList<>();
    private MutableLiveData<ArrayList<Movies>> listMovies1 = new MutableLiveData<>();
    private MutableLiveData<ArrayList<MovieItems>> listMovies = new MutableLiveData<>();
    API api;
    void setListMovies() {
        api = RetrofitClient.getClient().create(API.class);
        api.getMovies(MainActivity.myAPI)
                .enqueue(new Callback<MoviesList>() {
                    @Override
                    public void onResponse(Call<MoviesList> call, retrofit2.Response<MoviesList> response) {
                        listMovie = response.body().getResults();
                        Log.e(TAG, "Hasil List Movie: " + listMovie);
                        listMovies1.postValue(listMovie);
                    }

                    @Override
                    public void onFailure(Call<MoviesList> call, Throwable t) {

                    }
                });
        /*api.getMovieList(MainActivity.myAPI)
                .enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        //listMovie = (ArrayList<Movies>) response.body().getResults();
                        if (response.code() == 200) {
                            Log.e(TAG, "Success");
                            //Log.e(TAG, "Hasil List Movie: " + response.body());
                        }
                            else
                            Log.e(TAG, "Failed");
                        //listMovies1.postValue(listMovie);
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {

                    }
                });*/
    }
    void setMovie() {
        // request API
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<MovieItems> listItems = new ArrayList<>();
        String urlmovie = "https://api.themoviedb.org/3/discover/movie?api_key=" + MainActivity.myAPI + "&language=en-US";
        client.get(urlmovie, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject movie = list.getJSONObject(i);
                        MovieItems movieItems = new MovieItems(movie);
                        listItems.add(movieItems);
                    }

                    Log.e(TAG, "Hasil: " + listItems);
                    listMovies.postValue(listItems);
                }
                    catch (Exception e) {
                        Log.d("Exception", e.getMessage());
                    }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    //LiveData<ArrayList<MovieItems>> getMovie() { return listMovies; }
    LiveData<ArrayList<Movies>> getMovies() { return listMovies1; }
}
