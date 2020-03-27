package com.zap.movieandtvshow.tvshow;

import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zap.movieandtvshow.MainActivity;
import com.zap.movieandtvshow.R;
import com.zap.movieandtvshow.utilities.NetworUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TVShowTabFragment extends Fragment {

    public static String tv_request = "tv_request";
    private View view;

    private RecyclerView recyclerView;

    private String[] tvName;
    private String[] tvOverview;
    private String[] tvCreator;
    private String[] tvRelease;
    private int[] tvScore;
    private TypedArray tvPoster;

    ArrayList<TV> tvArrayList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tvshow_tab, container, false);

        //________initialize
        recyclerView = view.findViewById(R.id.recyclerview_tvshow);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity().getApplicationContext()));

        new FetchTV().execute();
        //showTVList();
        return view;
    }

    // inisialisasi array
    /*private void prepare() {
        tvName = getResources().getStringArray(R.array.tvshow_name);
        tvPoster = getResources().obtainTypedArray(R.array.tvshow_poster);
        tvOverview = getResources().getStringArray(R.array.tvshow_overview);
        tvCreator = getResources().getStringArray(R.array.tvshow_creator);
        tvRelease = getResources().getStringArray(R.array.tvshow_release);
        tvScore = getResources().getIntArray(R.array.tvshow_score);
    }

    private void showTVList() {
        tvShowList = new ArrayList<>();
        prepare();

        // add item
        for (int i = 0; i < tvName.length; i++) {
            TVShow tvShow = new TVShow();
            tvShow.setName(tvName[i]);
            tvShow.setDescription(tvOverview[i]);
            tvShow.setSutradara(tvCreator[i]);
            tvShow.setTanggalrilis(tvRelease[i]);
            tvShow.setScore(tvScore[i]);
            tvShow.setPoster(tvPoster.getResourceId(i, -1));
            tvShowList.add(tvShow);
        }

        recyclerView.setAdapter(new TVAdapter(getContext(), tvShowList, true));
    }*/

    //AsyncTask
    public class FetchTV extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            view.findViewById(R.id.progressBar_tv).setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            String urlmovie = "https://api.themoviedb.org/3/discover/tv?api_key=" + MainActivity.myAPI + "&language=en-US";
            tvArrayList = new ArrayList<>();
            try {
                tvArrayList = NetworUtils.fetchTVData(urlmovie); //Get popular movies
            }
                catch (IOException e) {
                    e.printStackTrace();
                }
            return null;
        }

        @Override
        protected void onPostExecute(Void  s) {
            super.onPostExecute(s);
            view.findViewById(R.id.progressBar_tv).setVisibility(View.INVISIBLE);
            recyclerView.setAdapter(new TVAdapter(getContext(), tvArrayList, true));
        }
    }
}
