package AlMosaferAutomationTesting.AlMosaferAutomationTesting;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest extends TestData {

	@BeforeTest
	public void Setup() {
		TestSetup();
	}

	@Test(priority = 1, enabled = false)
	public void CheckLanguageTest(String ExpectedLanguage) {
		String ActualLanguage = driver.findElement(By.tagName("html")).getDomAttribute("lang");

		Assert.assertEquals(ActualLanguage, ExpectedLanguage);
	}

	@Test(priority = 2)
	public void CheckCurrencyTest() {
		String ActualCurrency = driver.findElement(By.cssSelector(".sc-hUfwpO.kAhsZG")).getText();

		Assert.assertEquals(ActualCurrency, ExpectedCurrency);
	}

	@Test(priority = 3)
	public void CheckContactNo() {
		String ActualNumber = driver.findElement(By.linkText("+966554400000")).getText();
		Assert.assertEquals(ActualNumber, ExpectedNumber);

	}

	@Test(priority = 4)
	public void CheckQitafLogo() {
		WebElement TheFooter = driver.findElement(By.tagName("footer"));

		boolean ActualImageIsDisplay = TheFooter.findElement(By.cssSelector(".sc-ekulBa.iOOTo"))
				.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-lcpuFF.jipXfR")).isDisplayed();

		Assert.assertEquals(ActualImageIsDisplay, true);
	}

	@Test(priority = 5)
	public void CheckHotelTabIsNotSelected() {
		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		String ActualTab = HotelTab.getDomAttribute("aria-selected");


		Assert.assertEquals(ActualTab, expectedCheckHotelTabIsNotSelected);
	}

	@Test(priority = 6)
	public void CheckFlightDepartureDate() {

		List<WebElement> dates = driver.findElements(By.cssSelector(".sc-dXfzlN.iPVuSG"));
		String ActualDate = dates.get(0).getText();


		Assert.assertEquals(ActualDate, tomorrowAsFormatedValue);

	}

	@Test(priority = 7)
	public void CheckFlightReturnDate() {
		List<WebElement> dates = driver.findElements(By.cssSelector(".sc-dXfzlN.iPVuSG"));
		String ActualDate = dates.get(1).getText();


		Assert.assertEquals(ActualDate, DayAfterTomorrowAsFormatedValue);
	}

	@Test(priority = 8)
	public void ChangeLanguageRandomly() {
		
		driver.get(Websites[RandomIndex]);
		
		if(driver.getCurrentUrl().contains("en")) {
			CheckLanguageTest("en");
		}
		else {
			CheckLanguageTest("ar");
		}
	}
	@Test(priority = 9)
	public void RandomlySelectCities() {
		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		HotelTab.click();
		
		WebElement SearchInputField = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));
		
		if(driver.getCurrentUrl().contains("en")) {
			SearchInputField.sendKeys(EnglishCtities[randEnglishCitities]);
		}
		else {
			SearchInputField.sendKeys(ArabicCtities[randArabicCtities]);
		}
		
		WebElement SelectVistorNumber = driver.findElement(By.cssSelector(".sc-tln3e3-1.gvrkTi"));
		

		Select mySelector = new Select(SelectVistorNumber);

		mySelector.selectByValue(Values[RandomValue]);

		
		driver.findElement(By.cssSelector(".sc-1vkdpp9-5.btwWVk")).click();;
	}
	
	@Test(priority = 10)
	public void CheckTheResultsIsretrived() throws InterruptedException {
		Thread.sleep(10000);

		String Results = driver.findElement(By.xpath("//span[@data-testid='srp_properties_found']")).getText();

		if (driver.getCurrentUrl().contains("en")) {
			Assert.assertEquals(Results.contains("found"), true);

		} else {
			Assert.assertEquals(Results.contains("مكان إقامة"), true);

		}

	}

	@AfterTest
	public void DoWork() {

	}

}
