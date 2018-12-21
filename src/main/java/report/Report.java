package report;

abstract public class Report{

    private int id;
    private String name;
    private String message;

    public Report(int id, String nameReport, String message) {
        if(nameReport == null)
            throw new NullPointerException("Name of report is incorrect");
        if(message == null)
            throw new NullPointerException("Message of report is incorrect");

        this.id = id;
        this.name = nameReport;
        this.message = message;
    }

    Report() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        if(name == null)
            throw new NullPointerException("Name is incorrect");
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message){
        if(message == null)
            throw new NullPointerException("Message is incorrect");
        this.message = message;
    }

    abstract public String getString();

}
