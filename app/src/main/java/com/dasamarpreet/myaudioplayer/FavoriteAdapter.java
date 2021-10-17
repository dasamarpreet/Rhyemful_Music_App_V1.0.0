package com.dasamarpreet.myaudioplayer;

import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    Context context;
    ArrayList<String> favList;

    public FavoriteAdapter(Context context, ArrayList<String> favList) {
        this.context = context;
        this.favList = favList;
    }

    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.ViewHolder holder, int position) {
        holder.songNameFav.setText(favList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, PlayerActivity.class);
//                intent.putExtra("favPosition", position);
//                context.startActivity(intent);
                StyleableToast.makeText(context, "App in Development", Toast.LENGTH_SHORT, R.style.customToastFav).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return favList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView songNameFav;
        ImageView imageViewFav;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            songNameFav = itemView.findViewById(R.id.song_name_fav);
            imageViewFav = itemView.findViewById(R.id.song_image_fav);
        }
    }
}
