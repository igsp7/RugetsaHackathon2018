package com.example.igor.rugetsahackathon2018;

public class OurFileType {
    private String originName;
    private  String destName;
    private double kmInBetween;
    private  double originLat;
    private  double originLng;
    private double destLat;
    private  double destLng;

    private Node originNode = null;
    private Node destNode = null;


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
    public void setKmInBetween(String kmBetweenNodes) {
        this.kmInBetween = Double.parseDouble(kmBetweenNodes);
    }

    public Node getOriginNode() {
        return originNode;
    }
    public Node getDestNode() {
        return destNode;
    }
    public float getKmInBetween() {
        return kmBetweenNodes;
    }
}
