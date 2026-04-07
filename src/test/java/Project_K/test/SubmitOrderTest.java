package Project_K.test;

import java.io.File;
import java.io.IOException;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Project_K.SeleniumFrameworkDesign.Cartpage;
import Project_K.SeleniumFrameworkDesign.CheckOutPage;
import Project_K.SeleniumFrameworkDesign.ConfirmationPage;
import Project_K.SeleniumFrameworkDesign.Landingpage;
import Project_K.SeleniumFrameworkDesign.Orderpage;
import Project_K.SeleniumFrameworkDesign.ProductCatalogue;
import Projet_K.testcomponents.BaseTest;
import Projet_K.testcomponents.Retry;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {
	String Productname = "ZARA COAT 3";
@Test(dataProvider="getData",groups= {"purchase"}, retryAnalyzer=Retry.class)	
public void SubmitOrder(HashMap<String, String> input) throws IOException, InterruptedException
{
	  

		ProductCatalogue PC = landingPage.loginApplication(input.get("email"), input.get("Password"));
		List<WebElement>products = PC.getProductList();
		PC.addProductToCart(input.get("product"));	
		Cartpage CP = PC.goToCartpage();
		CheckOutPage cop = CP.goToCheckOutPage();	
		cop.SelectCounty("India");
		ConfirmationPage ConfirmationPage = cop.PlaceOrder();
		String ConfirmMessage  =  ConfirmationPage.getConfirmOrder();
		Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("Thankyou for the order.")); 
	
	 	
}

@Test(dependsOnMethods= {"SubmitOrder"}, groups = {"testorderhistroy"}, retryAnalyzer=Retry.class)
public void OrderHistoryTest()
{
	ProductCatalogue PC = landingPage.loginApplication("Pkhade@gamil.com", "Peter@010895");
	Orderpage OP = PC.goToOrderpage();
	Assert.assertTrue(OP.verifyOrderDisplay(Productname));
}



@DataProvider
public Object[][] getData() throws IOException
{
//	HashMap<String, String> map = new HashMap<String, String>();
//	map.put("email", "Pkhade@gamil.com");
//	map.put("Password", "Peter@010895");
//	map.put("product", "ZARA COAT 3");
//	HashMap<String, String> map1 = new HashMap<String, String>();
//	map1.put("email", "peterkabali@gmail.com");
//	map1.put("Password", "Peter@010203");
//	map1.put("product", "ADIDAS ORIGINAL");
	List<HashMap<String, String>> data = getJsonDataToHashMap(System.getProperty("user.dir")+"\\src\\test\\java\\Project_K\\data\\PurchaseOrder.json");
	return new Object [] [] {{data.get(0)}, {data.get(1)}};

}

}
		



