package E2E.SeleniumFrameworkDesign.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import E2E.SeleniumFrameworkDesign.ReusableComponents.ReusableComponents;

public class MyOrdersPage extends ReusableComponents{
	WebDriver driver;
	public MyOrdersPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);	
	}

	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> myOrderProducts;
	
	public boolean orderPresentOrNot(String productName){
		Boolean match=myOrderProducts.stream().anyMatch(myOrderProduct->myOrderProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
}
