package com.example.srikanth.youtubeapi.Adapters;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.srikanth.youtubeapi.DeveloperKey;
import com.example.srikanth.youtubeapi.OrganPage;
import com.example.srikanth.youtubeapi.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeIntents;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import org.json.JSONObject;

import java.util.ArrayList;

import static android.R.attr.start;
import static android.R.attr.valueTo;

/**
 * Created by Srikanth on 6/22/2017.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private ArrayList<VideoItem> videoList;
    Context context;

    //public static ArrayList<Integer> playlistno = new ArrayList<>();  //Required if we want to display playlist thumbnails also
    YouTubeThumbnailLoader youTubeThumbnailLoader;

    public VideoAdapter(ArrayList<VideoItem> videoList, Context context) {
        this.videoList = videoList;
        this.context = context;

    }

    @Override
    public VideoAdapter.VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater;
        inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.video_item_layout,parent,false);
        VideoViewHolder viewHolder=new VideoViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VideoAdapter.VideoViewHolder holder, final int position) {
        holder.videoTitle.setText(videoList.get(position).videoTitle);

        holder.thumbnailView.initialize(DeveloperKey.DEVELOPER_KEY , new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader ThumbnailLoader) {

                youTubeThumbnailLoader = ThumbnailLoader;
               /*if(playlistno.contains(position))                     //**** Required for playlist thumbnails
                    youTubeThumbnailLoader.setPlaylist(videoList.get(position).videoId);
                else*/
                    youTubeThumbnailLoader.setVideo(videoList.get(position).videoId);
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
        /* This onClickListner for thumnailview alone however onClickListner for whole cardview is also included*/
        holder.thumbnailView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = null;
                                                     //********Required for playlist thumbnails
                /*if(playlistno.contains(position))
                    intent = YouTubeIntents.createPlayPlaylistIntent(context,videoList.get(position).videoId);
                else*/
                    intent = YouTubeIntents.createPlayVideoIntentWithOptions(context,videoList.get(position).videoId,true,false);
                context.startActivity(intent);

            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                                               //********Required for playlist thumbnails
                /*if(playlistno.contains(position))
                    intent = YouTubeIntents.createPlayPlaylistIntent(context,videoList.get(position).videoId);
                else*/
                    intent = YouTubeIntents.createPlayVideoIntentWithOptions(context,videoList.get(position).videoId,true,false);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return videoList.size() ;
    }


    public static class VideoViewHolder extends RecyclerView.ViewHolder{
        public YouTubeThumbnailView thumbnailView;
        public TextView videoTitle;
        public CardView cardView;
        public VideoViewHolder(View itemView) {
            super(itemView);
            thumbnailView = (YouTubeThumbnailView) itemView.findViewById(R.id.thumbnailview);
            cardView = (CardView) itemView.findViewById(R.id.video_cardview);
            videoTitle = (TextView) itemView.findViewById(R.id.video_title);
        }
    }
}
