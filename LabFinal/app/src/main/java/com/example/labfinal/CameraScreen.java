package com.example.labfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.content.ClipData;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class CameraScreen extends AppCompatActivity  {




    int MY_CAMERA_PERMISSION_CODE = 777;
    int PICK_FROM_GALLERY = 999;


    final int CAMERA_REQUEST = 555;
    final int RESULT_LOAD_IMAGE = 666;
    GridView imageview;
    private ArrayList bitmapList = new ArrayList<>();
    Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_screen);


        if (ActivityCompat.checkSelfPermission(CameraScreen.this,
                android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(CameraScreen.this,
                    new String[]{
                            android.Manifest.permission.CAMERA},
                    MY_CAMERA_PERMISSION_CODE);
        }

        if (ActivityCompat.checkSelfPermission(
                CameraScreen.this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    CameraScreen.this,  new String[]{
                            android.Manifest.permission.READ_EXTERNAL_STORAGE,
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PICK_FROM_GALLERY);
        }


        imageview = findViewById(R.id.imageview);

        Button cam = findViewById(R.id.btnTake);
        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentValues values = new ContentValues();
                //values.put(MediaStore.Images.Media.TITLE, "Test Photo Title");
                // values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
                //
                imageUri = getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, CAMERA_REQUEST);
            }
        });


    }

    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK)
        {

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                //imageView.setImageBitmap(bitmap);
                bitmapList.add(bitmap);
                MyAdapter adapter = new MyAdapter(this, bitmapList);
                imageview = findViewById(R.id.imageview);
                imageview.setAdapter(adapter);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}