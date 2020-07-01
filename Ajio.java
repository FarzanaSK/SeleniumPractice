package week1;

import java.util.LinkedList;
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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ajio {

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
		
		//Navigate to Ajio
		driver.get("https://www.ajio.com/shop/sale");
		driver.manage().deleteAllCookies();
		
		
		JavascriptExecutor js;
		js=(JavascriptExecutor)driver;
		WebDriverWait wt;
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Click and Enter Bags in Search Field
		driver.findElementByXPath("//input[@name='searchVal']").sendKeys("bags");
		//Thread.sleep(3000);
		wt = new WebDriverWait(driver,60);
		wt.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//ul[@class = 'rilrtl-list ']/li[3]")));
		driver.findElementByXPath("//ul[@class = 'rilrtl-list ']/li[3]").click();
		
		//Click on five grid
		Thread.sleep(2000);
		driver.findElementByXPath("//div[@class='five-grid']").click();
		
		//Select SORT BY as "What's New"
		WebElement eleSortBy = driver.findElement(By.tagName("select")); 
		Select eleSel= new Select(eleSortBy);
		eleSel.selectByVisibleText("What's New");
		Thread.sleep(3000);
		
		//Enter Price Range Min as 2500 and Max as 5000
		driver.findElementByXPath("(//span[@class='facet-left-pane-label'])[3]").click();
		//Thread.sleep(500);
		//Entering Min value
		WebElement eleMinPrice = driver.findElementById("minPrice");
		js.executeScript("arguments[0].scrollIntoView();",eleMinPrice);
		eleMinPrice.sendKeys("2500",Keys.TAB);
		
		//Entering Max value
		WebElement eleMaxPrice = driver.findElementById("maxPrice");
		js.executeScript("arguments[0].scrollIntoView();",eleMaxPrice);
		eleMaxPrice.sendKeys("5000",Keys.ENTER);	
		
		Thread.sleep(2000);
			
		//Click on the product "TOMMY HILFIGER Sling Bag with Chain Strap"
		driver.findElementByXPath("//img[contains(@alt,'TOMMY HILFIGER Blue Slings & Satchels Sling Bag with Chain Strap')]").click();

		//Switching the control to the new window
		Set<String> WindowHandles = driver.getWindowHandles();
		List<String> ListofWindows= new LinkedList<>(WindowHandles);
		driver.switchTo().window(ListofWindows.get(1));
		
		
		//Verify the Coupon code for the price above 2890 is applicable for your product, 
		//if applicable then get the Coupon Code and the discount price for the coupon
		
		String eleProd = driver.findElement(By.className("prod-sp")).getText();
		String eleProdPrice = eleProd.replaceAll("[^0-9]", "");
		
		int elePrice = Integer.parseInt(eleProdPrice);
		
		if(elePrice>2890)
		{
			System.out.println("Coupon Available for this Item");
		}
		
		String eleCouponCode = driver.findElement(By.className("promo-title")).getText().replaceAll("Use Code", "");
		
		//Getting DicountPrice
		String eleDiscountPrice= driver.findElementByXPath("//div[@class='promo-discounted-price']//span").getText().replaceAll("[^0-9]", "");
	    
		System.out.println("Discounted Price --> "+eleDiscountPrice);
		
		//Check the availability of the product for pincode 560043, print the expected delivery date if it is available
		driver.findElementByXPath("//div[@class = 'edd-pincode-msg']").click();
		driver.findElementByXPath("//input[@name = 'pincode']").sendKeys("560043");
		driver.findElementByClassName("edd-pincode-modal-submit-btn").click();
		Thread.sleep(1000);
		
		//Delivery Date
		String eleDeliveryDate = driver.findElementByXPath("//ul[@class = 'edd-message-success-details']//span").getText();
		System.out.println("Expected Deliver Date is -->"+eleDeliveryDate);
		
		
		//Click on Other Informations under Product Details and Print the Customer Care address, phone and email
		js.executeScript("window.scrollBy(0,500)");
		
		driver.findElementByXPath("//div[text()='Other information']").click();
		
		//Printing the Customer Care Details
		String CustomerCareText = driver.findElementByXPath("(//li[@class='detail-list mandatory-info'])[7]/span[3]").getText();
		System.out.println("Customer Care Deatils-->  "+CustomerCareText);
		
		//Click on ADD TO BAG 
		driver.findElementByXPath("//div[@class='pdp-addtocart-button']").click();
        	Thread.sleep(10000);
        
        //Click GO TO BAG
        	driver.findElementByXPath("//div[@class='btn-cart']").click();
        
		//Check the Order Total before apply coupon
		String eleOrderTotal = driver.findElementByXPath("//span[@class = 'price-value bold-font']").getText();
		System.out.println("Total Order Before Applying Coupon--> " + eleOrderTotal);
		
		//String eleProductPrice = eleOrderTotal.substring(4, 9).replaceAll("[^0-9]", "");
		
		//Enter Coupon Code
		driver.findElementById("couponCodeInput").sendKeys(eleCouponCode);
		
		//Click Apply
		driver.findElementByXPath("//button[text() = 'Apply']").click();
		
		//Verify the discount price matches with the product price
		String eleProductPrice  = driver.findElementByXPath("//div[@class = 'orignal-price']").getText().substring(4, 9).replaceAll("[^0-9]", "");
		//System.out.println(eleProductPrice);
		
		//Checking if Actual Price is equal to Discounted Price
		if(!eleProductPrice.equals(eleDiscountPrice))
		{
			System.out.println("Actual Price is different from Discount Price");
		}
		
		//Click on Delete and Delete the item from Bag
		driver.findElementByXPath("//div[@class = 'delete-btn']").click();
		driver.findElementByXPath("(//div[@class = 'delete-btn'])[2]").click();
		
		//Close all the browsers
		driver.quit();
		
		
		
	}

}
