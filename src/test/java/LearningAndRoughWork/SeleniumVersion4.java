package LearningAndRoughWork;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumVersion4 {
	
	
	WebDriver driver;
	@Test
	public void browserInitialization()
	{
		try {
			
			WebDriverManager.chromedriver().browserVersion("").setup();
			
			driver=new ChromeDriver();
			
		} catch (Exception e) {
			throw e;
		}
	}

}
