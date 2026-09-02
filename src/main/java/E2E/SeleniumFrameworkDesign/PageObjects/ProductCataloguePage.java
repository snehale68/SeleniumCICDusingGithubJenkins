package E2E.SeleniumFrameworkDesign.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import E2E.SeleniumFrameworkDesign.ReusableComponents.ReusableComponents;

public class ProductCataloguePage extends ReusableComponents {

	WebDriver driver;

	public ProductCataloguePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	@FindBy(css = ".ng-animating")
	WebElement spinner;

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartIcon;

	By productsBy = By.cssSelector(".mb-3");
	By productName = By.cssSelector("b");
	By addToCartButton = By.cssSelector(".card-body button:last-of-type");
	By toastContainer = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		waitForFindByElementToAppear(productsBy);
		return products;
	}

	public WebElement product(String productNameValue) {
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(productName).getText().equals(productNameValue)).findFirst()
				.orElse(null);
		return prod;
	}

	public void addProductToCart(String productNameValue) throws InterruptedException {
		WebElement prod = product(productNameValue);
		prod.findElement(addToCartButton).click();
		waitForFindByElementToAppear(addToCartButton);
		waitForElementToDisappear(spinner);
		
		
	}

}