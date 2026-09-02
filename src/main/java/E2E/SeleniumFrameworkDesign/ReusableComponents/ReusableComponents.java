package E2E.SeleniumFrameworkDesign.ReusableComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import E2E.SeleniumFrameworkDesign.PageObjects.CartPage;
import E2E.SeleniumFrameworkDesign.PageObjects.HeaderPage;
import E2E.SeleniumFrameworkDesign.PageObjects.MyOrdersPage;

public class ReusableComponents {

	WebDriver driver;
	HeaderPage headerPage;

	public ReusableComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		   headerPage = new HeaderPage(driver);
	}

	public void waitForFindByElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void waitForWebElementToBeClickable(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}	

	public void waitForElementToDisappear(WebElement ele) throws InterruptedException {
		Thread.sleep(1000);
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		//wait.until(ExpectedConditions.invisibilityOf(ele));
	}

	public CartPage clickOnGoToCart() {

        return headerPage.clickOnGoToCart();
    }
	
	public MyOrdersPage clickOnMyOrders() {

        return headerPage.clickOnMyOrders();
    }
}
