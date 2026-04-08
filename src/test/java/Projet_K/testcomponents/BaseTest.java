package Projet_K.testcomponents;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Project_K.SeleniumFrameworkDesign.Landingpage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
public WebDriver driver;
public Landingpage landingPage;
	public WebDriver initializeDriver() throws IOException
	{
		 
		Properties pro = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Project_K\\resources\\GlobleData.properties");
		pro.load(fis);
		String BrowerName = System.getProperty("browser") != null 
                ? System.getProperty("browser") 
                : pro.getProperty("browser");
//		pro.getProperty("brower");
		
		if(BrowerName.equalsIgnoreCase("chrome"))	
		{
		WebDriverManager.chromedriver().setup();
		driver =new ChromeDriver();
		
		}
		//firefox
		else if(BrowerName.equalsIgnoreCase("firefox"))
		{
		WebDriverManager.firefoxdriver().setup();
		 driver =new FirefoxDriver();	
		}
		//edge
		//firefox
		else if(BrowerName.equalsIgnoreCase("edge"))
		{
		WebDriverManager.edgedriver().setup();
		 driver =new EdgeDriver();	
		}
		
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}	
	
	public List<HashMap<String, String>> getJsonDataToHashMap(String filepath) throws IOException
	{
		String jsoncontent = FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(
	jsoncontent,  new TypeReference<List<HashMap<String, String>>>(){});
		return data;
		}
	
	public String getScreenShot(String testcasename, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testcasename + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testcasename + ".png";
	}
	
	@BeforeMethod(alwaysRun = true)
	
	public Landingpage LaunchApplication() throws IOException, InterruptedException
	{
		driver = initializeDriver();
		landingPage =new Landingpage(driver);
		landingPage.url();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,50)");
		return landingPage;
			
	}
	

	
	@AfterMethod(alwaysRun = true)
	public void close()
	{
		driver.close();

	}
}
