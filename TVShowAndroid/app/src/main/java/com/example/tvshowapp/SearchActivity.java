package com.example.tvshowapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvshowapp.adapters.TvShowAdapter;
import com.example.tvshowapp.controller.TvShowApi;
import com.example.tvshowapp.model.TvShow;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity implements RecyclerViewInterface {

    private EditText inputSearch;
    private RecyclerView tvShowRecyclerView;
    private TvShowAdapter adapter;
    private List<TvShow> allTvShows = new ArrayList<>(); // Initialize the list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        inputSearch = findViewById(R.id.inputSearch);
        tvShowRecyclerView = findViewById(R.id.tvShowRecyclerView);

        // Set the SearchActivity as the listener for item clicks
        adapter = new TvShowAdapter(new ArrayList<>(), this);
        tvShowRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tvShowRecyclerView.setAdapter(adapter);

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
                    allTvShows = response.body(); // Assign the response to allTvShows
                    Log.d("API Response", "TV Shows: " + allTvShows);
                } else {
                    // Log the error message
                    Log.e("API Error", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<TvShow>> call, @NonNull Throwable t) {
                // Log the failure message
                Log.e("API Failure", "Failed to load TV shows", t);
                // Handle failure
            }
        });

        // Set a TextChangedListener on the search EditText
        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not needed for this example
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Filter the TV shows based on the search query
                List<TvShow> filteredTvShows = filterTvShows(charSequence.toString());
                adapter.setData(filteredTvShows);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Not needed for this example
            }
        });

        // Handle back button click
        ImageView imageBack = findViewById(R.id.imageBack);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Close the SearchActivity and go back to MainActivity
            }
        });
    }

    private List<TvShow> filterTvShows(String query) {
        List<TvShow> filteredList = new ArrayList<>();
        // If the query is empty, show an empty list
        if (query.isEmpty()) {
            return filteredList;
        }
        // Iterate through allTvShows and add those matching the query to filteredList
        for (TvShow tvShow : allTvShows) {
            if (tvShow.getName() != null && query != null &&
                    tvShow.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(tvShow);
            }
        }
        return filteredList;
    }

    @Override
    public void onItemClick(int position) {
        // Get the clicked TV show
        TvShow clickedTvShow = adapter.getItem(position);

        // Create an Intent to start DetailsActivity
        Intent intent = new Intent(SearchActivity.this, DetailsActivity.class);

        // Pass information using Intent extras
        intent.putExtra("TV_SHOW_NAME", clickedTvShow.getName());
        intent.putExtra("TV_SHOW_NETWORK", clickedTvShow.getNetwork());
        intent.putExtra("TV_SHOW_START_DATE", clickedTvShow.getStartDate());
        intent.putExtra("TV_SHOW_STATUS", clickedTvShow.getStatus());
        intent.putExtra("TV_SHOW_DESCRIPTION", clickedTvShow.getDescription());
        intent.putExtra("TV_SHOW_RATING", clickedTvShow.getRating());
        intent.putExtra("TV_SHOW_GENRE", clickedTvShow.getGenres());
        intent.putExtra("TV_SHOW_RUNTIME", clickedTvShow.getRuntime());
        intent.putExtra("TV_SHOW_IMAGE_PATH", clickedTvShow.getImagePath());
        intent.putExtra("TV_SHOW_PICTURES", clickedTvShow.getPictures());

        // Start the DetailsActivity
        startActivity(intent);
    }
}
