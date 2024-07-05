package com.example.tvshowapp.RoomDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.tvshowapp.model.FavoriteTvShow;

import java.util.List;

@Dao
public interface FavoriteTvShowDao {
    @Insert
    void insert(FavoriteTvShow favoriteTvShow);

    @Delete
    void delete(FavoriteTvShow favoriteTvShow);

    @Query("SELECT * FROM favorite_tv_shows")
    List<FavoriteTvShow> getAllFavorites();
}
