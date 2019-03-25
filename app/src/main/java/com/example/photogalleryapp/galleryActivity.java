package com.example.photogalleryapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Button ghomebtn = (Button) findViewById(R.id.ghomebtn);
        Button gcamerabtn = (Button) findViewById(R.id.gcamerabtn);
        ghomebtn.setOnClickListener(homeListener);
        gcamerabtn.setOnClickListener(cameraListener);


        GridView gridView = (GridView) findViewById(R.id.galleryView);
        //gridView.setAdapter(new GridAdapter());
    }
/**
    class GridAdapter extends BaseAdapter{
        private ArrayList<String> photoGallery;
        private Context context;

        public ArrayList<String> populateGallery() {
            File file = new File(Environment.getExternalStorageDirectory()
                    .getAbsolutePath(), "/Android/data/com.example.photogalleryapp/files/Pictures");
            photoGallery = new ArrayList<String>();
            File[] fList = file.listFiles();
            if (fList != null) {
                for (File f : file.listFiles()) {
                    photoGallery.add(f.getPath());
                }
            }
            return photoGallery;
        }

        public GridAdapter (Context c){
            context = c;
        }
        @Override
        public int getCount() {
            return photoGallery.size();
        }

        @Override
        public Object getItem(int position) {
            return photoGallery.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView iv = new ImageView (context);
            //iv.setImageResource(populateGallery()[position]);

            return null;
        }
    }
**/

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