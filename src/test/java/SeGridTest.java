import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SeGridTest {
	WebDriver driver = null;
	@Parameters ({"platform","browser","version","url"})
	@BeforeTest(alwaysRun=true)
	public void setUp(String platform,String browser,String version,String url) throws MalformedURLException{
		
		DesiredCapabilities caps = new DesiredCapabilities();
		if(platform.equalsIgnoreCase("Windows")) {
			caps.setPlatform(org.openqa.selenium.Platform.WINDOWS);
		}
		    if (platform.equalsIgnoreCase("MAC")) {
		      caps.setPlatform(org.openqa.selenium.Platform.MAC);
		    }
		    if (browser.equalsIgnoreCase("Internet Explorer")) {
		      caps = DesiredCapabilities.internetExplorer();
		    }
		    if (browser.equalsIgnoreCase("firefox")) {
		        caps = DesiredCapabilities.firefox();
		      }
		      if (browser.equalsIgnoreCase("chrome")) {
		        caps = DesiredCapabilities.chrome();
		      }
		     if (browser.equalsIgnoreCase("Microsoft Edge")) {
		        caps = DesiredCapabilities.edge();
		      }
		      caps.setVersion(version);
		      driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps);
		      driver.get(url);
    
		
	}
  @Test(description ="test BMI Calculator")
  public void testBMICalculator() throws InterruptedException {
	  WebElement height = driver.findElement(By.name("heightCMS"));
	  height.sendKeys("181");
	  WebElement weight = driver.findElement(By.name("weightKg"));
	  weight.sendKeys("80");
	  WebElement calculateButton = driver.findElement(By.id("Calculate"));
	  calculateButton.click();
	  WebElement bmi = driver.findElement(By.name("bmi"));
	  assertEquals(bmi.getAttribute("value"), "24.4");
	  WebElement bmiCategory = driver.findElement(By.name("bmi_category"));
	  assertEquals(bmiCategory.getAttribute("value"), "Normal");
  }
  @AfterTest
  public void tearDown() {
	  driver.quit();
  }
}
