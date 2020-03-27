package com.zap.movieandtvshow.movie;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zap.movieandtvshow.R;
import com.zap.movieandtvshow.model.Movies;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter  extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context context;
    private List<Movie> movieList;
    //private List<MovieItems> movieItems = new ArrayList<>();
    private List<Movies> movieItems = new ArrayList<>();
    boolean isHorizontalList;

    /**
     * Gunakan method ini jika semua datanya akan diganti
     *
     * @param items kumpulan data baru yang akan mengganti semua data yang sudah ada
     */
    public void setData(Context context, ArrayList<Movies> items) {
        movieItems.clear();
        movieItems.addAll(items);
        this.context = context;
        notifyDataSetChanged();
    }

    //________create  constructor with required parameter
    /*public MovieAdapter(Context context, ArrayList<MovieItems> movieItems, boolean isHorizontalList) {
        //________initialize
        this.context = context;
        this.movieItems = movieItems;
        this.isHorizontalList = isHorizontalList;
    }*/
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_item, viewGroup, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.txt_filmname.setText(movieItems.get(i).getOriginalTitle());
        viewHolder.txt_releasedate.setText(movieItems.get(i).getReleaseDate());
        viewHolder.txt_popularity.setText(context.getResources().getString(R.string.popularity) + ": " + movieItems.get(i).getPopularity());
        Picasso.get()
                .load(movieItems.get(i).getPosterPath())
                .into(viewHolder.poster);

        viewHolder.cardView_film.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, ""+movieItems.get(i), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, MovieDetail.class);
                intent.putExtra(MovieDetail.MOVIE_DETAIL, movieItems.get(viewHolder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() { return movieItems.size(); }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_filmname, txt_releasedate, txt_popularity;
        ImageView poster;

        CardView cardView_film;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_filmname = itemView.findViewById(R.id.txt_name);
            txt_releasedate = itemView.findViewById(R.id.txt_tanggalrilis);
            txt_popularity = itemView.findViewById(R.id.txt_popularity);

            poster = itemView.findViewById(R.id.movie_poster);

            cardView_film = itemView.findViewById(R.id.cardview_film);
        }
    }
}
