package com.example.igor.rugetsahackathon2018;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.util.ArrayList;

public class MapsMainActivity extends AppCompatActivity {

    Button btn1;
    Button btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_main);
        btn1 = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);
        isReadStoragePermissionGranted();

        FileOpener opener = new FileOpener("testAlgo.txt");
        ArrayList<Node> nodesFromFile = new ArrayList<>();
        nodesFromFile = opener.getOurFileTypeList();
        Log.e("Tag ALEXANDER", nodesFromFile.get(0).getName());
        Log.e("Tag ALEXANDER", nodesFromFile.get(1).getName());
        Log.e("Tag ALEXANDER", nodesFromFile.get(2).getName());


        Log.w("ALEXANDER", nodesFromFile.get(2).getName());
        final Intent mapsMan = new Intent(MapsMainActivity.this, MapsActivityManual.class);
        final Intent maps = new Intent(MapsMainActivity.this, MapsActivity.class);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MapsMainActivity.this.startActivity(maps);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MapsMainActivity.this.startActivity(mapsMan);
            }
        });


    }
        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            switch (requestCode) {
                case 2:
                    Log.d("fdf", "External storage2");
                    if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                        Log.v("fsdf","Permission: "+permissions[0]+ "was "+grantResults[0]);

                    }else{

                    }
                    break;

                case 3:
                    Log.d("asd", "External storage1");
                    if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                        Log.v("asdad","Permission: "+permissions[0]+ "was "+grantResults[0]);
                        //resume tasks needing this permission

                    }else{
                        //progress.dismiss();
                    }
                    break;
            }
        }
        public  boolean isReadStoragePermissionGranted() {
            if (Build.VERSION.SDK_INT >= 23) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED) {
                    Log.v("that","Permission is granted1");
                    return true;
                } else {

                    Log.v("that","Permission is revoked1");
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
                    return false;
                }
            }
            else { //permission is automatically granted on sdk<23 upon installation
                Log.v("that","Permission is granted1");
                return true;
            }
        }
    }

