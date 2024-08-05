package LearningAndRoughWork;

import java.io.File;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumVersion4 {

	WebDriver driver;

	@Test(enabled = false)
	public void chromeOptionsValues() {
		try {
			
			WebDriverManager.chromedriver().browserVersion("").setup();
			driver = new ChromeDriver();

			driver.findElement(By.xpath(""));

		} catch (Exception e) {
			throw e;
		}
	}

	@Test(enabled = false)
	public void screenshots() {
		try {
			WebDriverManager.chromedriver().setup();
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get("https://www.selenium.dev/");

			try {

				 WebElement logo = driver.findElement(By.xpath("/html/body/div[1]/main/section[1]/div/div/div/h1"));
		            File source = ((TakesScreenshot) logo).getScreenshotAs(OutputType.FILE);
		            FileUtils.copyFile(source, new File("./Screenshots/logo" + System.currentTimeMillis() + ".png"));
		        } catch (Exception e) {
		            System.out.println(e.getMessage());
		        }
		 
		        System.out.println("The Screenshot is taken and saved under Screenshots folder");
		        driver.quit();

		} catch (Exception e) {
			throw e;
		}
	}
	
	@Test(enabled=true)
	public void clickAndHold()
	{
		try {
			
			WebDriverManager.chromedriver().setup();
	        WebDriver driver = new ChromeDriver();
	        // Navigate to Url
	        driver.get("https://crossbrowsertesting.github.io/drag-and-drop.html");
	        driver.manage().window().maximize();
	 
	        // Find element xpath which we need to drag
	        WebElement from = driver.findElement(By.id("draggable"));
	 
	        // Find element xpath where we need to drop
	        WebElement to = driver.findElement(By.id("droppable"));
	 
	        Actions actionProvider = new Actions(driver);
	 
	        // Perform click-and-hold action on the element
	        actionProvider.clickAndHold(from).build().perform();
	 
	       // Move to drop Webelement
	        actionProvider.clickAndHold(to).build().perform();
	 
	        //Release drop element
	        actionProvider.release(to).build().perform();
			
		} catch (Exception e) {
			throw e;
		}
	}

}
