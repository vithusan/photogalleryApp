package com.example.photogalleryapp;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class homepage extends AppCompatActivity implements View.OnClickListener{
    static final int CAMERA_REQUEST_CODE = 1;
    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private String currentPhotoPath = null;
    private ArrayList<String> photoGallery;
    private int currentPhotoIndex = 0;
    public static final int SEARCH_ACTIVITY_REQUEST_CODE = 0;
    ImageView image;
    TextView Exif;
    String imagefile = "/Android/data/com.example.photogalleryapp/files/Pictures";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Button btnLeft = (Button)findViewById(R.id.btnLeft);
        Button btnRight = (Button)findViewById(R.id.btnRight);
        ImageButton backbtn = (ImageButton) findViewById(R.id.imageButton3);
        Button btngallery = (Button) findViewById(R.id.btngallery);

        btnLeft.setOnClickListener(this);
        btnRight.setOnClickListener(this);
        btngallery.setOnClickListener(galleryListener);
        backbtn.setOnClickListener(backListener);

        Date minDate = new Date(Long.MIN_VALUE);
        Date maxDate = new Date(Long.MAX_VALUE);
        photoGallery = populateGallery(minDate, maxDate);
        Log.d("onCreate, size", Integer.toString(photoGallery.size()));
        if (photoGallery.size() > 0)
            currentPhotoPath = photoGallery.get(currentPhotoIndex);
        displayPhoto(currentPhotoPath);

        //image = (ImageView)findViewById(R.id.imageView2);
        Exif = (TextView)findViewById(R.id.textView);

        //Bitmap bm = BitmapFactory.decodeFile(currentPhotoPath);
        //image.setImageBitmap(bm);

        //Exif.setText(ReadExif(currentPhotoPath));
        ///////////////////////////

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.photogalleryapp.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }


    private ArrayList<String> populateGallery(Date minDate, Date maxDate) {
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);


        //File file = new File(Environment.getExternalStorageDirectory()
                //.getAbsolutePath(), "/Android/data/com.example.photogalleryapp/files/Pictures");
        photoGallery = new ArrayList<String>();
        File[] fList = file.listFiles();
        if (fList != null) {
            for (File f : file.listFiles()) {
                photoGallery.add(f.getPath());
            }
        }
        return photoGallery;
    }

    private void displayPhoto(String path) {
        ImageView iv = (ImageView) findViewById(R.id.imageView2);
        iv.setImageBitmap(BitmapFactory.decodeFile(path));

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void onClick( View v) {
        switch (v.getId()) {
            case R.id.btnLeft:
                --currentPhotoIndex;
                break;
            case R.id.btnRight:
                ++currentPhotoIndex;
                break;
            default:
                break;
        }
        if (currentPhotoIndex < 0)
            currentPhotoIndex = 0;
        if (currentPhotoIndex >= photoGallery.size())
            currentPhotoIndex = photoGallery.size() - 1;

        currentPhotoPath = photoGallery.get(currentPhotoIndex);
        Log.d("phpotoleft, size", Integer.toString(photoGallery.size()));
        Log.d("photoleft, index", Integer.toString(currentPhotoIndex));
        displayPhoto(currentPhotoPath);
        Exif.setText(ReadExif(currentPhotoPath));
    }

    private View.OnClickListener galleryListener = new View.OnClickListener() {
        public void onClick(View v) {
            //Intent i = new Intent(homepage.this, galleryActivity.class);
            //startActivityForResult(i, SEARCH_ACTIVITY_REQUEST_CODE);
            //Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            //startActivityForResult(intent, 2);

            Intent photoPicker = new Intent(Intent.ACTION_GET_CONTENT);

            File PictureDir = new File (Environment.getExternalStorageDirectory()
                    .getAbsolutePath(), "/Android/data/com.example.photogalleryapp/files/Pictures");

            String picpath = PictureDir.getPath();

            Uri data = Uri.parse(picpath);

            photoPicker.setDataAndType(data, "image/*");

            startActivityForResult(photoPicker, 20);
        }
    };

    private View.OnClickListener backListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent i = new Intent(homepage.this, MainActivity.class);
            startActivityForResult(i, SEARCH_ACTIVITY_REQUEST_CODE);
        }
    };



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            //Bundle extras = data.getExtras();
            //Bitmap imageBitmap = (Bitmap) extras.get("data");
            //mImageView.setImageBitmap(imageBitmap);
            ImageView mImageView = (ImageView) findViewById(R.id.imageView2);
            mImageView.setImageBitmap(BitmapFactory.decodeFile(currentPhotoPath));
            Exif.setText(ReadExif(currentPhotoPath));
        }

    }

/**
    public void takePicture(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.photogalleryapp.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }
 **/


    public void revertfunc(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.photogalleryapp.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    String ReadExif(String file){
        String exif= "";

        try {
            ExifInterface exifInterface = new ExifInterface(file);
            exif += "\n DATESTAMP: " + exifInterface.getAttribute(ExifInterface.TAG_GPS_DATESTAMP);
            //exif += "\n TIMESTAMP: " + exifInterface.getAttribute(ExifInterface.TAG_GPS_TIMESTAMP);
            //exif += "\n LATITUDE: " + exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE);
            //exif += "\n LATITUDE_REF: " + exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF);
            //exif += "\n LONGITUDE: " + exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE);
            //exif += "\n LONGITUDE_REF: " + exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF);

            Toast.makeText(homepage.this, "",
                    Toast.LENGTH_LONG).show();
        }
        catch (IOException e){
            e.printStackTrace();
            Toast.makeText(homepage.this, e.toString(),
                    Toast.LENGTH_LONG).show();
        }
        return exif;
    }

}



