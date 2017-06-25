package com.example.srikanth.youtubeapi.Adapters;

import android.net.Uri;

import com.example.srikanth.youtubeapi.DeveloperKey;
import com.example.srikanth.youtubeapi.MainActivity;
import com.example.srikanth.youtubeapi.OrganPage;
import com.example.srikanth.youtubeapi.VideoAsyncTask;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Srikanth on 6/22/2017.
 */

public class VideoitemList  {
    public static ArrayList<VideoItem> videoList = new ArrayList<VideoItem>();

    public static ArrayList<VideoItem> getVideoList() {
        /* fetching data only takes place if httpRequest is false*/
        if(!MainActivity.httpRequest){

                new VideoAsyncTask().execute(OrganPage.CHANNELID);

            MainActivity.httpRequest = true;
        }
        return videoList;
    }




}
