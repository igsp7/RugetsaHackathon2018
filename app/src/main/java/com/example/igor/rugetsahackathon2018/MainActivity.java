package com.example.igor.rugetsahackathon2018;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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

        //Adding the edges to aKamilla
        addEdgesToNode(aKamila,Provatas,22.6);
        addEdgesToNode(aKamila,kMitrousi,3.1);
        addEdgesToNode(aKamila,Koumaria,6.3);

        //Adding the edges to kKamila
        addEdgesToNode(kKamila,kMitrousi,7.3);
        addEdgesToNode(kKamila,Skoutari,3.5);
        addEdgesToNode(kKamila,Koumaria,5.8);

        //Adding the edges to Peponia
        addEdgesToNode(Peponia,Skoutari,4.2);
        addEdgesToNode(Peponia,AgEleni,4.5);
        addEdgesToNode(Peponia,Adelfiko,6.7);

        //Adding the edges to AgEleni
        addEdgesToNode(AgEleni,Skoutari,4.5);
        addEdgesToNode(AgEleni,Peponia,4.5);

        //Adding the edges to Koumaria
        addEdgesToNode(Koumaria,aKamila,6.3);
        addEdgesToNode(Koumaria,kMitrousi,5.5);
        addEdgesToNode(Koumaria,kKamila,5.8);
        addEdgesToNode(Koumaria,Adelfiko,2.5);

        //Adding the edges to Adelfiko
        addEdgesToNode(Adelfiko,Koumaria,2.5);
        addEdgesToNode(Adelfiko,Peponia,6.7);

        networkHP.put(Serres.getName(),Serres);
        networkHP.put(Provatas.getName(),Provatas);
        networkHP.put(kMitrousi.getName(),kMitrousi);
        networkHP.put(Skoutari.getName(),Skoutari);
        networkHP.put(aKamila.getName(),aKamila);
        networkHP.put(kKamila.getName(),kKamila);
        networkHP.put(Peponia.getName(),Peponia);
        networkHP.put(AgEleni.getName(),AgEleni);
        networkHP.put(Koumaria.getName(),Koumaria);
        networkHP.put(Adelfiko.getName(),Adelfiko);


        calculateShortestPath();

    }
    public void InitializeNodes() {
        networkHP = new HashMap<>();
        Serres = new Node("Serres",41.092083f,23.541016f);
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
    public HashMap<String,Node> getNetworkHP(){
        return networkHP;
    }
    public void calculateShortestPath()
    {
        ArrayList<Node> allNodes = new ArrayList<>();
        ArrayList<Node> visited = new ArrayList<>();
        HashMap<String,Edge> neighborsHP;
        Node start =networkHP.get("Serres");
        visited.add(start);

        Set set = networkHP.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            Map.Entry mEntry = (Map.Entry) iterator.next();
            allNodes.add((Node)mEntry.getValue());
            Node node = (Node)mEntry.getValue();
            neighborsHP=node.getNeighbors();

            Set set1 = neighborsHP.entrySet();
            Iterator iterator1 = set1.iterator();
            Edge minEdge =(Edge) node.getNeighbors().values().toArray()[0];
            Double min = minEdge.getWeight();
            while(iterator1.hasNext())
            {
                Map.Entry mEntry1 = (Map.Entry) iterator1.next();
                Edge edge = (Edge) mEntry1.getValue();
                Double weight = edge.getWeight();
                if(weight<min){
                    min=weight;
                    minEdge=edge;
                }
            }
            Log.e("MinEdge","The min edge of " + node.getName() + " is " + minEdge.getNeighbor().getName() + " : " + min);
        }



    }
}
