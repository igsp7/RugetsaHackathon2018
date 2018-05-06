package com.example.igor.rugetsahackathon2018;

import android.util.Log;

public class OurFileType {
    private String originName;
    private  String destName;
    private double kmInBetween;
    private  double originLat;
    private  double originLng;
    private double destLat;
    private  double destLng;

    private Node originNode;
    private Node destNode;
    private Edge destNodeNei;

    public OurFileType() {

    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }
    public void setDestName(String destName) {
        this.destName = destName;
    }

    public void setOriginLat(String lat) {
        this.originLat = Double.parseDouble(lat);
    }
    public void setOriginLng(String lng) {
        this.originLng = Double.parseDouble(lng);
    }
    public void setDestLat(String lat) {
        this.destLat = Double.parseDouble(lat);
    }
    public void setDestLng(String lng) {
        this.destLng = Double.parseDouble(lng);
    }
    public void setKmInBetween(String kmBetweenNodes) {
        this.kmInBetween = Double.parseDouble(kmBetweenNodes);
    }

    public Node getOriginNode() {

        //creating the destination node like from Serres to Provatas this is Provatas Node
        destNode = new Node(destName, destLat, destLng);

        //creating the origin node like from Serres to Provatas this is Serres Node
        originNode = new Node(originName, originLat, originLng);

        //Adding the weight, meaning the distance from Serres (Origin Node) to Provatas(Destination Node) which is Serres's neighbour
        destNodeNei = new Edge(destNode, kmInBetween);

        //Adding as neighbour to Serres the Provatas Edge so when you search for Provatas in Serres Node youll get the corresponding value which is the Edge of Serres = Provatas
        originNode.addNeighbor(destNodeNei);

        return originNode;
    }

    public Node getDestNode() {
        return destNode;
    }
    public double getKmInBetween() {
        return kmInBetween;
    }
}
