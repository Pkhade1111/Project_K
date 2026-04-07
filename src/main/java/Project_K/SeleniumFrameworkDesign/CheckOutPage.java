package Project_K.SeleniumFrameworkDesign;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import projectk.AbstractComponent.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".action__submit")
	WebElement Scrolldown;
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement County;
	
	@FindBy(xpath="//button[@type='button'] [2]")
	WebElement SelectCounty;
	
	By result= By.cssSelector(".ta-results");
	
	@FindBy(css=".action__submit")
	WebElement PlaceOrder;
	
	
	public void SelectCounty(String countryname)	
	{
		Actions a =new Actions(driver);	
		a.scrollToElement(Scrolldown);
		a.sendKeys(County, countryname).build().perform();
		waitForElementToAppear(By.cssSelector(".ta-results"));
		SelectCounty.click();
	}
	public ConfirmationPage PlaceOrder()
	{
		PlaceOrder.click();	
		return new ConfirmationPage(driver);
	}
	
}
