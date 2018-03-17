import org.junit.Assert;
import org.junit.Test;
import report.ReportUpdate;
import service.ControllerUpdate;
import service.SenderReport;

public class TestSenderReport extends Assert {

    @Test
    public void sendTestMail(){
        ControllerUpdate controllerUpdate = new ControllerUpdate("src\\test\\resources\\manyEach\\yesterday",
                "src\\test\\resources\\manyEach\\today");

        ReportUpdate reportUpdate = controllerUpdate.checkUpdateHTML();

        SenderReport senderReport = new SenderReport();
        String mesBefore = "Здравствуйте, дорогая и.о. секретаря \n";
        senderReport.sendEmail("ariasoftTest123@gmail.com",mesBefore + reportUpdate.getString(),
                "Отчет об обновлениях");
    }
}
