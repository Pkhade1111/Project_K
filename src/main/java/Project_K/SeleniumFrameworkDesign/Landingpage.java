package Project_K.SeleniumFrameworkDesign;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import projectk.AbstractComponent.AbstractComponent;

public class Landingpage extends AbstractComponent {
	
	WebDriver  driver;
	public Landingpage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	} 
//	WebElement userEmail = driver.findElement(By.id("userEmail"));
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement Password;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement ErrorMessage;
	
	public String getErrorMesage()
	{
		waitForWebElementToAppear(ErrorMessage);
		return ErrorMessage.getText();
		 
	}
	
	public ProductCatalogue loginApplication(String email, String passward1)
	{
		userEmail.sendKeys(email);
		Password.sendKeys(passward1);
		submit.click();
		ProductCatalogue PC = new ProductCatalogue(driver);
		return PC;
	}
	public void url()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
}




