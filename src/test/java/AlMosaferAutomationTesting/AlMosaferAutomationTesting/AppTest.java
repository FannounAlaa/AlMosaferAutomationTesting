package AlMosaferAutomationTesting.AlMosaferAutomationTesting;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest {
	WebDriver driver = new ChromeDriver();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	String URL = "https://global.almosafer.com/en";

	@BeforeTest
	public void Setup() {
		driver.manage().window().maximize();
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		WebElement PopupButton = driver
				.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary"));
		PopupButton.click();
	}

	@Test(priority = 1)
	public void CheckLanguageTest() {
		String ActualLanguage = driver.findElement(By.tagName("html")).getDomAttribute("lang");
		String ExpectedLanguage = "en";

		Assert.assertEquals(ActualLanguage, ExpectedLanguage);
	}

	@Test(priority = 2)
	public void CheckCurrencyTest() {
		String ActualCurrency = driver.findElement(By.cssSelector(".sc-hUfwpO.kAhsZG")).getText();
		String ExpectedCurrency = "SAR";

		Assert.assertEquals(ActualCurrency, ExpectedCurrency);
	}

	@Test(priority = 3)
	public void CheckContactNo() {
		String ActualNumber = driver.findElement(By.linkText("+966554400000")).getText();
		String ExpectedNumber = "+966554400000";
		Assert.assertEquals(ActualNumber, ExpectedNumber);

	}
	@Test(priority = 4)
	public void CheckQitafLogo() {
		WebElement TheFooter = driver.findElement(By.tagName("footer"));

		boolean ActualImageIsDisplay = TheFooter.findElement(By.cssSelector(".sc-ekulBa.iOOTo"))
				.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-lcpuFF.jipXfR")).isDisplayed();

		Assert.assertEquals(ActualImageIsDisplay, true);
	}
	
	@AfterTest
	public void DoWork() {
		
	}
	
	
}
