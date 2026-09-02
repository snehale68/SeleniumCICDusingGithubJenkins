package E2E.SeleniumFrameworkDesign.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import E2E.SeleniumFrameworkDesign.PageObjects.CartPage;
import E2E.SeleniumFrameworkDesign.PageObjects.CheckoutPage;
import E2E.SeleniumFrameworkDesign.PageObjects.ConfirmationPage;
import E2E.SeleniumFrameworkDesign.PageObjects.ProductCataloguePage;
import E2E.SeleniumFrameworkDesign.TestComponents.BaseClassTest;
import E2E.SeleniumFrameworkDesign.TestComponents.Retry;

public class ErrorValidations extends BaseClassTest{
	
	@Test(groups= {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void invalidUserNameOrPassword() {
		String loginErrorMessage="Incorrect email or password.";
		//String loginErrorMessage="Incorrect email  password.";
		ProductCataloguePage productCataloguePage = landingPage.loginIntoApp("snehalep@g.com", "xyxz@10");
		Assert.assertEquals(landingPage.getErrorMessage(), loginErrorMessage);
		
	}
	
	@Test
	public void productErrorValidation() throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";

		ProductCataloguePage productCataloguePage = landingPage.loginIntoApp("snehalep1111@example.com", "SnehalEdake@10");
		productCataloguePage.addProductToCart(productName);
		CartPage cartPage = productCataloguePage.clickOnGoToCart();
		Boolean match = cartPage.productNamePresentOrNot("ZARA COAT 33");
		Assert.assertFalse(match);
	}

}
