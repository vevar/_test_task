import report.ReportUpdate;
import service.ControllerUpdate;
import service.SenderReport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Arguments are incorrect");
            return;
        }

        String oldData = args[0];
        String newDate = args[1];

        ControllerUpdate controllerUpdate = new ControllerUpdate(oldData, newDate);
        ReportUpdate reportUpdate = controllerUpdate.checkUpdateHTML();
        SenderReport senderReport = new SenderReport();

        System.out.println("Input email of receiver: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String mesBefore = "Здравствуйте, дорогая и.о. секретаря \n";
            senderReport.sendEmail(reader.readLine(), mesBefore + reportUpdate.getString(),
                    "Report about updates");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done!");
    }
}
