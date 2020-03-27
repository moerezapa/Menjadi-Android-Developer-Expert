package com.zap.movieandtvshow.favourite;

import android.arch.persistence.room.Room;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.zap.movieandtvshow.R;
import com.zap.movieandtvshow.localdb.AppDatabase;
import com.zap.movieandtvshow.localdb.DBRepository;
import com.zap.movieandtvshow.model.MovieFavourite;

import java.util.ArrayList;
import java.util.List;

public class MovieFavTabFragment extends Fragment {

    private View view;

    private MovieFavAdapter movieFavAdapter;

    private RecyclerView rvFav;
    private ProgressBar progressBar;

    private AppDatabase appDatabase;

    public static final String AUTHORITY = "com.zap.movieandtvshow.provider";
    public static final Uri URI_MOVIE = Uri.parse("content://" + AUTHORITY + "/" + "MovieFavourite");
    private static final int LOADER = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.moviefavourite_fragment, container, false);

        rvFav = view.findViewById(R.id.recyclerview_filmFav);
        rvFav.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvFav.setHasFixedSize(true);
        progressBar = view.findViewById(R.id.progressBar_filmFav);

        fetchFavourite();
        //getActivity().getSupportLoaderManager().initLoader(LOADER,null, mLoaderCallbacks);
        return view;
    }

    public void fetchFavourite(){
        appDatabase = Room.databaseBuilder(getActivity().getApplicationContext(), AppDatabase.class, DBRepository.DB_NAME).build();
        new AsyncTask<Void, Void, List<MovieFavourite>>() {
            @Override
            protected List<MovieFavourite> doInBackground(Void... voids) {
                List<MovieFavourite> movieFavouriteList = appDatabase.daoAccess().getAllFavourite();
                return movieFavouriteList;
            }

            @Override
            protected void onPostExecute(List<MovieFavourite> movieFavouriteList) {
                super.onPostExecute(movieFavouriteList);
                if (movieFavouriteList != null) {
                    view.findViewById(R.id.txt_noFilmFavourite).setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                    movieFavAdapter = new MovieFavAdapter(getActivity(), movieFavouriteList);
                    rvFav.setAdapter(movieFavAdapter);
                }
            }
        }.execute();
    }
    private LoaderManager.LoaderCallbacks<Cursor> mLoaderCallbacks =
            new LoaderManager.LoaderCallbacks<Cursor>() {
                @NonNull
                @Override
                public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
                    /*TODO ERROR DI SINI*/
                    switch (id){
                        case LOADER:
                            return new CursorLoader(
                                    getActivity().getApplicationContext(),
                                    URI_MOVIE,
                                    null,
                                    null,
                                    null,
                                    null);


                        default:
                            throw new IllegalArgumentException();
                    }

                }

                @Override
                public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
                    Log.e("DATASET", "MONMON");
                    switch (loader.getId()){
                        case LOADER:
                            movieFavAdapter.setMenus(data);
                            break;
                    }
                }

                @Override
                public void onLoaderReset(@NonNull Loader<Cursor> loader) {
                    switch (loader.getId()){
                        case LOADER:
                            movieFavAdapter.setMenus(null);
                            break;
                    }
                }
            };
}
