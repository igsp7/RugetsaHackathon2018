package com.example.igor.rugetsahackathon2018;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Node {
    private String name;
    private HashMap<String,Edge> neighborsHM = new HashMap<>();
    private HashMap<Double,Edge> neighborsDistanceHM = new HashMap<>();
    private ArrayList<Edge> edgesSorted = new ArrayList<>();
    private Node previousNode;

    private float latitude;
    private float longitude;

    private boolean isVisited;
    private boolean visitedDijkstra;

    public boolean isVisited() {
        return isVisited;
    }

    public boolean isVisitedDijkstra() {
        return visitedDijkstra;
    }

    public void setVisitedDijkstra(boolean visitedDijkstra) {
        this.visitedDijkstra = visitedDijkstra;
    }

    public Node(String name, float latitude, float longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isVisited=false;
        this.visitedDijkstra=false;
    }

    public String getName() {
        return name;
    }

    public void setVisited(boolean visited) {
        this.isVisited = visited;
    }

    public HashMap<Double, Edge> getNeighborsDistanceHM() {
        return neighborsDistanceHM;
    }
    public void setPreviousNode(Node node){
        this.previousNode=node;
    }

    public Node getPreviousNode() {
        return previousNode;
    }

    public HashMap<String,Edge> getNeighbors() {
        return neighborsHM;
    }
    public void addNeighborsWithDistance()
    {
        Set set = neighborsHM.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mEntry = (Map.Entry) iterator.next();
            Edge edge = (Edge) mEntry.getValue();
            neighborsDistanceHM.put(edge.getWeight(),edge);
        }
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
    public void addEdgesSorted(Edge edge){
        edgesSorted.add(edge);
    }

    public ArrayList<Edge> getEdgesSorted() {
        return edgesSorted;
    }
    public void setToArrayList(Edge edge){
        edgesSorted.add(edge);
    }
    public void clearSortedAL(){
        edgesSorted.clear();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(obj == this) return true;
        if(!(obj instanceof Node)) return false;
        Node o = (Node) obj;
        return o.name == this.name;
    }
}


