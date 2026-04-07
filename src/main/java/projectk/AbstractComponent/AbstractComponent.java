package projectk.AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Project_K.SeleniumFrameworkDesign.Cartpage;
import Project_K.SeleniumFrameworkDesign.Orderpage;

public class AbstractComponent {
	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css ="button[routerlink='/dashboard/cart")
	WebElement cartHeader;
	
	
	@FindBy(css ="button[routerlink='/dashboard/myorders']")
	WebElement OrdertHistory;
	


	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForElementTodisappear(WebElement Ele)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(Ele));

		}
	
	
	public Cartpage goToCartpage() throws InterruptedException
	{
		cartHeader.click();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,800)");
		Cartpage CP = new Cartpage(driver);
		return CP;
	}
	
	public Orderpage goToOrderpage()
	{
		
		OrdertHistory.click();	
		Orderpage OP = new Orderpage(driver);
		return OP;
	}
	

	
	
}
