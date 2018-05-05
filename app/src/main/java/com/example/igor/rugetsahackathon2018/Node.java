package com.example.igor.rugetsahackathon2018;

import java.util.HashMap;

public class Node {
    private String name;
    private HashMap<String,Edge> neighborsHM = new HashMap<>();

    private float latitude;
    private float longitude;


    public Node(String name, float latitude, float longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
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

    public void setCoordinates(float latitude,float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

}


