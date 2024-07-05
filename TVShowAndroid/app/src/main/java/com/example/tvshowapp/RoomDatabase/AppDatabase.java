package com.example.tvshowapp.RoomDatabase;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.tvshowapp.model.FavoriteTvShow;

@Database(entities = {FavoriteTvShow.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FavoriteTvShowDao favoriteTvShowDao();
}
