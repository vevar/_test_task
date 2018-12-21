package report;


import java.util.ArrayList;
import java.util.List;

public class ReportUpdate extends Report {

    public enum TypeContent {
        EDITED_VALUE, NEW_VALUE, DELETED_VALUE
    }

    private List<String> editedFiles;
    private List<String> newFiles;
    private List<String> deletedFiles;


    public ReportUpdate() {
        setName("Report of updates");
        editedFiles = new ArrayList<String>();
        newFiles = new ArrayList<String>();
        deletedFiles = new ArrayList<String>();
    }

    public String getString() {
        String message = "За последние сутки во вверенных Вами сайтах произошли следующие изменения:\n\n";


        StringBuilder strDeleted = new StringBuilder("\nИсчезли следующие страницы:\n");
        for (String str : deletedFiles) {
            strDeleted.append(str);
            strDeleted.append("\n");
        }

        StringBuilder strNew = new StringBuilder("\nПоявились следующие новые страницы:\n");
        for (String str : newFiles) {
            strNew.append(str);
            strNew.append("\n");
        }

        StringBuilder strEdited = new StringBuilder("\nИзменились следующие страницы:\n");
        for (String str : editedFiles) {
            strEdited.append(str);
            strEdited.append("\n");
        }
        message += strDeleted.toString() + strNew.toString() + strEdited.toString() + "\n\n";

        return message;
    }

    public List<String> getEditedFiles() {
        return editedFiles;
    }

    public List<String> getNewFiles() {
        return newFiles;
    }

    public List<String> getDeletedFiles() {
        return deletedFiles;
    }

    public void addFile(TypeContent type, String value) {
        if (value == null)
            throw new NullPointerException("Value is incorrect");
        switch (type) {

            case EDITED_VALUE:
                editedFiles.add(value);
                break;

            case NEW_VALUE:
                newFiles.add(value);
                break;

            case DELETED_VALUE:
                deletedFiles.add(value);
                break;
        }
    }
}
