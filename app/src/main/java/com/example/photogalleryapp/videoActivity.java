package com.example.photogalleryapp;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

public class videoActivity extends AppCompatActivity {
    static final int REQUEST_VIDEO_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        Button homebtn = (Button) findViewById(R.id.buttonhome);
        homebtn.setOnClickListener(backListener);

        Intent takeVideoIntent = new Intent (MediaStore.ACTION_VIDEO_CAPTURE);
        if(takeVideoIntent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videoUri = data.getData();
            VideoView videoView = (VideoView) findViewById(R.id.videoView);
            videoView.setVideoURI(videoUri);
            videoView.start();
        }
    }


    private void dispatchTakeVideoIntent(){
        Intent takeVideoIntent = new Intent (MediaStore.ACTION_VIDEO_CAPTURE);
        if(takeVideoIntent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

    private View.OnClickListener backListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent i = new Intent(videoActivity.this, MainActivity.class);
            startActivity(i);
        }
    };
}
