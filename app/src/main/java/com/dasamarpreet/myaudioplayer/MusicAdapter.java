package com.dasamarpreet.myaudioplayer;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.io.File;
import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MyVieHolder> {

    public Context mContext;
    static ArrayList<MusicFiles> mFile;

    MusicAdapter(Context mContext, ArrayList<MusicFiles> mFile){
        this.mContext = mContext;
        this.mFile = mFile;
    }

    @NonNull
    @Override
    public MyVieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.music_items, parent, false);
        return new MyVieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVieHolder holder, int position) {
        holder.file_name.setText(mFile.get(position).getTitle());
        byte[] image = getAlbumArt(mFile.get(position).getPath());
        if (image != null){
            Glide.with(mContext).asBitmap().load(image).into(holder.album_art);
        }
        else{
            Glide.with(mContext).load(R.drawable.logo_variant_no_bg).into(holder.album_art);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, PlayerTestingNew.class);
                intent.putExtra("position", position);
                mContext.startActivity(intent);
            }
        });

        holder.menuFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                OfflineStorage offlineStorage = new OfflineStorage(mContext);
//                    holder.menuFavorite.setBackgroundResource(R.drawable.fav_100);

                ArrayList<String> favSongs = offlineStorage.getListString("fav");
                if (favSongs.isEmpty()){
                    favSongs.add(mFile.get(position).getTitle());
                    offlineStorage.putListString("fav",favSongs);
                    holder.menuFavorite.setBackgroundResource(R.drawable.ic_favorite_on);
                    StyleableToast.makeText(mContext, "Added to Fav!", Toast.LENGTH_SHORT, R.style.customToast).show();
                }
                if (!favSongs.isEmpty()){
                    if (favSongs.contains(mFile.get(position).getTitle())){
                        StyleableToast.makeText(mContext, "Already in Fav!", Toast.LENGTH_SHORT, R.style.customToast).show();
                    }
                    else {
                        favSongs.add(mFile.get(position).getTitle());
                        offlineStorage.putListString("fav",favSongs);
                        holder.menuFavorite.setBackgroundResource(R.drawable.ic_favorite_on);
                        StyleableToast.makeText(mContext, "New Song added to Fav!", Toast.LENGTH_SHORT, R.style.customToast).show();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFile.size();
    }

    public class MyVieHolder extends RecyclerView.ViewHolder{
        TextView file_name;
        ImageView album_art, menuFavorite;

        public MyVieHolder(@NonNull View itemView) {
            super(itemView);
            file_name = itemView.findViewById(R.id.music_file_name);
            album_art = itemView.findViewById(R.id.music_img);
            menuFavorite = itemView.findViewById(R.id.menuFav);
        }
    }

    private byte[] getAlbumArt(String uri){
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(uri);
        byte[] art = retriever.getEmbeddedPicture();
        retriever.release();
        return art;
    }

    void updateList(ArrayList<MusicFiles> musicFilesArrayList){
        mFile = new ArrayList<>();
        mFile.addAll(musicFilesArrayList);
        notifyDataSetChanged();
    }
}
