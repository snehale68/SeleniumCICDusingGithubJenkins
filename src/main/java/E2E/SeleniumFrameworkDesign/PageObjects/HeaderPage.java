package E2E.SeleniumFrameworkDesign.PageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import E2E.SeleniumFrameworkDesign.ReusableComponents.ReusableComponents;

public class HeaderPage {

	WebDriver driver;

	public HeaderPage(WebDriver driver)

	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[routerlink*='cart']")
	WebElement goToCartIcon;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement goToMyOrders;

	public CartPage clickOnGoToCart() {
		goToCartIcon.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public MyOrdersPage clickOnMyOrders() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", goToMyOrders);
		goToMyOrders.click();
		MyOrdersPage myOrdersPage = new MyOrdersPage(driver);
		return myOrdersPage;
	}

}
