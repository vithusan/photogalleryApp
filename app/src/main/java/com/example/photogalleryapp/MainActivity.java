package com.example.photogalleryapp;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton camerabtn = (ImageButton) findViewById(R.id.camerabtn);
        ImageButton gallerybtn = (ImageButton) findViewById(R.id.gallerybtn);
        ImageButton videobtn = (ImageButton) findViewById(R.id.videobtn);
        camerabtn.setOnClickListener(cameraListener);
        gallerybtn.setOnClickListener(galleryListener);
        videobtn.setOnClickListener(videoListener);

    }



   private View.OnClickListener cameraListener = new View.OnClickListener() {
     public void onClick(View v) {
        Intent i = new Intent(MainActivity.this, homepage.class);
          startActivity(i);
       }
   };

    private View.OnClickListener videoListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent i = new Intent(MainActivity.this, videoActivity.class);
            startActivity(i);
        }
    };

    private View.OnClickListener galleryListener = new View.OnClickListener(){
      public void onClick(View v){
          Intent intent = new Intent(MainActivity.this, galleryActivity.class);
            startActivity(intent);
      }
    };


}



