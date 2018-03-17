package service;


import report.ReportUpdate;

import java.io.*;
import java.util.*;

public class ControllerUpdate implements ControllerService{

    private HashMap<String,List> oldData;
    private HashMap<String,List> newData;


    public ControllerUpdate(String oldPath, String newPath) {

        this.oldData = new HashMap<String, List>();
        this.newData = new HashMap<String, List>();
        setControllerData(oldPath, newPath);
    }

    public ControllerUpdate(HashMap<String,List> oldData, HashMap<String,List> newData) {
        this.oldData = oldData;
        this.newData = newData;
    }

    public ReportUpdate checkUpdateHTML() {

        ReportUpdate reportUpdate = new ReportUpdate();

        Set<Map.Entry<String, List>> oldDataSet = oldData.entrySet();

        for (Map.Entry<String, List> entry: oldDataSet){
          String url = entry.getKey();

          if (newData.containsKey(url)){
              if (newData.get(url).hashCode() != oldData.get(url).hashCode())
                  reportUpdate.addFile(ReportUpdate.TypeContent.EDITED_VALUE,url);
          }else
              reportUpdate.addFile(ReportUpdate.TypeContent.DELETED_VALUE, url);
          newData.remove(url);
        }

        Set<Map.Entry<String, List>> newDataSet = newData.entrySet();

        for (Map.Entry<String, List> entry: newDataSet)
            reportUpdate.addFile(ReportUpdate.TypeContent.NEW_VALUE,entry.getKey());

        return reportUpdate;
    }

    class FileHTMLFilter implements FilenameFilter {

        public boolean accept(File dir, String name) {
            return name.endsWith(".html");
        }
    }

    public void setControllerData(String oldPath, String newPath){
        try {
            setMapData(newPath, newData);
            setMapData(oldPath, oldData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setMapData(String path, HashMap<String, List> map) throws IOException {


        File dir = new File(path);
        if (!dir.exists())
            throw new NullPointerException("Path is incorrect");

        String[] URLs = dir.list(new FileHTMLFilter());
        if (URLs == null)
            return;

        File[] files = dir.listFiles(new FileHTMLFilter());
        if (files == null)
            return;

        int sizeURLs = URLs.length;

        for (int i = 0; i < sizeURLs; i++) {
            URLs[i] = "http://www.example.com/" + URLs[i];
        }

        for (int i = 0; i < sizeURLs; i++){

            BufferedReader reader = new BufferedReader(new FileReader(files[i]));
            ArrayList<String> arrayList = new ArrayList<String>();
            String str;

            while((str = reader.readLine())!=null){
                arrayList.add(str);
            }

            map.put(URLs[i],arrayList);
        }
    }
}
