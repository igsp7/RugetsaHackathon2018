package com.example.igor.rugetsahackathon2018;

public class Edge {
    private Node neighbor;
    private double weight;
    private boolean isNull = false;

    public Edge(){

    }

    public Edge(Node neighbor, double weight) {
        this.neighbor = neighbor;
        this.weight = weight;
    }
    public Edge(Node neighbor)
    {
        this.neighbor=neighbor;
    }

    public Node getNeighbor() {
        return neighbor;
    }

    public double getWeight() {
        return weight;
    }

    public void setNeighbor(Node neighbor) {
        this.neighbor = neighbor;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
