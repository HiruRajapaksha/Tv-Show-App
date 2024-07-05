package com.example.tvshowapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.tvshowapp.RoomDatabase.AppDatabase;
import com.example.tvshowapp.adapters.ImageSliderAdapter;
import com.example.tvshowapp.model.FavoriteTvShow;
import com.example.tvshowapp.model.TvShow;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    private AppDatabase appDatabase;
    private ViewPager2 sliderViewPager;
    private ImageSliderAdapter sliderAdapter;
    private RoundedImageView imageShowD;
    private TextView showName;
    private TextView netWork;
    private TextView status;
    private TextView startedOn;
    private TextView showDescription;
    private TextView rating;
    private TextView genre;
    private TextView runtime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        sliderViewPager = findViewById(R.id.sliderViewPager);
        imageShowD = findViewById(R.id.imageShowD);
        showName = findViewById(R.id.textShowName);
        netWork = findViewById(R.id.textNetworkingCountry);
        status = findViewById(R.id.textShowStatus);
        startedOn = findViewById(R.id.textShowStarted);
        showDescription = findViewById(R.id.textShowDescription);
        rating = findViewById(R.id.textShowRating);
        genre = findViewById(R.id.textShowGenre);
        runtime = findViewById(R.id.textShowRuntime);

        Intent intent = getIntent();

        // Retrieve information from Intent extras
        String tvShowName = intent.getStringExtra("TV_SHOW_NAME");
        String tvShowNetwork = intent.getStringExtra("TV_SHOW_NETWORK");
        String tvShowStartDate = intent.getStringExtra("TV_SHOW_START_DATE");
        String tvShowStatus = intent.getStringExtra("TV_SHOW_STATUS");
        String tvShowDescription = intent.getStringExtra("TV_SHOW_DESCRIPTION");
        String tvShowRating = intent.getStringExtra("TV_SHOW_RATING");
        String[] tvShowGenre = intent.getStringArrayExtra("TV_SHOW_GENRE");
        int tvShowRuntime = intent.getIntExtra("TV_SHOW_RUNTIME", 0);

        String tvShowImagePath = intent.getStringExtra("TV_SHOW_IMAGE_PATH");


        // Set values to TextView elements
        showName.setText(tvShowName);
        netWork.setText(tvShowNetwork);
        status.setText(tvShowStatus);
        startedOn.setText(tvShowStartDate);
        showDescription.setText(tvShowDescription);
        rating.setText(tvShowRating);
        genre.setText(Arrays.toString(tvShowGenre));
        runtime.setText(String.valueOf(tvShowRuntime));


        // Retrieve the 'pictures' array from intent
        String[] tvShowPictures = intent.getStringArrayExtra("TV_SHOW_PICTURES");

        // Set up the image slider with the 'pictures' array
        if (tvShowPictures != null && tvShowPictures.length > 0) {
            List<String> imageUrls = new ArrayList<>(Arrays.asList(tvShowPictures));
            sliderAdapter = new ImageSliderAdapter(this, imageUrls);
            sliderViewPager.setAdapter(sliderAdapter);
        }


        // Handle back button click
        ImageView imageBack = findViewById(R.id.imageBack);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Close the DetailsActivity and go back to MainActivity
            }
        });

        // Load the imageThumbnailPath into the RoundedImageView using Glide
        Glide.with(this)
                .load(tvShowImagePath)
                .centerCrop()
                .into(imageShowD);



        // Initialize Room Database
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app-database").build();

        ImageView imageFav = findViewById(R.id.tvFavoritesList);
        imageFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add the TV show to favorites
                addToFavorites();
            }
        });

    }


    private void addToFavorites() {
        // Convert TvShow to FavoriteTvShow
        FavoriteTvShow favoriteTvShow = getCurrentTvShow().toFavoriteTvShow();

        // Insert the FavoriteTvShow into the database
        new Thread(() -> appDatabase.favoriteTvShowDao().insert(favoriteTvShow)).start();

        Toast.makeText(this, "Added to Favorites", Toast.LENGTH_SHORT).show();
    }


    private TvShow getCurrentTvShow() {
        // Get the current TV show details from the intent

        Intent intent = getIntent();

        // Retrieve information from Intent extras
        int tvShowId = intent.getIntExtra("TV_SHOW_ID", 0);
        String tvShowName = intent.getStringExtra("TV_SHOW_NAME");
        String tvShowNetwork = intent.getStringExtra("TV_SHOW_NETWORK");
        String tvShowStartDate = intent.getStringExtra("TV_SHOW_START_DATE");
        String tvShowStatus = intent.getStringExtra("TV_SHOW_STATUS");
        String tvShowDescription = intent.getStringExtra("TV_SHOW_DESCRIPTION");
        String tvShowRating = intent.getStringExtra("TV_SHOW_RATING");
        String[] tvShowGenre = intent.getStringArrayExtra("TV_SHOW_GENRE");
        int tvShowRuntime = intent.getIntExtra("TV_SHOW_RUNTIME", 0);
        String tvShowImageThumbnailPath = intent.getStringExtra("TV_SHOW_IMAGE_THUMBNAIL_PATH");

        TvShow currentTvShow = new TvShow();

        currentTvShow.setTvShowId(tvShowId);
        currentTvShow.setName(tvShowName);
        currentTvShow.setNetwork(tvShowNetwork);
        currentTvShow.setStartDate(tvShowStartDate);
        currentTvShow.setStatus(tvShowStatus);
        currentTvShow.setDescription(tvShowDescription);
        currentTvShow.setRating(tvShowRating);
        currentTvShow.setGenres(tvShowGenre);
        currentTvShow.setRuntime(tvShowRuntime);
        currentTvShow.setImageThumbnailPath(tvShowImageThumbnailPath);


        return currentTvShow; // Replace with actual TvShow instance
    }




}
