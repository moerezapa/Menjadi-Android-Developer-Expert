package com.zap.movieandtvshow.movie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zap.movieandtvshow.R;
import com.zap.movieandtvshow.model.Movies;

public class MovieDetail extends AppCompatActivity {

    public static String MOVIE_DETAIL = "movie_detail";
    private TextView judulfilm, popularity, tanggalrilis, overview, vote;
    private ImageView poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moviedetail_movietab);

        showMovieDetail();
    }

    private void showMovieDetail() {
        Movies movie = getIntent().getParcelableExtra(MOVIE_DETAIL);

        getSupportActionBar().setTitle(movie.getTitle());

        judulfilm = findViewById(R.id.judulfilm);
        poster = findViewById(R.id.img_poster);
        popularity = findViewById(R.id.sutradara);
        tanggalrilis = findViewById(R.id.tanggalrilis);
        overview = findViewById(R.id.txt_overview);
        vote = findViewById(R.id.txt_score);

        judulfilm.setText(movie.getTitle());
        popularity.setText(String.valueOf(movie.getPopularity()));
        tanggalrilis.setText(movie.getReleaseDate());
        Picasso.get()
                .load(movie.getPosterPath())
                .into(poster);
        overview.setText(movie.getOverview());
        vote.setText(movie.getVoteAverage() + "%");
    }
}
