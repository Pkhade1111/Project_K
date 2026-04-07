package Project_K.test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Project_K.SeleniumFrameworkDesign.Cartpage;
import Project_K.SeleniumFrameworkDesign.ProductCatalogue;
import Projet_K.testcomponents.BaseTest;
import Projet_K.testcomponents.Retry;

public class ErrorValidations extends BaseTest {

@Test(groups = {"errorHandling"})
public void LoginErrorValidation() throws IOException 
{
		
		landingPage.loginApplication("kkhade@gamil.com", "leter@010895");
		Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMesage());
}
@Test(retryAnalyzer=Retry.class,groups = {"errorHandling"})
public void productValidation() throws InterruptedException
{
	String Productname = "ZARA COAT 3";
	ProductCatalogue PC = landingPage.loginApplication("Pkhade@gamil.com", "Peter@010895");
	List<WebElement>products = PC.getProductList();
	PC.addProductToCart(Productname);	
	Cartpage CP = PC.goToCartpage();
}
}


