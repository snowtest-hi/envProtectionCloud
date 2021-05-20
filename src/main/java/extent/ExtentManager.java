package extent;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentXReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ExtentManager {
    private static ExtentReports extent;
    private static final String OUTPUT_FOLDER = "test-output/";

    public static ExtentReports getInstance(String filePath) {
        if (extent == null)
            createInstance(filePath);
        return extent;
    }


    public static void createInstance(String filePath) {
        extent = new ExtentReports();
        File reportDir = new File(OUTPUT_FOLDER);
        if (!reportDir.exists() && !reportDir.isDirectory()) {
            reportDir.mkdir();
        }
        extent.attachReporter(createHtmlReporter(filePath), createExtentXReporter());
        extent.setSystemInfo("os", "Linux");
    }

    public static ExtentHtmlReporter createHtmlReporter(String filePath){
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filePath);
        //报表位置
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
               //使报表上的图表可见
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(filePath);
        htmlReporter.config().setCSS(".node.level-1  ul{ display:none;} .node.level-1.active ul{display:block;}");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("项目测试报告");
        return htmlReporter;
    }

    public static ExtentXReporter createExtentXReporter() {
        ExtentXReporter extentx = new ExtentXReporter("127.0.0.1",27017);
        extentx.config().setProjectName("test1");
        extentx.config().setReportName("Build-1224");
        extentx.config().setServerUrl("http://localhost:1337");
        return extentx;
    }
}
