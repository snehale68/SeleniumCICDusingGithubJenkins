package E2E.SeleniumFrameworkDesign.PageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import E2E.SeleniumFrameworkDesign.ReusableComponents.ReusableComponents;

public class LandingPage extends ReusableComponents {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement loginButton;

	// ng-tns-c4-46 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr
	// toast-error
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;

	public ProductCataloguePage loginIntoApp(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		waitForWebElementToBeClickable(loginButton);
		loginButton.click();
		System.out.println("User is logged in");
		ProductCataloguePage productCataloguePage = new ProductCataloguePage(driver);
		return productCataloguePage;
	}

	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}

}