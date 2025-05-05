package AlMosaferAutomationTesting.AlMosaferAutomationTesting;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestData {

	WebDriver driver = new ChromeDriver();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	String URL = "https://global.almosafer.com/en";

	String ExpectedCurrency = "SAR";
	String ExpectedNumber = "+966554400000";
	String expectedCheckHotelTabIsNotSelected = "false";

	LocalDate date = LocalDate.now();
	int tomorrow = date.plusDays(1).getDayOfMonth();
	String tomorrowAsFormatedValue = String.format("%02d", tomorrow);

	int DayAftertomorrow = date.plusDays(2).getDayOfMonth();
	String DayAfterTomorrowAsFormatedValue = String.format("%02d", DayAftertomorrow);

	Random rand = new Random();
	String[] Websites = { "https://www.almosafer.com/en", "https://www.almosafer.com/ar" };
	int RandomIndex = rand.nextInt(Websites.length);
	
	String[] EnglishCtities = {"Dubai", "Jeddeh", "Riyadh"};
	String[] ArabicCtities = {"جدة", "دبي"};
	
	int randEnglishCitities = rand.nextInt(EnglishCtities.length);
	int randArabicCtities = rand.nextInt(ArabicCtities.length);

	String[] Values = {"A", "B"};
	
	int RandomValue = rand.nextInt(Values.length);


	public void TestSetup() {
		driver.get(URL);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		WebElement PopupButton = driver
				.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary"));
		PopupButton.click();

	}

}
