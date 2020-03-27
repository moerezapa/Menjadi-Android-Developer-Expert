package com.zap.movieandtvshow.tvshow;

import android.os.Parcel;
import android.os.Parcelable;

public class TV implements Parcelable {
    private int id;
    private int voteAverage;
    private int voteCount;
    private String originalName;
    private String name;
    private double popularity;
    private String backdropPath;
    private String overview;
    private String firstAirDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(int voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPopularity() {
        return popularity;
    }

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

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
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

    private String posterPath;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.voteAverage);
        dest.writeInt(this.voteCount);
        dest.writeString(this.originalName);
        dest.writeString(this.name);
        dest.writeDouble(this.popularity);
        dest.writeString(this.backdropPath);
        dest.writeString(this.overview);
        dest.writeString(this.firstAirDate);
        dest.writeString(this.posterPath);
    }

    public TV() {
    }

    protected TV(Parcel in) {
        this.id = in.readInt();
        this.voteAverage = in.readInt();
        this.voteCount = in.readInt();
        this.originalName = in.readString();
        this.name = in.readString();
        this.popularity = in.readDouble();
        this.backdropPath = in.readString();
        this.overview = in.readString();
        this.firstAirDate = in.readString();
        this.posterPath = in.readString();
    }

    public static final Parcelable.Creator<TV> CREATOR = new Parcelable.Creator<TV>() {
        @Override
        public TV createFromParcel(Parcel source) {
            return new TV(source);
        }

        @Override
        public TV[] newArray(int size) {
            return new TV[size];
        }
    };
}
