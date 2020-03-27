package com.zap.movieandtvshow.movie;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zap.movieandtvshow.MainActivity;
import com.zap.movieandtvshow.R;
import com.zap.movieandtvshow.model.Movies;
import com.zap.movieandtvshow.utilities.NetworUtils;

import java.io.IOException;
import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class MovieTabFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private MovieViewModel movieViewModel;
    private MovieAdapter movieAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.movie_tab, container, false);

        // initialize recylerview
        recyclerView = view.findViewById(R.id.recyclerview_film);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity().getApplicationContext()));

        /*
        menyambungkan kelas MainViewModel dengan MainActivity

        Ketika Activity membutuhkan ViewModel, Anda cukup memanggil kelas ViewModelProviders dengan
        input dari .of() adalah context Activity yang dibutuhkan dan
        input .get() adalah kelas ViewModel mana yang akan dihubungkan dengan Activity.
                */
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getMovies().observe(this, getMovie);

        movieAdapter = new MovieAdapter();
        movieAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(movieAdapter);
        movieViewModel.setListMovies();

        return view;
    }

    private Observer<ArrayList<Movies>> getMovie = new Observer<ArrayList<Movies>>() {
        @Override
        public void onChanged(ArrayList<Movies> movieItems) {
            if (movieItems != null) {
                movieAdapter.setData(getContext(), movieItems);
                Log.e(TAG, "Hasil Movie Tab: " + movieItems);
                showLoading(false);
            }
        }
    };

    /*// inisialisasi array
    private void prepare() {
        filmName = getResources().getStringArray(R.array.film_name);
        filmPoster = getResources().obtainTypedArray(R.array.film_poster);
        filmOverview = getResources().getStringArray(R.array.film_overview);
        filmDirector = getResources().getStringArray(R.array.film_director);
        filmRelease = getResources().getStringArray(R.array.film_release);
        filmScore = getResources().getIntArray(R.array.movie_score);
    }

    private void showFilmList() {
        movieList = new ArrayList<>();
        prepare();

        // add item
        for (int i = 0; i < filmName.length; i++) {
            Movie movie = new Movie();
            movie.setName(filmName[i]);
            movie.setDescription(filmOverview[i]);
            movie.setSutradara(filmDirector[i]);
            movie.setTanggalrilis(filmRelease[i]);
            movie.setScore(filmScore[i]);
            movie.setPoster(filmPoster.getResourceId(i, -1));
            movieList.add(movie);
        }
        recyclerView.setAdapter(new MovieAdapter(getContext(), movieList, true));
    }*/

    private void showLoading(Boolean state) {
        if (state)
            view.findViewById(R.id.progressBar_movie).setVisibility(View.VISIBLE);
            else
                view.findViewById(R.id.progressBar_movie).setVisibility(View.GONE);

    }

    //AsyncTask
    /*public class FetchMovies extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            view.findViewById(R.id.progressBar_movie).setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            String urlmovie = "https://api.themoviedb.org/3/discover/movie?api_key=" + MainActivity.myAPI + "&language=en-US";
            movieArrayList = new ArrayList<>();
            try {
                movieArrayList = NetworUtils.fetchData(urlmovie); //Get popular movies
            }
                catch (IOException e) {
                    e.printStackTrace();
                }
            return null;
        }

        @Override
        protected void onPostExecute(Void  s) {
            super.onPostExecute(s);
            view.findViewById(R.id.progressBar_movie).setVisibility(View.INVISIBLE);
            recyclerView.setAdapter(new MovieAdapter(getContext(), movieArrayList, true));
        }
    }*/
}
