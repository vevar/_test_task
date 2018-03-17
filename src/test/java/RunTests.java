import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class RunTests {
    public static void main(String[] args) {


        JUnitCore jUnitCore = new JUnitCore();
        Result result = jUnitCore.run(TestControllerUpdate.class, TestSenderReport.class);
        System.out.println("Run tests: " + result.getRunCount());
        System.out.println("Failed test: " + result.getFailureCount());
        System.out.println("Success: " +result.wasSuccessful());
        System.out.println("Time: " + result.getRunTime());
    }
}
