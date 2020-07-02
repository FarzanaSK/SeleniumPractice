package week1;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Azure {

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
		
		//Navigate to Azure
		driver.get("https://azure.microsoft.com/en-in/");
		driver.manage().deleteAllCookies();
		
		
		JavascriptExecutor js;
		js=(JavascriptExecutor)driver;
		Actions builder = new Actions(driver);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
		//Click on Pricing
		driver.findElementById("navigation-pricing").click();
		Thread.sleep(1000);
		
		//Click on Pricing Calculator
		driver.findElementByXPath("//a[contains(text(),'Pricing calculator')]").click();
		Thread.sleep(3000);
		
		//Click on Containers
		driver.findElementByXPath("//button[@data-event-property='containers']").click();
		Thread.sleep(2000);
		
		//Select Container Instances
		driver.findElementByXPath("(//button[@title='Container Instances'])[2]").click();
		
		//Click on Container Instance Added View
		driver.findElementByXPath("//button[@id='new-module-loc' and text()='View']").click();
		Thread.sleep(500);
		
		//Select Region as "South India"
		WebElement eleSelect = driver.findElementByTagName("Select");
		Select eleSelDropDown = new Select(eleSelect);
		eleSelDropDown.selectByValue("south-india");
		Thread.sleep(500);
		
		//Set the Duration as 180000 seconds
		WebElement eleDuration = driver.findElementByXPath("//input[@aria-label='Seconds']");
		eleDuration.clear();
		eleDuration.sendKeys("80000",Keys.RIGHT);
		
		//Select the Memory as 4GB
		WebElement eleSelMemory = driver.findElementByXPath("//select[@aria-label='Memory']");
		Select SelMemory = new Select(eleSelMemory);
		SelMemory.selectByValue("4");
		Thread.sleep(500);
		
		//Enable SHOW DEV/TEST PRICING
		driver.findElementById("devtest-toggler").click();
		
		//Select Indian Rupee  as currency
		WebElement eleSelCurrency = driver.findElementByXPath("(//select[@aria-label='Currency'])[1]");
		//js.executeScript("arguments[0].scrollIntoView();",eleSelCurrency );	
		Select SelCurency = new Select(eleSelCurrency);
		SelCurency.selectByValue("INR");
		Thread.sleep(500);
		
		//Print the Estimated monthly price
		String MonthlyPrice = driver.findElementByXPath("(//span[@class='numeric'])[4]/span").getText();
		System.out.println("Monthly Price--> "+MonthlyPrice);
		Thread.sleep(2000);
		
		//Click on Export to download the estimate as excel
		WebElement eleExportButton = driver.findElementByXPath("//button[@class='calculator-button button-transparent export-button']");
		builder.click(eleExportButton).build().perform();
		Thread.sleep(2000);
		//String attribute = eleExportButton.getAttribute("href");
		//System.out.println(attribute);
		
		//Verify the downloded file in the local folder
		String fileName = "ExportedEstimate.xlsx";
		CheckDownloadedFile(fileName);
		
		
		//Navigate to Example Scenarios 
		WebElement eleExampleScenraios = driver.findElementByXPath("//a[text()='Example Scenarios']");
		builder.click(eleExampleScenraios).build().perform();
		Thread.sleep(1000);
		//Select CI/CD for Containers
		driver.findElementByXPath("//button[@title='CI/CD for Containers']").click();
		Thread.sleep(1000);
		
		//Click Add to Estimate
		WebElement eleAddToEstimate = driver.findElementByXPath("//button[text()='Add to estimate']");
		builder.click(eleAddToEstimate).build().perform();
		Thread.sleep(5000);
		
		
		//Change the Currency as Indian Rupee
		WebElement eleSelCurrency2 = driver.findElementByXPath("(//select[@aria-label='Currency'])[1]");
		//js.executeScript("arguments[0].scrollIntoView();",eleSelCurrency );	
		Select SelCurency2 = new Select(eleSelCurrency2);
		SelCurency2.selectByValue("INR");
		
		
		//Enable SHOW DEV/TEST PRICING
		driver.findElementById("devtest-toggler").click();
		Thread.sleep(2000);
		
		//Export the Estimate
		WebElement eleExportButton2 = driver.findElementByXPath("//button[@class='calculator-button button-transparent export-button']");
		builder.click(eleExportButton2).build().perform();
		Thread.sleep(2000);
		
		//Verify the downloded file in the local folder
		String fileName2 = "ExportedEstimate (1).xlsx";
		CheckDownloadedFile(fileName2);
	}
		
		//Method to Check Downloaded File
		public static void CheckDownloadedFile(String fileName)
		{
		    File dir = new File("C:\\Users\\Dell\\Downloads");
		    File[] dir_contents = dir.listFiles();
		  	    
		    for (int i = 0; i < dir_contents.length; i++) {
		        if (dir_contents[i].getName().equals(fileName))
		        {
		            System.out.println("Downloaded File Available   "+dir_contents[i].getName());
		         }
		    }
		}

}
