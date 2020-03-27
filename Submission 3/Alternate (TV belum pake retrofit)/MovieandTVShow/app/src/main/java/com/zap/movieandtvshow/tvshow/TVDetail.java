package com.zap.movieandtvshow.tvshow;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zap.movieandtvshow.MainActivity;
import com.zap.movieandtvshow.R;

public class TVDetail extends AppCompatActivity {

    public static final String TV_DETAIL = "tv_detail";
    public static String asal;
    TextView judultv, creatortv, tanggalrilis, overview, vote_average, popularity;
    ImageView poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tvshowdetail_tbshowtab);

        showMovieDetail();
    }

    private void showMovieDetail() {
        TV tvShow = getIntent().getParcelableExtra(TV_DETAIL);

        getSupportActionBar().setTitle(tvShow.getName());

        judultv = findViewById(R.id.judultv);
        poster = findViewById(R.id.img_poster);
        creatortv = findViewById(R.id.sutradara);
        tanggalrilis = findViewById(R.id.tanggalrilis);
        overview = findViewById(R.id.txt_overview);
        vote_average = findViewById(R.id.txt_vote_average);
        popularity = findViewById(R.id.txt_popularity);

        judultv.setText(tvShow.getName());
        tanggalrilis.setText(tvShow.getFirstAirDate());
        Picasso.get()
                .load(tvShow.getPosterPath())
                .into(poster);
        popularity.setText(String.valueOf(tvShow.getPopularity()));
        overview.setText(tvShow.getOverview());
        vote_average.setText(tvShow.getVoteAverage() + "%");
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra(asal, "tvdetail");
        startActivity(intent);
        finish();
    }
}
