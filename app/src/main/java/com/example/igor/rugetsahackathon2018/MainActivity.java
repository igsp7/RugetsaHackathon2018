package com.example.igor.rugetsahackathon2018;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

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
        InitializeNodes();

        //Adding the edges to Serres
        addEdgesToNode(Serres,Provatas,11.3);
        addEdgesToNode(Serres,kMitrousi,8.8);
        addEdgesToNode(Serres,Skoutari,7.3);

        //Adding the edges to Provatas
        addEdgesToNode(Provatas,Serres,11.3);
        addEdgesToNode(Provatas,aKamila,22.6);

        //Adding the edges to kMitrousi
        addEdgesToNode(kMitrousi,Serres,8.8);
        addEdgesToNode(kMitrousi,aKamila,3.1);
        addEdgesToNode(kMitrousi,kKamila,7.3);
        addEdgesToNode(kMitrousi,Koumaria,5.5);

        //Adding the edges to Skoutari
        addEdgesToNode(Skoutari,kKamila,3.5);
        addEdgesToNode(Skoutari,Peponia,4.2);
        addEdgesToNode(Skoutari,AgEleni,4.5);
        addEdgesToNode(Skoutari,Serres,7.3);

        //Adding the edges to A.Kamilla


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
