package com.example.tvshowapp.adapters;// TvShowFavoritesAdapter.java

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvshowapp.R;
import com.example.tvshowapp.model.FavoriteTvShow;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TvShowFavoritesAdapter extends RecyclerView.Adapter<TvShowFavoritesAdapter.ViewHolder> {
    private List<FavoriteTvShow> favorites;
    private Context context;
    private OnRemoveItemClickListener removeItemClickListener;


    public interface OnRemoveItemClickListener {
        void onRemoveItemClick(int position);
    }

    public void setRemoveItemClickListener(OnRemoveItemClickListener listener) {
        this.removeItemClickListener = listener;
    }


    public TvShowFavoritesAdapter(List<FavoriteTvShow> favorites, Context context) {
        this.favorites = favorites != null ? favorites : new ArrayList<>();
        this.context = context;
    }

    public void setFavorites(List<FavoriteTvShow> favorites) {
        this.favorites = favorites != null ? favorites : new ArrayList<>();
        notifyDataSetChanged();
    }

    public List<FavoriteTvShow> getFavorites() {
        return favorites;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favourite_tvshow, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FavoriteTvShow favoriteTvShow = favorites.get(position);

        holder.tvShowName.setText(favoriteTvShow.getName());
        holder.networkName.setText(favoriteTvShow.getNetwork());
        holder.started.setText(favoriteTvShow.getStartDate());
        holder.status.setText(favoriteTvShow.getStatus());

        // Load image using Picasso or your preferred image loading library
        Picasso.get().load(favoriteTvShow.getImageThumbnailPath()).into(holder.imageThumbnail);


        // Set click listener for the remove icon
        holder.removeIcon.setOnClickListener(v -> {
            if (removeItemClickListener != null) {
                removeItemClickListener.onRemoveItemClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageThumbnail;
        TextView tvShowName;
        TextView networkName;
        TextView started;
        TextView status;

        ImageView removeIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageThumbnail = itemView.findViewById(R.id.imageTvShow);
            tvShowName = itemView.findViewById(R.id.textName);
            networkName = itemView.findViewById(R.id.textNetwork);
            started = itemView.findViewById(R.id.textStarted);
            status = itemView.findViewById(R.id.textStatus); // Update with actual ID
            removeIcon = itemView.findViewById(R.id.tvFavoritesRemove);

        }
    }
}
