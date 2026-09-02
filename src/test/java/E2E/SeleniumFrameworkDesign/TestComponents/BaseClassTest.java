package E2E.SeleniumFrameworkDesign.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import E2E.SeleniumFrameworkDesign.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClassTest {

	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {
		Properties properties = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//E2E//SeleniumFrameworkDesign//Resources//GlobalData.properties");
		properties.load(fis);
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: properties.getProperty("browser");
		System.out.println(browserName);
		boolean isHeadless = browserName.contains("headless");
		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if (isHeadless) {
				options.addArguments("--headless=new");
				options.addArguments("--window-size=1920,1080");
			}
			driver = new ChromeDriver(options);
		} else if (browserName.contains("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			WebDriverManager.firefoxdriver().setup();
			if (isHeadless) {
				options.addArguments("--headless");
				options.addArguments("--width=1920");
				options.addArguments("--height=1080");
			}
			driver = new FirefoxDriver(options);
		} else if (browserName.contains("edge")) {
			EdgeOptions options = new EdgeOptions();
			WebDriverManager.edgedriver().setup();

			if (isHeadless) {
				options.addArguments("--headless=new");
				options.addArguments("--window-size=1920,1080");
			}
			driver = new EdgeDriver(options);
		}
		if (!isHeadless) {
			driver.manage().window().maximize();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

		// converting .json file to String
		String jsonString = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// Converting String to HashMap using Jackson DataBind
		// for this we need to add Jackson Databind dependency from maven repository -
		// in pom.xml
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonString,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
		// data variable stores- {{map1},{map2}}

	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";

	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}

}
