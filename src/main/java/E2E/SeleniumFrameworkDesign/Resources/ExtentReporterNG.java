package E2E.SeleniumFrameworkDesign.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getReportObject() { //static methods can be accessed without even creating objects

		// ExtentSparKReporter is helper class which generate/creates report file and
		// updates the files content according to our requirements
		String filePath = System.getProperty("user.dir") + "//reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(filePath);
		reporter.config().setReportName("Web Automation for Products");
		reporter.config().setDocumentTitle("Web Automation Results");

		// ExtenReports class is main class which takes ExtentSparKReporter class object
		// and mainly responsible for create& consolidate report for your test execution
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Framework Author:", "Snehal Edake");
		
		return extent;

	}

}
