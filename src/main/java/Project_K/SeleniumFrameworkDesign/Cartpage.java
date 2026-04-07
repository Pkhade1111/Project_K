package Project_K.SeleniumFrameworkDesign;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import projectk.AbstractComponent.AbstractComponent;

public class Cartpage extends AbstractComponent {
	
	WebDriver  driver;
	public Cartpage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css =".totalRow button")
	WebElement CartProduct;
	

	public CheckOutPage goToCheckOutPage()
	{
	
		CartProduct.click();
		CheckOutPage cop = new CheckOutPage(driver); 
		return cop;
	}
	


}




