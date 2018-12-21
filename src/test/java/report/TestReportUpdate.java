package report;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestReportUpdate extends Assert {

    private ReportUpdate report;

    private static String NEW_FILE_1 = "http://www.example.com/indexNew1.html";
    private static String NEW_FILE_2 = "http://www.example.com/indexNew2.html";

    private static String DELETED_FILE_1 = "http://www.example.com/indexDel1.html";
    private static String DELETED_FILE_2 = "http://www.example.com/indexDel2.html";

    private static String EDITED_FILE_1 = "http://www.example.com/indexEdit1.html";
    private static String EDITED_FILE_2 = "http://www.example.com/indexEdit2.html";

    @Before
    public void init() {
        report = new ReportUpdate();
    }


    @Test
    public void testOneNewValue() {
        report.addFile(ReportUpdate.TypeContent.NEW_VALUE, NEW_FILE_1);

        List<String> newFiles = report.getNewFiles();
        List<String> deletedFiles = report.getDeletedFiles();
        List<String> editedFiles = report.getEditedFiles();

        assertEquals(Collections.singletonList(NEW_FILE_1), newFiles);
        assertEquals(Collections.EMPTY_LIST, deletedFiles);
        assertEquals(Collections.EMPTY_LIST, editedFiles);
    }


    @Test
    public void testManyNewValue() {
        report.addFile(ReportUpdate.TypeContent.NEW_VALUE, NEW_FILE_1);
        report.addFile(ReportUpdate.TypeContent.NEW_VALUE, NEW_FILE_2);

        List<String> newFiles = report.getNewFiles();
        List<String> deletedFiles = report.getDeletedFiles();
        List<String> editedFiles = report.getEditedFiles();

        assertEquals(Arrays.asList(NEW_FILE_1, NEW_FILE_2), newFiles);
        assertEquals(Collections.EMPTY_LIST, deletedFiles);
        assertEquals(Collections.EMPTY_LIST, editedFiles);
    }

    @Test
    public void testOneDeletedValue() {
        report.addFile(ReportUpdate.TypeContent.DELETED_VALUE, DELETED_FILE_1);

        List<String> newFiles = report.getNewFiles();
        List<String> deletedFiles = report.getDeletedFiles();
        List<String> editedFiles = report.getEditedFiles();

        assertEquals(Collections.EMPTY_LIST, newFiles);
        assertEquals(Collections.singletonList(DELETED_FILE_1), deletedFiles);
        assertEquals(Collections.EMPTY_LIST, editedFiles);
    }


    @Test
    public void testManyDeletedValue() {
        report.addFile(ReportUpdate.TypeContent.DELETED_VALUE, DELETED_FILE_1);
        report.addFile(ReportUpdate.TypeContent.DELETED_VALUE, DELETED_FILE_2);

        List<String> newFiles = report.getNewFiles();
        List<String> deletedFiles = report.getDeletedFiles();
        List<String> editedFiles = report.getEditedFiles();

        assertEquals(Collections.EMPTY_LIST, newFiles);
        assertEquals(Arrays.asList(DELETED_FILE_1, DELETED_FILE_2), deletedFiles);
        assertEquals(Collections.EMPTY_LIST, editedFiles);
    }

    @Test
    public void testOneEditedValue() {
        report.addFile(ReportUpdate.TypeContent.EDITED_VALUE, EDITED_FILE_1);

        List<String> newFiles = report.getNewFiles();
        List<String> deletedFiles = report.getDeletedFiles();
        List<String> editedFiles = report.getEditedFiles();

        assertEquals(Collections.EMPTY_LIST, newFiles);
        assertEquals(Collections.EMPTY_LIST, deletedFiles);
        assertEquals(Collections.singletonList(EDITED_FILE_1), editedFiles);
    }


    @Test
    public void testManyEditedValue() {
        report.addFile(ReportUpdate.TypeContent.EDITED_VALUE, EDITED_FILE_1);
        report.addFile(ReportUpdate.TypeContent.EDITED_VALUE, EDITED_FILE_2);

        List<String> newFiles = report.getNewFiles();
        List<String> deletedFiles = report.getDeletedFiles();
        List<String> editedFiles = report.getEditedFiles();

        assertEquals(Collections.EMPTY_LIST, newFiles);
        assertEquals(Collections.EMPTY_LIST, deletedFiles);
        assertEquals(Arrays.asList(EDITED_FILE_1, EDITED_FILE_2), editedFiles);
    }

    @Test
    public void testManyEachValue() {
        report.addFile(ReportUpdate.TypeContent.NEW_VALUE, NEW_FILE_1);
        report.addFile(ReportUpdate.TypeContent.NEW_VALUE, NEW_FILE_2);

        report.addFile(ReportUpdate.TypeContent.DELETED_VALUE, DELETED_FILE_1);
        report.addFile(ReportUpdate.TypeContent.DELETED_VALUE, DELETED_FILE_2);

        report.addFile(ReportUpdate.TypeContent.EDITED_VALUE, EDITED_FILE_1);
        report.addFile(ReportUpdate.TypeContent.EDITED_VALUE, EDITED_FILE_2);

        List<String> newFiles = report.getNewFiles();
        List<String> deletedFiles = report.getDeletedFiles();
        List<String> editedFiles = report.getEditedFiles();

        assertEquals(Arrays.asList(NEW_FILE_1, NEW_FILE_2), newFiles);
        assertEquals(Arrays.asList(DELETED_FILE_1, DELETED_FILE_2), deletedFiles);
        assertEquals(Arrays.asList(EDITED_FILE_1, EDITED_FILE_2), editedFiles);
    }

    @Test
    public void testGetString(){
        report.addFile(ReportUpdate.TypeContent.NEW_VALUE, NEW_FILE_1);
        report.addFile(ReportUpdate.TypeContent.NEW_VALUE, NEW_FILE_2);

        report.addFile(ReportUpdate.TypeContent.DELETED_VALUE, DELETED_FILE_1);
        report.addFile(ReportUpdate.TypeContent.DELETED_VALUE, DELETED_FILE_2);

        report.addFile(ReportUpdate.TypeContent.EDITED_VALUE, EDITED_FILE_1);
        report.addFile(ReportUpdate.TypeContent.EDITED_VALUE, EDITED_FILE_2);

        String expected = "За последние сутки во вверенных Вами сайтах произошли следующие изменения:\n" +
                "\n" +
                "\n" +
                "Исчезли следующие страницы:\n" +
                "http://www.example.com/indexDel1.html\n" +
                "http://www.example.com/indexDel2.html\n" +
                "\n" +
                "Появились следующие новые страницы:\n" +
                "http://www.example.com/indexNew1.html\n" +
                "http://www.example.com/indexNew2.html\n" +
                "\n" +
                "Изменились следующие страницы:\n" +
                "http://www.example.com/indexEdit1.html\n" +
                "http://www.example.com/indexEdit2.html" +
                "\n" +
                "\n" +
                "\n";
        String result = report.getString();

        assertEquals(expected,result);
    }
}

