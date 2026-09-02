package E2E.SeleniumFrameworkDesign.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.hc.client5.http.entity.mime.Header;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import E2E.SeleniumFrameworkDesign.PageObjects.CartPage;
import E2E.SeleniumFrameworkDesign.PageObjects.CheckoutPage;
import E2E.SeleniumFrameworkDesign.PageObjects.ConfirmationPage;
import E2E.SeleniumFrameworkDesign.PageObjects.HeaderPage;
import E2E.SeleniumFrameworkDesign.PageObjects.LandingPage;
import E2E.SeleniumFrameworkDesign.PageObjects.MyOrdersPage;
import E2E.SeleniumFrameworkDesign.PageObjects.ProductCataloguePage;
import E2E.SeleniumFrameworkDesign.TestComponents.BaseClassTest;

public class LoginAndPlaceOrderTest extends BaseClassTest {
	String productName = "ZARA COAT 3";
	String countryName = "India";
	String confirmMessage = "THANKYOU FOR THE ORDER.";
	String orderFilePath=(System.getProperty("user.dir") + "//src//test//java//E2E//SeleniumFrameworkDesign//data//Order.json");

	@Test(dataProvider = "getData", groups= {"Orders"})
	public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		ProductCataloguePage productCataloguePage = landingPage.loginIntoApp(input.get("email"), input.get("password"));
		productCataloguePage.addProductToCart(input.get("product"));
		CartPage cartPage = productCataloguePage.clickOnGoToCart();
		Boolean match = cartPage.productNamePresentOrNot(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.checkOut();
		checkoutPage.selectCountry(countryName);
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		Assert.assertTrue(confirmationPage.getConfirmationMessage().equalsIgnoreCase(confirmMessage));
	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistory() throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		ProductCataloguePage productCataloguePage = landingPage.loginIntoApp("snehalep@example.com", "SnehalEdake@10");
		MyOrdersPage myOrdersPage = productCataloguePage.clickOnMyOrders();
		Boolean match = myOrdersPage.orderPresentOrNot(productName);
		System.out.println(match);
		Assert.assertTrue(match);
	}
	
	@DataProvider
	public Object[][] getData() throws IOException{
		
		
		List<HashMap<String,String>> data=getJsonDataToMap(orderFilePath);
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
		/*
		 * HashMap<String,String> map=new HashMap<String,String>(); map.put("email",
		 * "snehalep@example.com"); map.put("password", "SnehalEdake@10");
		 * map.put("product", "ZARA COAT 3");
		 * 
		 * HashMap<String,String> map1=new HashMap<String,String>(); map1.put("email",
		 * "snehalep1111@example.com"); map1.put("password", "SnehalEdake@10");
		 * map1.put("product", "ADIDAS ORIGINAL");
		 */
		
		//return new Object[][] {{map},{map1}};
	}
}
