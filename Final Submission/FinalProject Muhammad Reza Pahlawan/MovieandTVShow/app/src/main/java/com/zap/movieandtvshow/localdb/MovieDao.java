package com.zap.movieandtvshow.localdb;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import com.zap.movieandtvshow.model.MovieFavourite;

import java.util.List;

/*
    Note:
    @Dao
    Create a data access object in the database using an interface class
 */
@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavourite(MovieFavourite movie);

    @Query("SELECT * FROM " + MovieFavourite.TABLE_NAME)
    List<MovieFavourite> getAllFavourite();

    @Delete
    void deleteFavourite(MovieFavourite movie);

    @Query("SELECT * FROM " + MovieFavourite.TABLE_NAME)
    Cursor selectAllMovie();
}
