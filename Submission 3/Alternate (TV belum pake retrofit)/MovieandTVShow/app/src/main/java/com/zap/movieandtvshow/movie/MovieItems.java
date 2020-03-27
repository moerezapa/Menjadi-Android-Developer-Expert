package com.zap.movieandtvshow.movie;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class MovieItems implements Parcelable {
    private int id;
    private  int voteAverage;
    private int voteCount;
    private String originalTitle;
    private String title;
    private double popularity;
    private String backdropPath;
    private String overview;
    private String releaseDate;
    private String posterPath;

    public MovieItems(JSONObject jsonObject) {
        try {
            int id = jsonObject.getInt("id");
            int voteAverage = jsonObject.getInt("vote_average");
            int voteCount = jsonObject.getInt("vote_count");
            String originalTitle = jsonObject.getString("original_title");
            String title = jsonObject.getString("title");
            Double popularity = jsonObject.getDouble("popularity");
            String backdropPath = jsonObject.getString("backdrop_path");
            String overview = jsonObject.getString("overview");
            String releaseDate = jsonObject.getString("release_date");
            String posterPath = jsonObject.getString("poster_path");

            this.id = id;
            this.voteAverage = voteAverage;
            this.voteCount = voteCount;
            this.originalTitle = originalTitle;
            this.title = title;
            this.popularity = popularity;
            this.backdropPath = backdropPath;
            this.overview = overview;
            this.releaseDate = releaseDate;
            this.posterPath = posterPath;
        }
            catch (Exception e) {
                e.printStackTrace();
            }
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getVoteAverage() { return voteAverage; }
    public void setVoteAverage(int voteAverage) { this.voteAverage = voteAverage; }

    public int getVoteCount() { return voteCount; }

    public void setVoteCount(int voteCount) { this.voteCount = voteCount; }

    public String getOriginalTitle() { return originalTitle; }

    public void setOriginalTitle(String originalTitle) { this.originalTitle = originalTitle; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public double getPopularity() { return popularity; }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getBackdropPath() {
        if (backdropPath != null && !backdropPath.isEmpty()) {
            if(!backdropPath.toLowerCase().contains("http://"))
                return "http://image.tmdb.org/t/p/original" + backdropPath;
            else
                return backdropPath;
        }
        return null; //Use Picasso to put placeholder for poster
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterPath() {
        if (posterPath != null && !posterPath.isEmpty()) {
            if(!posterPath.toLowerCase().contains("http://"))
                return "http://image.tmdb.org/t/p/w342" + posterPath;
            else
                return posterPath;
        }
        return null; //Use Picasso to put placeholder for poster
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.voteAverage);
        dest.writeInt(this.voteCount);
        dest.writeString(this.originalTitle);
        dest.writeString(this.title);
        dest.writeDouble(this.popularity);
        dest.writeString(this.backdropPath);
        dest.writeString(this.overview);
        dest.writeString(this.releaseDate);
        dest.writeString(this.posterPath);
    }

    protected MovieItems(Parcel in) {
        this.id = in.readInt();
        this.voteAverage = in.readInt();
        this.voteCount = in.readInt();
        this.originalTitle = in.readString();
        this.title = in.readString();
        this.popularity = in.readDouble();
        this.backdropPath = in.readString();
        this.overview = in.readString();
        this.releaseDate = in.readString();
        this.posterPath = in.readString();
    }

    public static final Parcelable.Creator<MovieItems> CREATOR = new Parcelable.Creator<MovieItems>() {
        @Override
        public MovieItems createFromParcel(Parcel source) {
            return new MovieItems(source);
        }

        @Override
        public MovieItems[] newArray(int size) {
            return new MovieItems[size];
        }
    };
}
