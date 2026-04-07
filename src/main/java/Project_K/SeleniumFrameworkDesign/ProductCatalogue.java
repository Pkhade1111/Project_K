package Project_K.SeleniumFrameworkDesign;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import projectk.AbstractComponent.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	
	WebDriver  driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css =".mb-3")
	List<WebElement> products;
	
	@FindBy(css ="ng-animating")
	WebElement spinner;
	
	By productsby = By.cssSelector(".mb-3");
	By addTocart = By.cssSelector(".card-body button:last-of-type");
	By tostmessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsby);
		return products;
		
	}
	
	public WebElement getProdutByName(String Productname)
	{
		WebElement prod = getProductList().stream().filter(product->product.findElement(By.cssSelector("b"))
				.getText().equals(Productname)).findFirst().orElse(null);
		return prod;
	}
	public void addProductToCart(String Productname) 
	{
		WebElement prod = getProdutByName(Productname);
		prod.findElement(addTocart).click();
		waitForElementToAppear(tostmessage);
		waitForElementTodisappear(spinner);
		
		
		

	}


}




