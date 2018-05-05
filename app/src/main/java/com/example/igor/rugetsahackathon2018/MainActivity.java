package com.example.igor.rugetsahackathon2018;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

   private Node Serres ;
   private Node Provatas;
   private Node aKamila;
   private Node kKamila;
   private Node kMitrousi;
   private Node Koumaria;
   private Node Skoutari;
   private Node Adelfiko;
   private Node AgEleni;
   private Node Peponia;
   private HashMap<String,Node> networkHP;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // InitializeNodes();
        //getting permissions for api>22
        isReadStoragePermissionGranted();
        FileOpener opener = new FileOpener("that.txt");

        ArrayList<Node> nodesFromFile = new ArrayList<>();
        nodesFromFile = opener.getOurFileTypeList();
       //basic printing data for checking
        Log.w("ALEXANDER TAG", nodesFromFile.get(0).getNeighbor("Provatas").getNeighbor().getName());
//        //Adding the edges to Serres
//        addEdgesToNode(Serres,Provatas,11.3);
//        addEdgesToNode(Serres,kMitrousi,8.8);
//        addEdgesToNode(Serres,Skoutari,7.3);
//
//        //Adding the edges to Provatas
//        addEdgesToNode(Provatas,Serres,11.3);
//        addEdgesToNode(Provatas,aKamila,22.6);
//
//        //Adding the edges to kMitrousi
//        addEdgesToNode(kMitrousi,Serres,8.8);
//        addEdgesToNode(kMitrousi,aKamila,3.1);
//        addEdgesToNode(kMitrousi,kKamila,7.3);
//        addEdgesToNode(kMitrousi,Koumaria,5.5);
//
//        //Adding the edges to Skoutari
//        addEdgesToNode(Skoutari,kKamila,3.5);
//        addEdgesToNode(Skoutari,Peponia,4.2);
//        addEdgesToNode(Skoutari,AgEleni,4.5);
//        addEdgesToNode(Skoutari,Serres,7.3);
//
//        //Adding the edges to A.Kamilla


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

    public void InitializeNodes() {
        networkHP = new HashMap<>();
        Serres = new Node("Serres",23.541016f,23.541016f);
        Provatas = new Node ("Provatas",41.068238f,23.390686f);
        aKamila = new Node ("aKamila",41.058320f,23.424134f);
        kKamila = new Node ("kKamila",41.020431f,23.483293f);
        kMitrousi = new Node("kMitrousi",41.058680f,23.457547f);
        Koumaria = new Node("Koumaria",41.016434f,23.434656f);
        Skoutari = new Node("Skoutari",41.020032f,23.520701f);
        Adelfiko = new Node("Adelfiko",41.014645f,23.457354f);
        AgEleni = new Node("AgEleni",41.003545f,23.559196f);
        Peponia = new Node("Peponia",40.988154f,23.516756f);
    }
    public void addEdgesToNode(Node targetNode,Node edgeNode,double weight) {
        Edge edge = new Edge(edgeNode,weight);
        targetNode.addNeighbor(edge);
    }
    public void putNodeToNetwork(Node node){
        networkHP.put(node.getName(),node);
    }
}
