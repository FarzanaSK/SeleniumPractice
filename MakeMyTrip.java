package week1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MakeMyTrip {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		
		//ChromeOptions to block pop up and maximize screen.
		ChromeOptions options = new ChromeOptions();
		
		//To maximize browser
		//options.addArguments("start-maximized");
		
		//To block popups
		//options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
		options.addArguments("--disable-notifications");
	
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		//Navigate to MakeMyTrip
		driver.get("https://www.makemytrip.com/");
		driver.manage().deleteAllCookies();
		
		Actions builder = new Actions(driver);
		JavascriptExecutor js;
		js=(JavascriptExecutor)driver;
		WebDriverWait wt;
		wt = new WebDriverWait(driver,40);
		
		Thread.sleep(2000);
		driver.findElementByXPath("//li[@data-cy='account']").click();
		Thread.sleep(1000);
	
		//Click Hotels
		WebElement eleHotels = driver.findElementByXPath("//span[text()='Hotels']");
		js.executeScript("arguments[0].click();", eleHotels);
		Thread.sleep(3000);
		
		//Enter city as Goa, and choose Goa, India
		WebElement eleInput = driver.findElementByXPath("(//input[@type='text'])[1]");
		eleInput.click();
		Thread.sleep(3000);
		WebElement eleEnterCity = driver.findElementByXPath("//input[@placeholder='Enter city/ Hotel/ Area/ Building']");
		eleEnterCity.sendKeys("Goa",Keys.ENTER);
		WebElement eleCityName = driver.findElementByXPath("//li[@role='option'][1]/div/div/div/p[1]");
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOf(eleCityName));
		eleCityName.click();
		Thread.sleep(1000);
		
		//Select date as july 15 and July 19
		driver.findElementByXPath("(//div[text()='15'])[1]").click();
		driver.findElementByXPath("(//div[text()='19'])[1]").click();
		Thread.sleep(1000);
		
		//Clicking Guests Rooms
		driver.findElementByXPath("//input[@id='guest']").click();
		Thread.sleep(500);
		
		//Click 2 adults
		driver.findElementByXPath("(//ul[@data-cy='adultCount'])/li[2]").click();
		Thread.sleep(500);
		
		//click 1 children
		driver.findElementByXPath("(//ul[@class='guestCounter font12 darkText'])[2]/li[2]").click();
		Thread.sleep(500);
		
		//click Apply
		driver.findElementByXPath("//button[@data-cy='submitGuest']").click();
		Thread.sleep(2000);
		
		//click Search
		driver.findElementByXPath("//button[text()='Search']").click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(3000);
		
		//Click on the screen
		builder.click().build().perform();
		
		//Select locality as Baga
		WebElement eleBaga = driver.findElementByXPath("(//ul[@class='filterList'])[2]/li[4]");
		eleBaga.click();
		Thread.sleep(2000);
		
		//Select user rating as 3&above(good) under Select Filters
		driver.findElementByXPath("(//span[@class='checkmarkOuter'])/label[contains(text(),'3')]").click();
		Thread.sleep(500);
		
		//Select Sort By: Price-Low to High
		driver.findElementByXPath("//span[text()='Popularity']").click();
		Thread.sleep(500);
		driver.findElementByXPath("//li[contains(text(),'Low to High')]").click();
		Thread.sleep(2000);
		
		//Click on the first resulting hotel and go to the new window
		driver.findElementById("Listing_hotel_0").click();
		
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> ListofWindows = new ArrayList<String>(windowHandles);
		driver.switchTo().window(ListofWindows.get(1));
	
		//Delete Cookies and getCurrentUrl to avoid Access Denied error 
		driver.manage().deleteAllCookies();
		String currentUrl = driver.getCurrentUrl();
		driver.get(currentUrl);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(1000);

		//Print Hotel Name
		String eleHotel = driver.findElementById("detpg_hotel_name").getText();
		System.out.println("Name of the Hotel-->" + eleHotel);
		Thread.sleep(1000);
		
		//Click on Book Now Button
		driver.findElementByXPath("(//a[contains(@class,'primaryBtn')])[1]").click();
		Thread.sleep(2000);
		
		//Print the Total Payable amount
		String eleTotalAmount = driver.findElementByXPath("//span[@id='revpg_total_payable_amt']").getText();
		System.out.println("Total Payable Amount-->" + eleTotalAmount);
		
		//Close the Browser
		driver.quit();
		
			
	}

}
