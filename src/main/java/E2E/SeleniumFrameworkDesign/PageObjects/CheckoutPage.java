package E2E.SeleniumFrameworkDesign.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import E2E.SeleniumFrameworkDesign.ReusableComponents.ReusableComponents;

public class CheckoutPage extends ReusableComponents {
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	@FindBy(xpath = "//button[contains(@class,'ta-item')][2]")
	WebElement selectCountry;

	@FindBy(css = ".action__submit")
	WebElement submitButton;

	By results = By.cssSelector(".ta-results");

	public void selectCountry(String countryName) {

		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForFindByElementToAppear(results);
		selectCountry.click();
	}

	public ConfirmationPage submitOrder() {
		submitButton.click();
		return new ConfirmationPage(driver);
	}
}
