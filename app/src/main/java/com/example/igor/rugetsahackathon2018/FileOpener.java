package com.example.igor.rugetsahackathon2018;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileOpener {
    private String filename = null;
    //created a custom class for saving originNode, destNode and kmBetween
    //going to use regex to pass the extracted strings and create a list of OurFileType which then you can use to get all the node info you want.
    private ArrayList<OurFileType> ourFileTypeList = new ArrayList<>();
    //needed to get file from storage
    File sdcard = Environment.getExternalStorageDirectory();
    //the chosen file
    File fileFromDisk = null;

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

        try {
            reader = new BufferedReader(new FileReader(fileFromDisk));
            ArrayList<String> dataOrdered = new ArrayList<>();
            String text = null;
            for(OurFileType tempFile : ourFileTypeList) {
                while ((text = reader.readLine()) != null) {
                    Pattern regex = Pattern.compile("(-?\\d+(?:\\.\\d+)?)");
                    Matcher matcher = regex.matcher(text);
                    //matcher.group(1);
                    while(matcher.find()) {
                        dataOrdered.add(matcher.group(1));
                    }
                    tempFile.setOriginLat[0];
                    tempFile.setOriginLng[1];
                    tempFile.setDestLat[2];
                    tempFile.setDestLng[3];
                    tempFile.setKmInBetween[4];
                    //here you need to use regex to get the wanted information

                    //setThingsOnTempFile then ourFileTypeList.add(tempFile)
                    regex = Pattern.compile("([a-zA-Z]+\\.?)");
                    Matcher matcher2 = regex.matcher(text);
                    while(matcher2.find()){
                        System.out.println(matcher2.group(1));


                    }
                    tempFile.setOriginName[0];
                    tempFile.setDestName[1];
                }
                ourFileTypeList.add(tempFile);


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

    public ArrayList<OurFileType> getOurFileTypeList() {
        return ourFileTypeList;
    }
}
