package Project_K.SeleniumFrameworkDesign;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import projectk.AbstractComponent.AbstractComponent;

public class Orderpage extends AbstractComponent {
	
	WebDriver  driver;
	public Orderpage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css ="tr td:nth-child(3)")
	private List <WebElement>ProductNames;
	

	public Boolean verifyOrderDisplay(String Productname)
	{
		Boolean match =ProductNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(Productname));
		return match;
	}
	


}




