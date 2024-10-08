package mobile_testing_app;


import mobile_testing_app.MobileTesting;
//import performance_testing_app.TimeoutException;
//import performance_testing_app.WebTesting;
import io.appium.java_client.android.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.interactions.*;

public class Assignment {
	private static String[] loginPageElements = {"com.experitest.ExperiBank:id/usernameTextField", "com.experitest.ExperiBank:id/passwordTextField", "com.experitest.ExperiBank:id/loginButton"};
	private static String[] summaryPageElements = {"com.experitest.ExperiBank:id/makePaymentButton", "com.experitest.ExperiBank:id/logoutButton"};
	private static String[] paymentPageElements = {"com.experitest.ExperiBank:id/phoneTextField", "com.experitest.ExperiBank:id/nameTextField", "com.experitest.ExperiBank:id/nameTextField",
											"com.experitest.ExperiBank:id/amountLabel", "com.experitest.ExperiBank:id/amount", "com.experitest.ExperiBank:id/countryTextField",
											"com.experitest.ExperiBank:id/countryButton", "com.experitest.ExperiBank:id/sendPaymentButton", "com.experitest.ExperiBank:id/cancelButton"};
	

	/* Start Here */

	@SuppressWarnings("unchecked")
	public static void run() throws NoSuchElementException {
		AndroidDriver<AndroidElement> driver = (AndroidDriver<AndroidElement>) MobileTesting.getDriver();
		
		long timeout = 6; // 6 seconds
		WebDriverWait wait = new WebDriverWait(driver, timeout);

		/* Start testing */
		AndroidElement element;
		Actions action = new Actions(driver);
		Alert alert;
		
		// (1) Check if on login page
		/* WRITE HERE */
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.experitest.ExperiBank:id/loginView")));
		} catch (TimeoutException ex) {
			ex.printStackTrace();
		}
		
		MobileTesting.printTest(1);
		
		// (2) Type the string "company" to the username field
		/* WRITE HERE */
		driver.findElement((By.id("com.experitest.ExperiBank:id/usernameTextField"))).sendKeys("company");
		
		MobileTesting.printTest(2);
		
		// (3) Focus the password field
		/* WRITE HERE */
		driver.findElement((By.id("com.experitest.ExperiBank:id/passwordTextField"))).click();
		
		MobileTesting.printTest(3);
		
		// (4) Type the string "company" to the password field
		/* WRITE HERE */
		driver.findElement((By.id("com.experitest.ExperiBank:id/passwordTextField"))).sendKeys("company");
		
		MobileTesting.printTest(4);
		
		// (5) Focus the username field
		/* WRITE HERE */
		driver.findElement((By.id("com.experitest.ExperiBank:id/usernameTextField"))).click();
		
		MobileTesting.printTest(5);
		
		// (6) Empty out the username field
		/* WRITE HERE */
		driver.findElement((By.id("com.experitest.ExperiBank:id/usernameTextField"))).clear();
		
		MobileTesting.printTest(6);
		
