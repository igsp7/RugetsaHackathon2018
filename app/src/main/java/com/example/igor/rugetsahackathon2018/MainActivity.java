package com.example.igor.rugetsahackathon2018;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

   private ArrayList<Node> unvisited = new ArrayList<>();
   private ArrayList<Node> visited = new ArrayList<>();
   private double travelledDistance = 0;

   private ArrayList<Node> dijkstraAlUnvisited;

    private Button button;
   private Button button1;

    //The return edge of returnShortestEdge method
    private Edge returningEdge;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        button=findViewById(R.id.button);
        button1 = findViewById(R.id.button2);

        InitializeNodes();
        Log.e("hey","hey");

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

        Serres.addNeighborsWithDistance();

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



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeSortedArrayListToEachNode();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateShortestPath();
                Log.e("Distance",travelledDistance+"");
            }
        });

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
    public void makeSortedArrayListToEachNode()
    {
        ArrayList<Node> allNodes = new ArrayList<>();
        ArrayList<Node> visited = new ArrayList<>();

        Log.e("hey","hey");

        HashMap<String,Edge> neighborsHP;

        Set set = networkHP.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            Map.Entry mEntry = (Map.Entry) iterator.next();
            Node node = (Node)mEntry.getValue();
            neighborsHP= node.getNeighbors();
            node.clearSortedAL();

            Set set1 = neighborsHP.entrySet();
            Iterator iterator1 = set1.iterator();
            while (iterator1.hasNext()) {
                    Map.Entry mEntry1 = (Map.Entry) iterator1.next();
                    Edge edge = (Edge) mEntry1.getValue();
                    node.setToArrayList(edge);
            }
            Collections.sort(node.getEdgesSorted(),new Comparator<Edge>(){
                public int compare(Edge edge, Edge edge1) {
                    return edge.getWeight()> edge1.getWeight() ? 1 : (edge.getWeight() < edge1.getWeight()) ? -1 : 0;
                }
            });
            for(int i=0;i<node.getEdgesSorted().size();i++){
                Log.e(node.getName(),node.getEdgesSorted().get(i).getWeight()+"\n");
            }
        }
    }
    public void calculateShortestPath()
    {
        Node start = networkHP.get("Serres");
        start.setVisited(true);
        visited.clear();
        unvisited.clear();

        unvisited.addAll(networkHP.values());
        unvisited.remove(start);
        visited.add(start);

        Node currentNode = start;

        while(unvisited.size()>0){
            Edge shortestEdge = returnShortestEdge(currentNode);
            if(shortestEdge!=null) {
                currentNode=shortestEdge.getNeighbor();
                currentNode.setVisited(true);
                visited.add(currentNode);
                unvisited.remove(currentNode);
                travelledDistance += shortestEdge.getWeight();
            }
            else{
                dijkstra(currentNode);
                ArrayList<Node> visitedNodes = new ArrayList<>();
                Node visitingNode = unvisited.get(0);
                visitedNodes.addAll(getHashMapRoute(visitingNode,currentNode));
                visited.addAll(visitedNodes);

                currentNode=visitingNode;
                unvisited.remove(currentNode);

            }

        }
        Node endingNode = networkHP.get("Provatas");
//        dijkstra(endingNode);
        for(int i=0;i<visited.size();i++)
            Log.e("visited:",visited.get(i).getName());
    }
    public Edge returnShortestEdge(Node node){

        for(int i = 0;i<=node.getEdgesSorted().size();i++) {
            if(i == node.getEdgesSorted().size())
                returningEdge = null;
            else{
                if (!node.getEdgesSorted().get(i).getNeighbor().isVisited()) {
                    returningEdge = node.getEdgesSorted().get(i);
                    break;
                }

            }

        }
        return returningEdge;
    }
    public void dijkstra(Node startingPoing) {
        HashMap<Node,Double> dijkstraHP = new HashMap<>();
        dijkstraAlUnvisited = new ArrayList<>();





        ArrayList<Node> dijkstraAlVisited = new ArrayList<>();
        dijkstraAlUnvisited.addAll(networkHP.values());
        for(int i = 0 ; i < dijkstraAlUnvisited.size(); i++)
        {
            dijkstraHP.put(dijkstraAlUnvisited.get(i),99999d);
        }
        dijkstraHP.put(startingPoing,0d);
        for(Map.Entry<Node,Double> entry : dijkstraHP.entrySet()){
            entry.getKey().setVisitedDijkstra(false);
            entry.getKey().setPreviousNode(null);
        }

        while(dijkstraAlUnvisited.size()>1){
            Node currentNode =nearestKnownVertex(dijkstraHP);
            ArrayList<Edge> nodeEdgesAlSorted = currentNode.getEdgesSorted();
            for(int i =0 ; i<nodeEdgesAlSorted.size(); i++){
                if(!nodeEdgesAlSorted.get(i).getNeighbor().isVisitedDijkstra()) {
                    Node visitingNode = nodeEdgesAlSorted.get(i).getNeighbor();
                        Double currentDistanceFromStart = dijkstraHP.get(visitingNode);
                        if (currentDistanceFromStart > nodeEdgesAlSorted.get(i).getWeight() + dijkstraHP.get(currentNode)) {
                            dijkstraHP.put(visitingNode, nodeEdgesAlSorted.get(i).getWeight() + dijkstraHP.get(currentNode));
                            visitingNode.setPreviousNode(currentNode);
                        }



                }
            }
            dijkstraAlVisited.add(currentNode);
            dijkstraAlUnvisited.remove(currentNode);
            currentNode.setVisitedDijkstra(true);
        }

    }
    public Node nearestKnownVertex(HashMap<Node,Double> dijkstraHP){

        Map.Entry<Node,Double> min = null;
        for(Map.Entry<Node,Double> entry : dijkstraHP.entrySet())
            if(min == null || (min.getValue() > entry.getValue() && !entry.getKey().isVisitedDijkstra())){
                min=entry;
            }
        return min.getKey();
    }
    public ArrayList<Node> getHashMapRoute(Node node,Node currentNode) {
        ArrayList<Node> alRoute = new ArrayList<>();
        while(node!=currentNode) {
            alRoute.add(node);
            Edge edge = node.getNeighbors().get(node.getPreviousNode().getName());
            travelledDistance +=edge.getWeight();
            node = node.getPreviousNode();
        }
        Collections.reverse(alRoute);
        return alRoute;

    }

}
