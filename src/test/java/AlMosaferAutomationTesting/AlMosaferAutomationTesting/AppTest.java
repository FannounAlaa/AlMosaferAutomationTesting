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

	@Test(priority = 1, enabled = false)
	public void CheckLanguageTest(String ExpectedLanguage) {
		String ActualLanguage = driver.findElement(By.tagName("html")).getDomAttribute("lang");

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

	@Test(priority = 5)
	public void CheckHotelTabIsNotSelected() {
		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		String ActualTab = HotelTab.getDomAttribute("aria-selected");

		String expectedCheckHotelTabIsNotSelected = "false";

		Assert.assertEquals(ActualTab, expectedCheckHotelTabIsNotSelected);
	}

	@Test(priority = 6)
	public void CheckFlightDepartureDate() {

		List<WebElement> dates = driver.findElements(By.cssSelector(".sc-dXfzlN.iPVuSG"));
		String ActualDate = dates.get(0).getText();

		LocalDate date = LocalDate.now();
		int tomorrow = date.plusDays(1).getDayOfMonth();
		String tomorrowAsFormatedValue = String.format("%02d", tomorrow);

		Assert.assertEquals(ActualDate, tomorrowAsFormatedValue);

	}

	@Test(priority = 7)
	public void CheckFlightReturnDate() {
		List<WebElement> dates = driver.findElements(By.cssSelector(".sc-dXfzlN.iPVuSG"));
		String ActualDate = dates.get(1).getText();

		LocalDate date = LocalDate.now();

		int DayAftertomorrow = date.plusDays(2).getDayOfMonth();
		String DayAfterTomorrowAsFormatedValue = String.format("%02d", DayAftertomorrow);

		Assert.assertEquals(ActualDate, DayAfterTomorrowAsFormatedValue);
	}

	@Test(priority = 8)
	public void ChangeLanguageRandomly() {
		Random rand = new Random();
		String[] Websites = {"https://www.almosafer.com/en", "https://www.almosafer.com/ar"};
		int RandomIndex = rand.nextInt(Websites.length);
		
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
		Random rand = new Random();
		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		HotelTab.click();
		
		WebElement SearchInputField = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));
		String[] EnglishCtities = {"Dubai", "Jeddeh", "Riyadh"};
		String[] ArabicCtities = {"جدة", "دبي"};
		
		int randEnglishCitities = rand.nextInt(EnglishCtities.length);
		int randArabicCtities = rand.nextInt(ArabicCtities.length);
		
		if(driver.getCurrentUrl().contains("en")) {
			SearchInputField.sendKeys(EnglishCtities[randEnglishCitities]);
		}
		else {
			SearchInputField.sendKeys(ArabicCtities[randArabicCtities]);
		}
		
		WebElement SelectVistorNumber = driver.findElement(By.cssSelector(".sc-tln3e3-1.gvrkTi"));
		
		String[] Values = {"A", "B"};
		
		int RandomValue = rand.nextInt(Values.length);

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
