package web_testing_app;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.AfterClass;

import web_testing_app.WebTesting;

import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.interactions.*;

import java.util.concurrent.TimeUnit;
import java.lang.Thread;

public class Assignment {
	private static long shortTimeout = 10;
	private static long longTimeout = 90;
	
	@BeforeClass
	public static void setUp() {
		WebTesting.initSystemProperties();
	}
	
	@AfterClass
	public static void tearDownAll() {
		if (WebTesting.getDriver() != null) {
			WebTesting.quitDriver();
		}
	}

	@After
	public void tearDown() {
		if (WebTesting.getDriver() != null) {
			WebTesting.quitDriver();
		}
	}
	
	
	
	@Test
	public void testGoogleSpeedTestExplicitly() {
		FirefoxDriver driver = (FirefoxDriver) WebTesting.launchDriver("https://www.google.com", "firefox");		
		if (driver == null) {
			fail("Driver not created.");
		}
		
		WebDriverWait shortWait = new WebDriverWait(driver, shortTimeout);
		WebDriverWait longWait = new WebDriverWait(driver, longTimeout);
		
		
		
		/* FIRST TEST */
		
		// #1: Ensuring that the search bar is visible before accessing it
		try {
			shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("L2AGLb")));
		} catch (TimeoutException ex) {
			fail("Cookies didn't appear");
		}
		
		driver.findElement(By.id("L2AGLb")).click();
		
		try {
			shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
		} catch (TimeoutException ex) {
			fail("Search bar not found.");
		}
			
		// #2: Entering "internet speed test" into the search bar then pressing the "Return" key
		driver.findElement(By.name("q")).sendKeys("internet speed test" + Keys.RETURN);
		
		// #3: Ensuring that the "RUN SPEED TEST" button is clickable
		try {
			shortWait.until(ExpectedConditions.elementToBeClickable(By.id("knowledge-verticals-internetspeedtest__test_button")));
		} catch (TimeoutException ex) {
			fail("\"RUN SPEED TEST\" button not found.");
		}
				
		// #4: Clicking the "RUN SPEED TEST" button by its "id" attribute
		driver.findElement(By.id("knowledge-verticals-internetspeedtest__test_button")).click();
		
		// #5: Ensuring that the "CANCEL" button is clickable directly after this
		try {
			shortWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//g-raised-button[@jsaction=\'dArJMd\']")));
		} catch (TimeoutException ex) {
			fail("\"CANCEL\" button not found.");
		}
			

		// #6: Wait for the test to finish
		try {
			longWait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(By.xpath("//g-raised-button[@jsaction=\'dArJMd\']"))));
		} catch (TimeoutException ex) {
			fail("Speed test did not finish.");
		}
		
		// #7: "RETRY" signifies a failure
		try {
			shortWait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(By.xpath("//g-raised-button[@jsaction=\'i0JLwd\']"))));
			
		} catch (TimeoutException ex) {
			fail("Speed test failed to run.");
		}
		
		// #8: "TEST AGAIN" signifies a success
		try {
			shortWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//g-raised-button[@jsaction=\'iyDKIb\']")));
		} catch (TimeoutException ex) {
			fail("Speed test failed to run.");
		}
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		

		
	}
	
	// Implicit Speed Test 
	
	
	@Test
	public void testGoogleSpeedTestImplicitly() throws InterruptedException {
		FirefoxDriver driver = (FirefoxDriver) WebTesting.launchDriver("https://www.google.com", "firefox");
		
		if (driver == null) {
			fail("Driver not created.");
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		long shortSleep = 6000; // 6 seconds
		long longSleep = 60000; // 60 seconds
		
		
		// #9: Entering "internet speed test" into the search bar then pressing the "Return" key
		Thread.sleep(shortSleep);
		
		driver.findElement(By.id("L2AGLb")).click();
		
		
		try {
			driver.findElement(By.name("q")).sendKeys("internet speed test" + Keys.RETURN);
		} catch (NoSuchElementException ex) {
			fail("Search bar not found.");
		}
				
		// #10: Clicking the "RUN SPEED TEST" button by its "id" attribute
		try {
			driver.findElement(By.id("knowledge-verticals-internetspeedtest__test_button")).click();
		} catch (NoSuchElementException ex) {
			fail("\"RUN SPEED TEST\" button not found.");
		}
	
		// #11: Ensuring that the "CANCEL" is displayed directly after this
		Thread.sleep(shortSleep);
		
		WebElement cancelButton = null;
		
		try {
			cancelButton = driver.findElement(By.cssSelector("g-raised-button[jsaction=\'dArJMd\']"));
		} catch (NoSuchElementException ex) {
			fail("\"CANCEL\" button not found.");
		}
		
		if (!cancelButton.isDisplayed()) {
			fail("\"CANCEL\" button not found.");
		}
	
		// #12: "RETRY" signifies a failure
		Thread.sleep(longSleep);

		WebElement retryButton = null;
		
		try {
			retryButton = driver.findElement(By.cssSelector("g-raised-button[jsaction=\'i0JLwd\']"));
		} catch (NoSuchElementException ex) {
			fail("\"RETRY\" button not found.");
		}
		
		if (retryButton.isDisplayed()) {
			fail("Speed test did not finish.");
		}
		
		// #13: "TEST AGAIN" signifies a success
		WebElement testAgainButton = null;
		
		try {
			testAgainButton = driver.findElement(By.cssSelector("g-raised-button[jsaction=\'iyDKIb\']"));
		} catch (NoSuchElementException ex) {
			fail("\"TEST AGAIN\" button not found.");
		}
		
		if (!testAgainButton.isDisplayed()) {
			fail("Speed test did not finish.");
		}
	}
	

	// Calculator Explicit Test
	
	@Test
	public void testCalculatorExplicitly() {
		FirefoxDriver driver = (FirefoxDriver) WebTesting.launchDriver("www.google.com", "firefox");
		if (driver == null) {
			fail("Driver not created.");
		}
		
		WebDriverWait shortWait = new WebDriverWait(driver, shortTimeout);
		WebDriverWait longWait = new WebDriverWait(driver, longTimeout);
		
		try {
			shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("L2AGLb")));
		} catch (TimeoutException ex) {
			fail("Cookies didn't appear");
		}
		
		driver.findElement(By.id("L2AGLb")).click();
		
		
		// #14: Ensuring that the search bar is visible before accessing it
		try {
			shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
		} catch (TimeoutException ex) {
			fail("Search bar not found.");
		}
			
		// #15: Entering "calculator" into the search bar then pressing the "Return" key
		driver.findElement(By.name("q")).sendKeys("calculator" + Keys.RETURN);
		
		// #16: Ensuring that the Calculator is visible
		try {
			shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\'tyYmIf\']")));
		} catch (TimeoutException ex) {
			fail("Calculator not found.");
		}

		
		WebElement calculatorText = driver.findElement(By.id("cwos"));	
		
		// #17: Entering 1 and check whether the value was input into the text field
		driver.findElement(By.xpath("//div[@jsname=\'N10B9\']")).click();
				
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "1"));
		} catch (TimeoutException ex) {
			fail("\"1\" was not input.");
		}	
		
		// #18: Entering "+" and check whether the value was input into the text field
		driver.findElement(By.xpath("//div[@jsname=\'XSr6wc\']")).click();
						
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "1 +"));
		} catch (TimeoutException ex) {
			fail("\"+\" was not input.");
		}
		
		// #19: Entering "-" and check whether the value was input correctly into the text field
		driver.findElement(By.xpath("//div[@jsname=\'pPHzQc\']")).click();
								
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "1 -"));
		} catch (TimeoutException ex) {
			fail("\"-\" was not input.");
		}
	
		// #20: Entering "2" and checking whether the value was input into the text field
		driver.findElement(By.xpath("//div[@jsname=\'lVjWed\']")).click();
										
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "1 - 2"));
		} catch (TimeoutException ex) {
			fail("\"2\" was not input.");
		}
				
		// #21: Pressing "=" and checking whether the value was solved correctly
		driver.findElement(By.xpath("//div[@jsname=\'Pt8tGc\']")).click();
												
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "-1"));
		} catch (TimeoutException ex) {
			fail("\"-1\" was not the solution.");
		}
	
		// #22: Pressing "AC" button and checking whether the value was cleared
		driver.findElement(By.xpath("//div[@jsname=\'SLn8gc\']")).click();														
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "0"));
		} catch (TimeoutException ex) {
			fail("Output not cleared to \"0\".");
		}
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// #23: Solve "87 + 52" with correct usage of WebDriverWait
		driver.findElement(By.xpath("//div[@jsname=\'T7PMFe\']")).click(); 
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "8"));
		} catch (TimeoutException ex) {
			fail("\"8\" was not input.");
		}
		driver.findElement(By.xpath("//div[@jsname=\'rk7bOd\']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "87"));
		} catch (TimeoutException ex) {
			fail("\"7\" was not input.");
		}
		driver.findElement(By.xpath("//div[@jsname=\'XSr6wc\']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "87 +"));
		} catch (TimeoutException ex) {
			fail("\"+\" was not input.");
		}
		driver.findElement(By.xpath("//div[@jsname=\'Ax5wH\']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "87 + 5"));
		} catch (TimeoutException ex) {
			fail("\"5\" was not input.");
		}
		driver.findElement(By.xpath("//div[@jsname=\'lVjWed\']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "87 + 52"));
		} catch (TimeoutException ex) {
			fail("\"2\" was not input.");
		}
		driver.findElement(By.xpath("//div[@jsname=\'Pt8tGc\']")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "139"));
		} catch (TimeoutException ex) {
			fail("\"139\" was not the solution.");
		}
		
		driver.findElement(By.xpath("//div[@jsname=\'SLn8gc\']")).click();
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "0"));
		} catch (TimeoutException ex) {
			fail("Output not cleared to \"0\".");
		}
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// #24: Solve "63 × 21" with correct usage of WebDriverWait
		driver.findElement(By.xpath("//div[@jsname=\'abcgof\']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "6"));
		} catch (TimeoutException ex) {
			fail("\"6\" was not input.");
		}
		driver.findElement(By.xpath("//div[@jsname=\'KN1kY\']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "63"));
		} catch (TimeoutException ex) {
			fail("\"3\" was not input.");
		}
		driver.findElement(By.xpath("//div[@jsname=\'YovRWb\']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "63 ×"));
		} catch (TimeoutException ex) {
			fail("\"×\" was not input.");
		}
		driver.findElement(By.xpath("//div[@jsname=\'lVjWed\']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "63 × 2"));
		} catch (TimeoutException ex) {
			fail("\"2\" was not input.");
		}
		driver.findElement(By.xpath("//div[@jsname=\'N10B9\']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "63 × 21"));
		} catch (TimeoutException ex) {
			fail("\"1\" was not input.");
		}
		driver.findElement(By.xpath("//div[@jsname=\'Pt8tGc\']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "1323"));
		} catch (TimeoutException ex) {
			fail("\"1323\" was not the solution.");
		}
		driver.findElement(By.xpath("//div[@jsname=\'SLn8gc\']")).click(); 
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "0"));
		} catch (TimeoutException ex) {
			fail("Output not cleared to \"0\".");
		}
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		
		// #25: Solve "45 ÷ 9" with correct usage of WebDriverWait
		driver.findElement(By.xpath("//div[@jsname=\'xAP7E\']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "4"));
		} catch (TimeoutException ex) {
			fail("\"4\" was not input.");
		}
		driver.findElement(By.xpath("//div[@jsname=\'Ax5wH\']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "45"));
		} catch (TimeoutException ex) {
			fail("\"5\" was not input.");
		}
		driver.findElement(By.xpath("//div[@jsname=\'WxTTNd\']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "45 ÷"));
		} catch (TimeoutException ex) {
			fail("\"÷\" was not input.");
		}
		driver.findElement(By.xpath("//div[@jsname=\'XoxYJ\']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "45 ÷ 9"));
		} catch (TimeoutException ex) {
			fail("\"9\" was not input.");
		}
		driver.findElement(By.xpath("//div[@jsname=\'Pt8tGc\']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "5"));
		} catch (TimeoutException ex) {
			fail("\"5\" was not the solution.");
		}
		driver.findElement(By.xpath("//div[@jsname=\'SLn8gc\']")).click(); 
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "0"));
		} catch (TimeoutException ex) {
			fail("Output not cleared to \"0\".");
		}
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		
		// #26: Solve "72 ÷ 10" with correct usage of WebDriverWait
		driver.findElement(By.xpath("//div[@jsname=\'rk7bOd\']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "7"));
		} catch (TimeoutException ex) {
			fail("\"7\" was not input.");
		}
		driver.findElement(By.xpath("//div[@jsname=\'lVjWed\']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "72"));
		} catch (TimeoutException ex) {
			fail("\"2\" was not input.");
		}
		driver.findElement(By.xpath("//div[@jsname=\'WxTTNd\']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "72 ÷"));
		} catch (TimeoutException ex) {
			fail("\"÷\" was not input.");
		}
		driver.findElement(By.xpath("//div[@jsname=\'N10B9\']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "72 ÷ 1"));
		} catch (TimeoutException ex) {
			fail("\"1\" was not input.");
		}
		driver.findElement(By.xpath("//div[@jsname=\'bkEvMb\']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "72 ÷ 10"));
		} catch (TimeoutException ex) {
			fail("\"0\" was not input.");
		}
		driver.findElement(By.xpath("//div[@jsname=\'Pt8tGc\']")).click();		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "7.2"));
		} catch (TimeoutException ex) {
			fail("\"7.2\" was not the solution.");
		}		
		driver.findElement(By.xpath("//div[@jsname=\'SLn8gc\']")).click();		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "0"));
		} catch (TimeoutException ex) {
			fail("Output not cleared to \"0\".");
		}
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
			
		// #27: Solve "log(58 × 6 ÷ 2 - 74))" with correct usage of WebDriverWait
		driver.findElement(By.xpath("//div[@jsname=\'DfiOAc\']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "log("));
		} catch (TimeoutException ex) {
			fail("\"log\" was not input.");
		}
		driver.findElement(By.xpath("//div[@jsname=\'Ax5wH\']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "log(5"));
		} catch (TimeoutException ex) {
			fail("\"5\" was not input.");
		}
		driver.findElement(By.xpath("//div[@jsname=\'T7PMFe\']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "log(58"));
		} catch (TimeoutException ex) {
			fail("\"8\" was not input.");
		}
		driver.findElement(By.xpath("//div[@jsname=\'YovRWb\']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "log(58 ×"));
		} catch (TimeoutException ex) {
			fail("\"×\" was not input.");
		}
		driver.findElement(By.xpath("//div[@jsname=\'abcgof\']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "log(58 × 6"));
		} catch (TimeoutException ex) {
			fail("\"6\" was not input.");
		}
		driver.findElement(By.xpath("//div[@jsname=\'WxTTNd\']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "log(58 × 6 ÷"));
		} catch (TimeoutException ex) {
			fail("\"÷\" was not input.");
		}
		driver.findElement(By.xpath("//div[@jsname=\'lVjWed\']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "log(58 × 6 ÷ 2"));
		} catch (TimeoutException ex) {
			fail("\"2\" was not input.");
		}
		driver.findElement(By.xpath("//div[@jsname=\'pPHzQc\']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "log(58 × 6 ÷ 2 -"));
		} catch (TimeoutException ex) {
			fail("\"-\" was not input.");
		}
		driver.findElement(By.xpath("//div[@jsname=\'rk7bOd\']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "log(58 × 6 ÷ 2 - 7"));
		} catch (TimeoutException ex) {
			fail("\"7\" was not input.");
		}
		driver.findElement(By.xpath("//div[@jsname=\'xAP7E\']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "log(58 × 6 ÷ 2 - 74"));
		} catch (TimeoutException ex) {
			fail("\"4\" was not input.");
		}
		driver.findElement(By.xpath("//div[@jsname=\'Pt8tGc\']")).click(); 		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "2"));
		} catch (TimeoutException ex) {
			fail("\"2\" was not the solution.");
		}		
		driver.findElement(By.xpath("//div[@jsname=\'SLn8gc\']")).click(); 		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElement(calculatorText, "0"));
		} catch (TimeoutException ex) {
			fail("Output not cleared to \"0\".");
		}
		
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

	// Calculator Implicit Test

	
	@Test
	public void testCalculatorImplicitly() throws InterruptedException {
		FirefoxDriver driver = (FirefoxDriver) WebTesting.launchDriver("https://www.google.com", "firefox");
		
		if (driver == null) {
			fail("Driver not created.");
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.id("L2AGLb")).click();
		
		
		// #28: Entering "calculator" into the search bar then press the "Return" key
		try {
			driver.findElement(By.name("q")).sendKeys("calculator" + Keys.RETURN);
		} catch (NoSuchElementException ex) {
			fail("Search bar not found.");
		}
		
		
		
		// #29: Ensuring that the Calculator is visible
		try {
			driver.findElement(By.cssSelector("div.tyYmIf"));
		} catch (NoSuchElementException ex) {
			fail("Calculator not found.");
		}
		
		
		// #30: Entering 1 and checking whether the value was input into the text field
		driver.findElement(By.cssSelector("div[jsname=\'N10B9\']")).click();
		
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'1\']"));
		} catch (NoSuchElementException ex) {
			fail("\"1\" was not input.");
		}

		// #31: Entering "+" and checking whether the value was input into the text field
		driver.findElement(By.cssSelector("div[jsname=\'XSr6wc\']")).click();
		
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'1 + \']"));
		} catch (NoSuchElementException ex) {
			fail("\"+\" was not input.");
		}

		
		// #32: Entering "-" and checking whether the value was input correctly into the text field
		driver.findElement(By.cssSelector("div[jsname=\'pPHzQc\']")).click();
		
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'1 - \']"));
		} catch (NoSuchElementException ex) {
			fail("\"-\" was not input.");
		}

		// #33: Entering "2" and checking whether the value was input into the text field
		driver.findElement(By.cssSelector("div[jsname=\'lVjWed\']")).click();
		
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'1 - 2\']"));
		} catch (NoSuchElementException ex) {
			fail("\"2\" was not input.");
		}

		// #34: Pressing "=" and checking whether the value was solved correctly
		driver.findElement(By.cssSelector("div[jsname=\'Pt8tGc\']")).click();
		
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'-1\']"));
		} catch (NoSuchElementException ex) {
			fail("\"-1\" was not the solution.");
		}
			
		// #35: Pressing "AC" button and checking whether the value was cleared
		driver.findElement(By.cssSelector("div[jsname=\'SLn8gc\']")).click();
		
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'0\']"));
		} catch (NoSuchElementException ex) {
			fail("Output not cleared to \"0\".");
		}
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		

		// #36: Solve "87 + 52" with correct usage of ImplicitlyWait	
		driver.findElement(By.cssSelector("div[jsname=\'T7PMFe\']")).click();		
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'8\']"));
		} catch (NoSuchElementException ex) {
			fail("\"8\" was not input.");
		}
		
		driver.findElement(By.cssSelector("div[jsname=\'rk7bOd\']")).click();
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'87\']"));
		} catch (NoSuchElementException ex) {
			fail("\"7\" was not input.");
		}
		
		driver.findElement(By.cssSelector("div[jsname=\'XSr6wc\']")).click();
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'87 + \']"));
		} catch (NoSuchElementException ex) {
			fail("\"+\" was not input.");
		}
		
		driver.findElement(By.cssSelector("div[jsname=\'Ax5wH\']")).click();
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'87 + 5\']"));
		} catch (NoSuchElementException ex) {
			fail("\"5\" was not input.");
		}
		
		driver.findElement(By.cssSelector("div[jsname=\'lVjWed\']")).click();
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'87 + 52\']"));
		} catch (NoSuchElementException ex) {	
			fail("\"2\" was not input.");
		}
		
		driver.findElement(By.cssSelector("div[jsname=\'Pt8tGc\']")).click();		
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'139\']"));
		} catch (NoSuchElementException ex) {
			fail("\"139\" was not the solution.");
		}
		
		
		driver.findElement(By.cssSelector("div[jsname=\'SLn8gc\']")).click();
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'0\']"));
		} catch (NoSuchElementException ex) {
			fail("Output not cleared to \"0\".");
		}
		
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		
		
		// #37: Solve "63 × 21" with correct usage of ImplicitlyWait
		driver.findElement(By.cssSelector("div[jsname=\'abcgof\']")).click();		
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'6\']"));
		} catch (NoSuchElementException ex) {
			fail("\"6\" was not input.");
		}
		
		driver.findElement(By.cssSelector("div[jsname=\'KN1kY\']")).click();
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'63\']"));
		} catch (NoSuchElementException ex) {
			fail("\"3\" was not input.");
		}
		
		driver.findElement(By.cssSelector("div[jsname=\'YovRWb\']")).click();
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'63 × \']"));
		} catch (NoSuchElementException ex) {
			fail("\"×\" was not input.");
		}
		
		driver.findElement(By.cssSelector("div[jsname=\'lVjWed\']")).click();
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'63 × 2\']"));
		} catch (NoSuchElementException ex) {
			fail("\"2\" was not input.");
		}
		
		driver.findElement(By.cssSelector("div[jsname=\'N10B9\']")).click();
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'63 × 21\']"));
		} catch (NoSuchElementException ex) {	
			fail("\"1\" was not input.");
		}
		
		driver.findElement(By.cssSelector("div[jsname=\'Pt8tGc\']")).click();		
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'1323\']"));
		} catch (NoSuchElementException ex) {
			fail("\"1323\" was not the solution.");
		}
		
		
		driver.findElement(By.cssSelector("div[jsname=\'SLn8gc\']")).click();
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'0\']"));
		} catch (NoSuchElementException ex) {
			fail("Output not cleared to \"0\".");
		}
		
		// #38: Solve "45 ÷ 9" with correct usage of ImplicitlyWait
		driver.findElement(By.cssSelector("div[jsname=\'xAP7E\']")).click();
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'4\']"));
		} catch (NoSuchElementException ex) {
			fail("\"4\" was not input.");
		}
		
		driver.findElement(By.cssSelector("div[jsname=\'Ax5wH\']")).click();
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'45\']"));
		} catch (NoSuchElementException ex) {
			fail("\"5\" was not input.");
		}
		
		driver.findElement(By.cssSelector("div[jsname=\'WxTTNd\']")).click();
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'45 ÷ \']"));
		} catch (NoSuchElementException ex) {
			fail("\"÷\" was not input.");
		}
		
		driver.findElement(By.cssSelector("div[jsname=\'XoxYJ\']")).click();
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'45 ÷ 9\']"));
		} catch (NoSuchElementException ex) {
			fail("\"9\" was not input.");
		}
		
		driver.findElement(By.cssSelector("div[jsname=\'Pt8tGc\']")).click();
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'5\']"));
		} catch (NoSuchElementException ex) {
			fail("\"5\" was not the solution.");
		}
		
		driver.findElement(By.cssSelector("div[jsname=\'SLn8gc\']")).click();
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'0\']"));
		} catch (NoSuchElementException ex) {
			fail("Output not cleared to \"0\".");
		}
		
		
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		
		// #39: Solve "72 ÷ 10" with correct usage of ImplicitlyWait
		driver.findElement(By.cssSelector("div[jsname=\'rk7bOd\']")).click();		
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'7\']"));
		} catch (NoSuchElementException ex) {
			fail("\"7\" was not input.");
		}
		
		driver.findElement(By.cssSelector("div[jsname=\'lVjWed\']")).click();		
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'72\']"));
		} catch (NoSuchElementException ex) {
			fail("\"2\" was not input.");
		}
		
		driver.findElement(By.cssSelector("div[jsname=\'WxTTNd\']")).click();		
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'72 ÷ \']"));
		} catch (NoSuchElementException ex) {
			fail("\"÷\" was not input.");
		}
		
		driver.findElement(By.cssSelector("div[jsname=\'N10B9\']")).click();		
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'72 ÷ 1\']"));
		} catch (NoSuchElementException ex) {
			fail("\"1\" was not input.");
		}
		
		driver.findElement(By.cssSelector("div[jsname=\'bkEvMb\']")).click();		
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'72 ÷ 10\']"));
		} catch (NoSuchElementException ex) {	
			fail("\"0\" was not input.");
		}
		
		driver.findElement(By.cssSelector("div[jsname=\'Pt8tGc\']")).click(); 
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'7.2\']"));
		} catch (NoSuchElementException ex) {
			fail("\"7.2\" was not the solution.");
		}
		
		driver.findElement(By.cssSelector("div[jsname=\'SLn8gc\']")).click();
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'0\']"));
		} catch (NoSuchElementException ex) {
			fail("Output not cleared to \"0\".");
		}
		
		
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		
		// #40: Solve "log(58 × 6 ÷ 2 - 74)" with correct usage of ImplicitlyWait
		driver.findElement(By.cssSelector("div[jsname=\'DfiOAc\']")).click();
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'log(\']"));
		} catch (NoSuchElementException ex) {
			fail("\"log\" was not input.");
		}
		driver.findElement(By.cssSelector("div[jsname=\'Ax5wH\']")).click();
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'log(5\']"));
		} catch (NoSuchElementException ex) {
			fail("\"5\" was not input.");
		}
		driver.findElement(By.cssSelector("div[jsname=\'T7PMFe\']")).click();
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'log(58\']"));
		} catch (NoSuchElementException ex) {
			fail("\"8\" was not input.");
		}
		driver.findElement(By.cssSelector("div[jsname=\'YovRWb\']")).click();
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'log(58 × \']"));
		} catch (NoSuchElementException ex) {
			fail("\"×\" was not input.");
		}
		driver.findElement(By.cssSelector("div[jsname=\'abcgof\']")).click();
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'log(58 × 6\']"));
		} catch (NoSuchElementException ex) {
			fail("\"6\" was not input.");
		}
		driver.findElement(By.cssSelector("div[jsname=\'WxTTNd\']")).click();
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'log(58 × 6 ÷ \']"));
		} catch (NoSuchElementException ex) {
			fail("\"÷\" was not input.");
		}
		driver.findElement(By.cssSelector("div[jsname=\'lVjWed\']")).click();
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'log(58 × 6 ÷ 2\']"));
		} catch (NoSuchElementException ex) {
			fail("\"2\" was not input.");
		}
		driver.findElement(By.cssSelector("div[jsname=\'pPHzQc\']")).click();
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'log(58 × 6 ÷ 2 - \']"));
		} catch (NoSuchElementException ex) {
			fail("\"-\" was not input.");
		}
		driver.findElement(By.cssSelector("div[jsname=\'rk7bOd\']")).click();
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'log(58 × 6 ÷ 2 - 7\']"));
		} catch (NoSuchElementException ex) {
			fail("\"7\" was not input.");
		}
		driver.findElement(By.cssSelector("div[jsname=\'xAP7E\']")).click();
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'log(58 × 6 ÷ 2 - 74\']"));
		} catch (NoSuchElementException ex) {
			fail("\"4\" was not input.");
		}		
		driver.findElement(By.cssSelector("div[jsname=\'Pt8tGc\']")).click();		
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'2\']"));
		} catch (NoSuchElementException ex) {
			fail("\"2\" was not the solution.");
		}		
		driver.findElement(By.cssSelector("div[jsname=\'SLn8gc\']")).click();		
		try {
			driver.findElement(By.xpath("//span[@id=\'cwos\' and text()=\'0\']"));
		} catch (NoSuchElementException ex) {
			fail("Output not cleared to \"0\".");
		}
		
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
