package com.example.tvshowapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvshowapp.R;
import com.example.tvshowapp.RecyclerViewInterface;
import com.example.tvshowapp.model.TvShow;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.ViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;
    private List<TvShow> tvShows;


    public TvShowAdapter(List<TvShow> tvShows, RecyclerViewInterface recyclerViewInterface) {
        this.tvShows = tvShows;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    public void setData(List<TvShow> tvShows) {
        this.tvShows = tvShows;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_tvshow, parent, false);
        return new ViewHolder(view, recyclerViewInterface);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TvShow tvShow = tvShows.get(position);

        holder.tvShowName.setText(tvShow.getName());
        holder.networkName.setText(tvShow.getNetwork());
        holder.started.setText(tvShow.getStartDate());
        holder.status.setText(tvShow.getStatus());

        // Load image using Picasso or your preferred image loading library
        Picasso.get().load(tvShow.getImageThumbnailPath()).into(holder.imageThumbnail);

        // Set other TextViews with additional information if needed
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }

    public TvShow getItem(int position) {
        return tvShows.get(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageThumbnail;
        TextView tvShowName;

        TextView networkName;

        TextView started;

        TextView status;

        public ViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            imageThumbnail = itemView.findViewById(R.id.imageTvShow);
            tvShowName = itemView.findViewById(R.id.textName);
            networkName = itemView.findViewById(R.id.textNetwork);
            started = itemView.findViewById(R.id.textStarted);
            status = itemView.findViewById(R.id.textStatus);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (recyclerViewInterface != null){
                        int pos = getBindingAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
            // Initialize other TextViews here if needed
        }
    }
}
