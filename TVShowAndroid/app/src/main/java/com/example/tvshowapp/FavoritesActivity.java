// FavoritesActivity.java

package com.example.tvshowapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.tvshowapp.RoomDatabase.AppDatabase;
import com.example.tvshowapp.adapters.TvShowFavoritesAdapter;
import com.example.tvshowapp.model.FavoriteTvShow;
import com.example.tvshowapp.model.TvShow;

import java.util.ArrayList;
import java.util.List;


public class FavoritesActivity extends AppCompatActivity implements TvShowFavoritesAdapter.OnRemoveItemClickListener {

    private AppDatabase appDatabase;
    private RecyclerView recyclerView;
    private TvShowFavoritesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);


        // Handle back button click
        ImageView imageBack = findViewById(R.id.imageBack);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Close the FavoritesActivity and go back to MainActivity
            }
        });


        // Initialize Room Database
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app-database").build();

        // Set up RecyclerView
        recyclerView = findViewById(R.id.favoritesRecyclerView);
        adapter = new TvShowFavoritesAdapter(new ArrayList<>(), this);
        adapter.setRemoveItemClickListener((TvShowFavoritesAdapter.OnRemoveItemClickListener) this); // Set remove item click listener
        recyclerView.setAdapter(adapter);

        // Load favorite TV shows
        loadFavoriteTvShows();
    }

    private void loadFavoriteTvShows() {
        new Thread(() -> {
            List<FavoriteTvShow> favoriteTvShows = appDatabase.favoriteTvShowDao().getAllFavorites();
            runOnUiThread(() -> {
                if (favoriteTvShows.isEmpty()) {
                    // Handle case when there are no favorites
                    // For example, display a message or hide the RecyclerView
                } else {
                    adapter.setFavorites(favoriteTvShows);
                }
            });
        }).start();

    }


    @Override
    public void onRemoveItemClick(int position) {
        // Remove the item from the favorites list and update the RecyclerView
        removeFavoriteTvShow(position);
    }

    private void removeFavoriteTvShow(int position) {
        new Thread(() -> {

            // Debug log
            System.out.println("Position before removal: " + position);

            // Remove from Room Database
            appDatabase.favoriteTvShowDao().delete(adapter.getFavorites().get(position));


            // Debug log
            System.out.println("Position after removal: " + position);


            // Remove from the local list
            runOnUiThread(() -> {
                adapter.getFavorites().remove(position);
                adapter.notifyDataSetChanged();
            });
        }).start();
    }





}
