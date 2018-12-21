package service;

import org.junit.Assert;
import org.junit.Test;
import report.ReportUpdate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class TestControllerUpdate extends Assert {

    private HashMap<String, List> oldData = new HashMap<String, List>();
    private HashMap<String, List> newData = new HashMap<String, List>();
    private ControllerUpdate controllerUpdate = new ControllerUpdate(oldData, newData);

    @Test
    public void oneUpdateInEach() {

        controllerUpdate.setControllerData("src\\test\\resources\\oneEach\\yesterday\\",
                "src\\test\\resources\\oneEach\\today\\");

        ReportUpdate reportUpdate = controllerUpdate.checkUpdateHTML();

        List<String> newFiles = reportUpdate.getNewFiles();
        List<String> editedFiles = reportUpdate.getEditedFiles();
        List<String> deletedFiles = reportUpdate.getDeletedFiles();

        String[] newURLs = {"http://www.example.com/indexNew1.html"};
        String[] delURLs = {"http://www.example.com/indexDel1.html"};
        String[] editURLs = {"http://www.example.com/indexEdit1.html"};

        assertEquals(Arrays.asList(newURLs), newFiles);
        assertEquals(Arrays.asList(delURLs), deletedFiles);
        assertEquals(Arrays.asList(editURLs), editedFiles);
    }

    @Test
    public void moreUpdateInEach() {
        controllerUpdate.setControllerData("src\\test\\resources\\manyEach\\yesterday\\",
                "src\\test\\resources\\manyEach\\today\\");

        ReportUpdate reportUpdate = controllerUpdate.checkUpdateHTML();

        List<String> newFiles = reportUpdate.getNewFiles();
        List<String> editedFiles = reportUpdate.getEditedFiles();
        List<String> deletedFiles = reportUpdate.getDeletedFiles();

        String[] newURLs = {"http://www.example.com/indexNew1.html", "http://www.example.com/indexNew2.html",
                "http://www.example.com/indexNew3.html"};
        String[] delURLs = {"http://www.example.com/indexDel2.html", "http://www.example.com/indexDel1.html"};
        String[] editURLs = {"http://www.example.com/indexEdit1.html", "http://www.example.com/indexEdit2.html"};

        assertEquals(new HashSet<String>(Arrays.asList(newURLs)), new HashSet<String>(newFiles));
        assertEquals(new HashSet<String>(Arrays.asList(delURLs)), new HashSet<String>(deletedFiles));
        assertEquals(new HashSet<String>(Arrays.asList(editURLs)), new HashSet<String>(editedFiles));

    }

    @Test
    public void updateEdit() {
        controllerUpdate.setControllerData("src\\test\\resources\\updateEdit\\yesterday\\",
                "src\\test\\resources\\updateEdit\\today\\");

        ReportUpdate reportUpdate = controllerUpdate.checkUpdateHTML();

        List<String> newFiles = reportUpdate.getNewFiles();
        List<String> editedFiles = reportUpdate.getEditedFiles();
        List<String> deletedFiles = reportUpdate.getDeletedFiles();


        String[] editURLs = {"http://www.example.com/indexEdit1.html", "http://www.example.com/indexEdit2.html"};

        assertTrue(newFiles.isEmpty());
        assertTrue(deletedFiles.isEmpty());
        assertEquals(new HashSet<String>(Arrays.asList(editURLs)), new HashSet<String>(editedFiles));
    }

    @Test
    public void updateDeleted() {
        controllerUpdate.setControllerData("src\\test\\resources\\updateDeleted\\yesterday\\",
                "src\\test\\resources\\updateDeleted\\today\\");

        ReportUpdate reportUpdate = controllerUpdate.checkUpdateHTML();

        List<String> newFiles = reportUpdate.getNewFiles();
        List<String> editedFiles = reportUpdate.getEditedFiles();
        List<String> deletedFiles = reportUpdate.getDeletedFiles();

        String[] delURLs = {"http://www.example.com/indexDel2.html", "http://www.example.com/indexDel1.html"};

        assertTrue(newFiles.isEmpty());
        assertTrue(editedFiles.isEmpty());
        assertEquals(new HashSet<String>(Arrays.asList(delURLs)), new HashSet<String>(deletedFiles));

    }

    @Test
    public void updateNew() {
        controllerUpdate.setControllerData("src\\test\\resources\\updateNew\\yesterday\\",
                "src\\test\\resources\\updateNew\\today\\");

        ReportUpdate reportUpdate = controllerUpdate.checkUpdateHTML();

        List<String> newFiles = reportUpdate.getNewFiles();
        List<String> editedFiles = reportUpdate.getEditedFiles();
        List<String> deletedFiles = reportUpdate.getDeletedFiles();

        String[] newURLs = {"http://www.example.com/indexNew1.html", "http://www.example.com/indexNew2.html",
                "http://www.example.com/indexNew3.html"};

        assertTrue(deletedFiles.isEmpty());
        assertTrue(editedFiles.isEmpty());
        assertEquals(new HashSet<String>(Arrays.asList(newURLs)), new HashSet<String>(newFiles));

    }
}