		// (7) Press the login button and ensure still on login page
		/* WRITE HERE */
		driver.findElement((By.id("com.experitest.ExperiBank:id/loginButton"))).click();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.experitest.ExperiBank:id/loginView")));
		} catch (TimeoutException ex) {
			ex.printStackTrace();
		}
		
		try {
			wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Toast[1]"))));
		} catch (TimeoutException ex) {
			ex.printStackTrace();
		}
		
		MobileTesting.printTest(7);
		
		// (8) Re-type "company" to the username field
		/* WRITE HERE */
		driver.findElement((By.id("com.experitest.ExperiBank:id/usernameTextField"))).sendKeys("company");
		
		MobileTesting.printTest(8);
		
		// (9) Press the login button and ensure now on summary page with $100
		/* WRITE HERE */
		driver.findElement((By.id("com.experitest.ExperiBank:id/loginButton"))).click();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.experitest.ExperiBank:id/paymentHomeView")));
		} catch (TimeoutException ex) {
			ex.printStackTrace();
		}
		
		MobileTesting.printTest(9);
		
		// (10) Press the make payment button and ensure now on payment page
		/* WRITE HERE */
		driver.findElement((By.id("com.experitest.ExperiBank:id/makePaymentButton"))).click();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.experitest.ExperiBank:id/makePaymentHomeView")));
		} catch (TimeoutException ex) {
			ex.printStackTrace();
		}
		
		MobileTesting.printTest(10);
		
		// (11) Type the string "612-555-0112" to the phone field
		/* WRITE HERE */
		driver.findElement((By.id("com.experitest.ExperiBank:id/phoneTextField"))).sendKeys("612-555-0112");
		
		MobileTesting.printTest(11);
		
		// (12) Type the string "Alice" to the name text field
		/* WRITE HERE */
		driver.findElement((By.id("com.experitest.ExperiBank:id/nameTextField"))).sendKeys("Alice");
		
		MobileTesting.printTest(12);
		
		// (13) Drag the amount slider to $35
		/* WRITE HERE */
		WebElement slider = driver.findElement(By.id("com.experitest.ExperiBank:id/amount"));
		int xAxisStartPoint = slider.getLocation().getX();
		int xAxisWidth = slider.getSize().getWidth();
		int yAxis = slider.getLocation().getY();
		//press the x & y coordinates on the screen to move the slider to 33%
		int xOffset = (int)((xAxisWidth * 0.33) - xAxisStartPoint);
		action.dragAndDropBy(slider, xOffset, yAxis).perform();
		
		MobileTesting.printTest(13);
		
		// (14) Press the send payment button and ensure still on payment page
		/* WRITE HERE */
		driver.findElement((By.id("com.experitest.ExperiBank:id/sendPaymentButton"))).click();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.experitest.ExperiBank:id/makePaymentHomeView")));
		} catch (TimeoutException ex) {
			ex.printStackTrace();
		}
		
		MobileTesting.printTest(14);
		
		// (15) Type the string "United States" to the country text field
		/* WRITE HERE */
		driver.findElement((By.id("com.experitest.ExperiBank:id/countryTextField"))).sendKeys("United States");
		
		MobileTesting.printTest(15);
		
		// (16) Press the cancel button and ensure now on summary page with $100
		/* WRITE HERE */
		driver.findElement((By.id("com.experitest.ExperiBank:id/cancelButton"))).click();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.experitest.ExperiBank:id/paymentHomeView")));
		} catch (TimeoutException ex) {
			ex.printStackTrace();
		}
		
		MobileTesting.printTest(16);
		
		// (17) Press the make payment button and ensure now on payment page with default values
		/* WRITE HERE */
		driver.findElement((By.id("com.experitest.ExperiBank:id/makePaymentButton"))).click();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.experitest.ExperiBank:id/makePaymentHomeView")));
		} catch (TimeoutException ex) {
			ex.printStackTrace();
		}	
		
		MobileTesting.printTest(17);
		
		// (18) Type the string "612-555-0355" to the phone field
		/* WRITE HERE */
		driver.findElement((By.id("com.experitest.ExperiBank:id/phoneTextField"))).sendKeys("612-555-0355");
		
		MobileTesting.printTest(18);
				
		// (19) Type the string "Bob" to the name text field
		/* WRITE HERE */
		driver.findElement((By.id("com.experitest.ExperiBank:id/nameTextField"))).sendKeys("Bob");
		
		MobileTesting.printTest(19);
		
		// (20) Drag the amount slider to $55
		/* WRITE HERE */
		//press the x & y coordinates on the screen to move the slider to 53%
		slider = driver.findElement(By.id("com.experitest.ExperiBank:id/amount"));
		xAxisStartPoint = slider.getLocation().getX();
		xAxisWidth = slider.getSize().getWidth();
		yAxis = slider.getLocation().getY();
		//press the x & y coordinates on the screen to move the slider to 53%
		xOffset = (int)((xAxisWidth * 0.53) - xAxisStartPoint);
		action.dragAndDropBy(slider, xOffset, yAxis).perform();
		
		MobileTesting.printTest(20);
		
		// (21) Press the country button
		/* WRITE HERE */
		driver.findElement((By.id("com.experitest.ExperiBank:id/countryButton"))).click();
		
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.experitest.ExperiBank:id/countryList")));
		} catch (TimeoutException ex) {
			ex.printStackTrace();
		}
		
		MobileTesting.printTest(21);
		
		// (22) Select France from the country list (13th on the list)
		/* WRITE HERE */
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"France\").instance(0))").click();

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.experitest.ExperiBank:id/makePaymentHomeView")));
		} catch (TimeoutException ex) {
			ex.printStackTrace();
		}	
		
		MobileTesting.printTest(22);
		
		// (23) Press the send payment button and ensure alert appears
		/* WRITE HERE */
		driver.findElement(By.id("com.experitest.ExperiBank:id/sendPaymentButton")).click();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/alertTitle")));
		} catch (TimeoutException ex) {
			ex.printStackTrace();
		}
		
		MobileTesting.printTest(23);
		
		// (24) Dismiss the alert and ensure now on payment page
		/* WRITE HERE */
		driver.findElement(By.xpath("//android.widget.Button[2]")).click();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.experitest.ExperiBank:id/makePaymentView")));
		} catch (TimeoutException ex) {
			ex.printStackTrace();
		}
		
		MobileTesting.printTest(24);
		
		// (25) Press the send payment button and ensure alert appears again
		/* WRITE HERE */
		driver.findElement(By.id("com.experitest.ExperiBank:id/sendPaymentButton")).click();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/alertTitle")));
		} catch (TimeoutException ex) {
			ex.printStackTrace();
		}
		
		MobileTesting.printTest(25);
		
		// (26) Accept the alert and ensure now on summary page
		/* WRITE HERE */
		driver.findElement(By.xpath("//android.widget.Button[1]")).click();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.experitest.ExperiBank:id/paymentHomeView")));
		} catch (TimeoutException ex) {
			ex.printStackTrace();
		}
		
		MobileTesting.printTest(26);
		
		// (27) Press the logout button and ensure now on login page
		/* WRITE HERE */
		driver.findElement(By.id("com.experitest.ExperiBank:id/logoutButton")).click();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.experitest.ExperiBank:id/loginView")));
		} catch (TimeoutException ex) {
			ex.printStackTrace();
		}
		
		MobileTesting.printTest(27);
	}
}
