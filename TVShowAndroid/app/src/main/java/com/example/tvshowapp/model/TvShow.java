package com.example.tvshowapp.model;

import com.google.gson.annotations.SerializedName;

public class TvShow {
    @SerializedName("_id")
    private String id;
    @SerializedName("id")
    private int tvShowId;
    @SerializedName("name")
    private String name;
    @SerializedName("permalink")
    private String permalink;
    @SerializedName("description")
    private String description;
    @SerializedName("start_date")
    private String startDate;
    @SerializedName("end_date")
    private String endDate;
    @SerializedName("country")
    private String country;
    @SerializedName("network")
    private String network;
    @SerializedName("status")
    private String status;
    @SerializedName("runtime")
    private int runtime;
    @SerializedName("image_path")
    private String imagePath;
    @SerializedName("image_thumbnail_path")
    private String imageThumbnailPath;
    @SerializedName("rating")
    private String rating;
    @SerializedName("genres")
    private String[] genres;
    @SerializedName("pictures")
    private String[] pictures;


    // Default Constructor
    public TvShow() {

    }
    // Parameterized Constructor
    public TvShow(int tvShowId, String name, String permalink, String description, String startDate, String endDate, String country, String network, String status, int runtime, String imageThumbnailPath, String rating, String[] genres, String[] pictures) {
        this.tvShowId = tvShowId;
        this.name = name;
        this.permalink = permalink;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.country = country;
        this.network = network;
        this.status = status;
        this.runtime = runtime;
        this.imageThumbnailPath = imageThumbnailPath;
        this.rating = rating;
        this.genres = genres;
        this.pictures = pictures;
    }

    // Convert Tv Shows to Favourite TV Shows
    public FavoriteTvShow toFavoriteTvShow() {
        FavoriteTvShow favoriteTvShow = new FavoriteTvShow();
        favoriteTvShow.tvShowId = this.tvShowId;
        favoriteTvShow.name = this.name;
        favoriteTvShow.network = this.network;
        favoriteTvShow.startDate = this.startDate;
        favoriteTvShow.status = this.status;
        favoriteTvShow.imageThumbnailPath = this.imageThumbnailPath;
        return favoriteTvShow;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTvShowId() {
        return tvShowId;
    }

    public void setTvShowId(int tvShowId) {
        this.tvShowId = tvShowId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getImageThumbnailPath() {
        return imageThumbnailPath;
    }

    public void setImageThumbnailPath(String imageThumbnailPath) {
        this.imageThumbnailPath = imageThumbnailPath;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public String[] getPictures() {
        return pictures;
    }

    public void setPictures(String[] pictures) {
        this.pictures = pictures;
    }
}
