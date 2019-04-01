package com.example.photogalleryapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class galleryActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private int currentPhotoIndex = 0;
    private String currentPhotoPath = null;
    private ArrayList<String> photoGallery;
    public static final int REQUEST_CODE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Button ghomebtn = (Button) findViewById(R.id.ghomebtn);
        Button gcamerabtn = (Button) findViewById(R.id.gcamerabtn);
        ghomebtn.setOnClickListener(homeListener);
        gcamerabtn.setOnClickListener(cameraListener);

        //GridView gridView = (GridView) findViewById(R.id.galleryView);
        //gridView.setAdapter(new GridAdapter());

        Intent photoPicker = new Intent(Intent.ACTION_GET_CONTENT);
        
        File PictureDir = new File (Environment.getExternalStorageDirectory()
                .getAbsolutePath(), "/Android/data/com.example.photogalleryapp/files/Pictures");

        String picpath = PictureDir.getPath();
        
        Uri data = Uri.parse(picpath);
        
        photoPicker.setDataAndType(data, "image/*");
        
        startActivity(photoPicker);


    }


    private View.OnClickListener homeListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent i = new Intent(galleryActivity.this, MainActivity.class);
            startActivity(i);
        }
    };

    private View.OnClickListener cameraListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(galleryActivity.this, homepage.class);
            startActivity(intent);
        }
    };

}