package com.example.igor.rugetsahackathon2018;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class Node {
    private String name;
    private HashMap<String,Edge> neighborsHM = new HashMap<>();
    private ArrayList<Node> visitedNodes = new ArrayList<>();

    private double latitude;
    private double longitude;

    public Node() {

    }
    public Node(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }
    public void printData() {
        Log.w("ALEXANDER PRINTING INSIDE NODE CLASS", name + latitude + longitude + neighborsHM.get("Provatas").getNeighbor().getName());
    }

    public HashMap<String,Edge> getNeighbors() {
        return neighborsHM;
    }

    public Edge getNeighbor(String neighborName)
    {
        return neighborsHM.get(neighborName);
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addNeighbor(Edge neighbor){
        neighborsHM.put(neighbor.getNeighbor().getName(),neighbor);
    }
    public void clearNeighbors(){
        neighborsHM.clear();
    }

    public void setCoordinates(double latitude,double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

}


