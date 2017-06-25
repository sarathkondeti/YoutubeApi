package com.example.srikanth.youtubeapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.srikanth.youtubeapi.Adapters.VideoAdapter;
import com.example.srikanth.youtubeapi.Adapters.VideoItem;
import com.example.srikanth.youtubeapi.Adapters.VideoitemList;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeIntents;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import static android.provider.MediaStore.Video.Thumbnails.VIDEO_ID;

public class OrganPage extends YouTubeBaseActivity {
    public static RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    public static VideoAdapter videoAdapter;
    public static ProgressBar progressBar;
    public static String CHANNELID="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organ_page);
        Intent intent = getIntent();
        CHANNELID = intent.getStringExtra(DeveloperKey.EXTRA_MESSAGE); //we get the channelID from MainActivity, and this used in
                                                                       //VideoitemList to fetch data from internet

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE); //By default this is "gone"  ,when AsyncTask is started it is made visilble and later it's gone

        recyclerView = (RecyclerView) findViewById(R.id.videorv);
        layoutManager = new LinearLayoutManager(this);
        videoAdapter = new VideoAdapter(VideoitemList.getVideoList(),this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(videoAdapter);
        recyclerView.setHasFixedSize(true);



    }




}
