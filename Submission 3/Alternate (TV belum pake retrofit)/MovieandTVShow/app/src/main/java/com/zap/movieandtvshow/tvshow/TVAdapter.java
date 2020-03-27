package com.zap.movieandtvshow.tvshow;

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

import java.util.List;

public class TVAdapter extends RecyclerView.Adapter<TVAdapter.ViewHolder> {

    private Context context;

    private List<TV> tvShowList;

    boolean isHorizontalList;

    //________create  constructor with required parameter
    public TVAdapter(Context context, List<TV> tvShowList, boolean isHorizontalList) {
        //________initialize
        this.context = context;
        this.tvShowList = tvShowList;
        this.isHorizontalList = isHorizontalList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.tvshow_item, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.txt_tvname.setText(tvShowList.get(i).getName());
        viewHolder.txt_releasedate.setText(tvShowList.get(i).getFirstAirDate());
        viewHolder.txt_popularity.setText(context.getResources().getString(R.string.popularity) + ": " + tvShowList.get(i).getPopularity());
        Picasso.get()
                .load(tvShowList.get(i).getPosterPath())
                .into(viewHolder.poster);
        viewHolder.cardView_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, ""+tvShowList.get(i), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(viewHolder.cardView_tv.getContext(), TVDetail.class);
                intent.putExtra(TVDetail.TV_DETAIL, tvShowList.get(i));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() { return tvShowList.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_tvname, txt_releasedate, txt_popularity;
        ImageView poster;

        CardView cardView_tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_tvname = itemView.findViewById(R.id.txt_name);
            txt_releasedate = itemView.findViewById(R.id.txt_tanggalrilis);
            txt_popularity = itemView.findViewById(R.id.txt_popularity);

            poster = itemView.findViewById(R.id.tvshow_poster);

            cardView_tv = itemView.findViewById(R.id.cardview_tvshow);
        }

    }
}
