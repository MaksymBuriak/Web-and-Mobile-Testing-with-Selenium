package web_testing_app;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.chrome.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;


public class WebTesting {
	private static WebDriver localDriver;
	
	
	public static WebDriver getDriver() {
		return localDriver;
	}
	
	
	public static void initSystemProperties() {
		// Collect path to drivers
		Path currentRelativePath = Paths.get("");

		String geckoPath = Paths.get(currentRelativePath.toAbsolutePath().toString(), "drivers/geckodriver").toString();
		geckoPath = geckoPath.replace("\\", "/");
		System.out.println(geckoPath);
		
		String chromePath = Paths.get(currentRelativePath.toAbsolutePath().toString(), "drivers/chromedriver").toString();
		chromePath = chromePath.replace("\\", "/");
		System.out.println(chromePath);
				
		// Firefox Driver
		/** Tested for Firefox Version 85.0.2    **/
		/** Tested for geckdriver Version 0.29.0 **/
		System.setProperty("webdriver.gecko.driver", geckoPath);
		
		// Chrome Driver
		/** Tested for Chrome Version 89.0.4389.72       **/
		/** Tested for chromedriver Version 88.0.4324.96 **/
		System.setProperty("webdriver.chrome.driver", chromePath);
		System.setProperty("webdriver.chrome.whitelistedIps", "");
	}
	
	
	public static WebDriver launchDriver(String siteUrl, String browser) {
		if (browser.equals("firefox")) {
			// Set options for Firefox
			FirefoxOptions options = new FirefoxOptions()
							.setBinary("/Applications/Firefox.app/Contents/MacOS/firefox") // Path to Firefox binary
					     	.addPreference("browser.startup.page", 1)
					     	.addPreference("browser.startup.homepage", siteUrl)
					     	.setAcceptInsecureCerts(true)
					     	.setHeadless(false);
				
			// Browser is launched on creation of the driver
			quitDriver();
			localDriver = new FirefoxDriver(options);
		} else if (browser.equals("chrome")) {
			String userDataDir = "/Users/maksymburiak/Library/Application Support/Google/Chrome/Test";
			String profileName = "Profile 1";
			// Set options for Chrome
			ChromeOptions options = new ChromeOptions()
//							.setBinary("/Users/maksymburiak/Desktop/chrome-mac-arm64/Google Chrome for Testing.app")
							.addArguments("user-data-dir=" + userDataDir)
							.addArguments("profile-directory=" + profileName)
							.addArguments("--homepage \""+siteUrl+"\"")
							.setAcceptInsecureCerts(true)
							.setHeadless(false)
							.addArguments("chromedriver --whitelisted-ips=\"\"");
				
			// Browser is launched on creation of the driver
			quitDriver();
			localDriver = new ChromeDriver(options);


		}
		
		return localDriver;
	}
	
	public static void quitDriver() {
		if (localDriver != null) {
			localDriver.quit();
			localDriver = null;
		}
	}
}
