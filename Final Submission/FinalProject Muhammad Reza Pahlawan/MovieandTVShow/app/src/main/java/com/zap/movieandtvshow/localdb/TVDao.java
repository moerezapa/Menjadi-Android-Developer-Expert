package com.zap.movieandtvshow.localdb;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.zap.movieandtvshow.model.TVFavourite;

import java.util.List;

/*
    Note:
    @Dao
    Create a data access object in the database using an interface class
 */
@Dao
public interface TVDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTVFavourite(TVFavourite tvFavourite);

    @Query("SELECT * FROM TVFavourite")
    List<TVFavourite> getAllTVFavourite();

    @Delete
    void deleteTVFavourite(TVFavourite tvFavourite);
}
