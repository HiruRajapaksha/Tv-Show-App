package com.example.tvshowapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tvshowapp.adapters.TvShowAdapter;
import com.example.tvshowapp.controller.TvShowApi;
import com.example.tvshowapp.model.TvShow;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface{

    private RecyclerView recyclerView;
    private TvShowAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.tvShowRecylerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        // Create Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:4000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        // Create API service
        TvShowApi tvShowApi = retrofit.create(TvShowApi.class);


        // Make API request
        Call<List<TvShow>> call = tvShowApi.getTvShows();
        call.enqueue(new Callback<List<TvShow>>() {
            @Override
            public void onResponse(@NonNull Call<List<TvShow>> call, @NonNull Response<List<TvShow>> response) {
                if (response.isSuccessful()) {
                    List<TvShow> tvShows = response.body();
                    Log.d("API Response", "TV Shows: " + tvShows);
                    adapter = new TvShowAdapter(tvShows, MainActivity.this);
                    recyclerView.setAdapter(adapter);
                } else {

                    // Log the error message
                    Log.e("API Error", "Error: " + response.message());

                    // Handle error
                    Toast.makeText(MainActivity.this, "Error loading TV shows", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<TvShow>> call, @NonNull Throwable t) {

                // Log the failure message
                Log.e("API Failure", "Failed to load TV shows", t);

                // Handle failure
                Toast.makeText(MainActivity.this, "Failed to load TV shows", Toast.LENGTH_SHORT).show();
            }
        });



        ImageView imageFav = findViewById(R.id.tvShowFavorites);
        ImageView imageSearch = findViewById(R.id.imageSearch);

        imageFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent favIntent = new Intent(MainActivity.this, FavoritesActivity.class);
                startActivity(favIntent);
            }
        });


        // Set OnClickListener for the search icon
        imageSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the SearchActivity when the search icon is clicked
                Intent searchIntent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(searchIntent);
            }
        });


    }


    @Override
    public void onItemClick(int position) {

        // Get the clicked TV show
        TvShow clickedTvShow = adapter.getItem(position);

        // Create an Intent to start DetailsActivity
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);

        // Pass information using Intent extras
        intent.putExtra("TV_SHOW_ID", clickedTvShow.getId());
        intent.putExtra("TV_SHOW_NAME", clickedTvShow.getName());
        intent.putExtra("TV_SHOW_NETWORK", clickedTvShow.getNetwork());
        intent.putExtra("TV_SHOW_START_DATE", clickedTvShow.getStartDate());
        intent.putExtra("TV_SHOW_STATUS", clickedTvShow.getStatus());
        intent.putExtra("TV_SHOW_DESCRIPTION", clickedTvShow.getDescription());
        intent.putExtra("TV_SHOW_RATING", clickedTvShow.getRating());
        intent.putExtra("TV_SHOW_GENRE", clickedTvShow.getGenres());
        intent.putExtra("TV_SHOW_RUNTIME", clickedTvShow.getRuntime());

        intent.putExtra("TV_SHOW_IMAGE_PATH", clickedTvShow.getImagePath());
        intent.putExtra("TV_SHOW_IMAGE_THUMBNAIL_PATH", clickedTvShow.getImageThumbnailPath());


        // Pass the 'pictures' array
        intent.putExtra("TV_SHOW_PICTURES", clickedTvShow.getPictures());


        // Add other information as needed

        // Start the DetailsActivity
        startActivity(intent);
    }


}