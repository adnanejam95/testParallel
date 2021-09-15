import static org.testng.Assert.assertEquals;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

//import com.ibm.icu.text.MessageFormat;

public class SauceTest {
	WebDriver driver;
	
	@Before
	public void setUp() throws Exception{
		//String SAUCE_USER ="adnanejam";
		//String SAUCE_KEY = "1e3072a6-c92f-4bd1-aa69-e426b7cbc08a";
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platform", "Windows");
		caps.setCapability("browserName", "chrome");
		caps.setCapability("name", "BMI Calculator Test");
		driver = new RemoteWebDriver(new URL("https://adnanejam:1e3072a6-c92f-4bd1-aa69-e426b7cbc08a@ondemand.us-west-1.saucelabs.com:443/wd/hub"),caps);
		driver.get("http://cookbook.seleniumacademy.com/bmicalculator.html");
	}
	@Test
	public void testBMICal() {
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
	@After 
	public void tearDown() throws Exception{
		driver.close();
	}

}
