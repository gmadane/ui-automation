package report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ReportManager {

    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private ReportManager() {
        // Prevent instantiation
    }

    // Start a test and assign it to the ThreadLocal
    public static synchronized ExtentTest startTest(String testName, String description) {
        ExtentTest extentTest = extent.createTest(testName, description);
        test.set(extentTest);
        return extentTest;
    }

    // Get the current test
    public static synchronized ExtentTest getTest() {
        return test.get();
    }

    // Flush the report (usually called at the end of the suite)
    public static synchronized void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}
