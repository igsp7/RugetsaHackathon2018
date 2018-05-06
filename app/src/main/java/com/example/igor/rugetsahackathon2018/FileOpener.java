package com.example.igor.rugetsahackathon2018;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileOpener {
    private String filename = null;
    //created a custom class for saving originNode, destNode and kmBetween
    //going to use regex to pass the extracted strings and create a list of OurFileType which then you can use to get all the node info you want.
    private OurFileType ourFileType = new OurFileType();
    private ArrayList<Node> nodes = new ArrayList<>();
    //needed to get file from storage
    File sdcard = Environment.getExternalStorageDirectory();
    //the chosen file
    File fileFromDisk = null;
    public FileOpener() {

    }
    public FileOpener(String filenameString) {
        this.filename = filenameString;
        fileFromDisk =  new File(sdcard,filename);
        openFile(fileFromDisk);
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    //basic file opening functionality
    public void openFile(File file) {
        BufferedReader reader = null;
        int i = -1;
        try {
            reader = new BufferedReader(new FileReader(fileFromDisk));
            ArrayList<String> dataOrdered = new ArrayList<>();
            String text = null;
            while ((text = reader.readLine()) != null) {
                dataOrdered.clear();

                //REGEX TO MATCH NUMBER VALUES...
                Pattern regex = Pattern.compile("(-?\\d+(?:\\.\\d+)?)");
                Matcher matcher = regex.matcher(text);
                while(matcher.find()) {
                    dataOrdered.add(matcher.group(1));
                }
                ourFileType.setOriginLat(dataOrdered.get(0));
                ourFileType.setOriginLng(dataOrdered.get(1));
                ourFileType.setDestLat(dataOrdered.get(2));
                ourFileType.setDestLng(dataOrdered.get(3));
                ourFileType.setKmInBetween(dataOrdered.get(4));
                //here you need to use regex to get the wanted information
                dataOrdered.clear();


                //TODO: I H A T E REGEX
                //Regex to match text values
                regex = Pattern.compile("([a-zA-Z]+\\.?[\\s]?+[a-zA-Z]+[\\s]?[a-zA-Z]+)");
                Matcher matcher2 = regex.matcher(text);
                while(matcher2.find()){
                    dataOrdered.add(matcher2.group(1));


                }
                ourFileType.setOriginName(dataOrdered.get(0));
                ourFileType.setDestName(dataOrdered.get(1));
                boolean isEqual = false;
                if(nodes!= null) {
                    for (Node each : nodes) {
                        if(each.getName().equals(dataOrdered.get(0)))
                            isEqual = true;
                    }
                }
                if (isEqual) {
                    nodes.get(i).addNeighbor(ourFileType.setEdgeOnly());
                }
                else {
                    nodes.add(ourFileType.getOriginNode());
                    i++;
                }//Log.w("Alexanders Tag",nodes.get(0).getNeighbor("Provatas").getNeighbor().getName());

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }

    }

    public ArrayList<Node> getOurFileTypeList() {
        return nodes;
    }
}