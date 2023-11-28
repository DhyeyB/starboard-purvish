
package general;

import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.ByteArrayInputStream;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.codeborne.selenide.testng.TextReport;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.qameta.allure.Allure;

public class Login_test

{

	public TextReport report = new TextReport();

	static DesiredCapabilities cap = new DesiredCapabilities();

	public static AndroidDriver<MobileElement> driver;

	public static void Home() throws Exception

	{
		try {
			// Confirm that you are on Home screen
			WebDriverWait wait = new WebDriverWait(driver, 30);
			
			// Wait to load the screen For Home
			WebElement div = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Home']")));
			
			// Print that You are on Home screen
			div.click();
		}

		catch (Exception e)

		{

			System.out.println(e);
			Allure.addAttachment("AllureSelenide",
					new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

		}
	}

	public static void login() throws Exception

	{
		try

		{

			WebDriverWait wait = new WebDriverWait(driver, 30);
			
			// Clicked on Sign in button
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text=\"Sign in\"]")));

			driver.findElement(By.xpath("//android.widget.TextView[@text=\"Sign in\"]")).click();

			// click on email textbox
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText[@index='0']")));

			driver.findElement(By.xpath("//android.widget.EditText[@index='0']"))

					.sendKeys("pandyapurvish1708@gmail.com"); // User for Staging environment

			driver.findElement(By.xpath("//android.widget.TextView[@text='Send Magic Link']")).click();
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='OK']")));
			driver.findElement(By.xpath("//android.widget.TextView[@text='OK']")).click();

			driver.closeApp();

			// Open Gmail
			driver.activateApp("com.google.android.gm");

			// Wait for new magic link mail
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("(//android.widget.TextView[@text='Magic link to sign in to the app.']) [1]")));

			driver.findElement(By.xpath("(//android.widget.TextView[@text='Magic link to sign in to the app.']) [1]"))
					.click();

			// Scroll and Click on magic link
			MobileElement ele = driver.findElement(MobileBy.AndroidUIAutomator(
					"new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"Login to Starboard\"))"));
			ele.click();

			// Enter Your PIN
			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//android.widget.TextView[@text='Enter your new PIN']")));

			// Click on 1
			driver.findElement(By.xpath("//android.widget.TextView[@text='1']")).click();

			// Click on 2
			driver.findElement(By.xpath("//android.widget.TextView[@text='2']")).click();

			// Click on 3
			driver.findElement(By.xpath("//android.widget.TextView[@text='3']")).click();

			// Click on 4
			driver.findElement(By.xpath("//android.widget.TextView[@text='4']")).click();

			// Click on 5
			driver.findElement(By.xpath("//android.widget.TextView[@text='5']")).click();

			// WebDriverWait wait2 = new WebDriverWait(driver, 20);

			WebElement div2 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//android.widget.TextView[@text='Confirm your new PIN']")));

			// Click on 1
			driver.findElement(By.xpath("//android.widget.TextView[@text='1']")).click();

			// Click on 2
			driver.findElement(By.xpath("//android.widget.TextView[@text='2']")).click();

			// Click on 3
			driver.findElement(By.xpath("//android.widget.TextView[@text='3']")).click();

			// Click on 4
			driver.findElement(By.xpath("//android.widget.TextView[@text='4']")).click();

			// Click on 5
			driver.findElement(By.xpath("//android.widget.TextView[@text='5']")).click();

			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='PIN Set Up Successful']")));

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[@text='Skip']")))
					.click();

			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("(//*[@resource-id='com.impossible_research.sandbox.starboard:id/head']) [1]")));
			driver.findElement(By.xpath("(//*[@resource-id='com.impossible_research.sandbox.starboard:id/head']) [1]"))
					.getText();
			driver.findElement(By.xpath("(//*[@resource-id='com.impossible_research.sandbox.starboard:id/head']) [1]"))
					.click();
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[@text='Allow']")))
			// 		.click();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[@text='Home']")));

		}

		catch (Exception e1)

		{

			System.out.println(e1);
			Allure.addAttachment("AllureSelenide",
					new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

		}

	}

	@BeforeSuite
	public void LoginTOApplication() throws Exception {
		try

		{

			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);

			cap.setCapability(MobileCapabilityType.NO_RESET, true); // It will always clear the caches

			cap.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");

			// cap.setCapability("app", "/home/bs/Documents/starboard-Android/starboard/apk/Starboard-dev.apk");

			driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub/"), cap);

			WebDriverWait wait = new WebDriverWait(driver, 50);

			// Close starboard app
			// driver.closeApp();
	

			// Open Gmail
			driver.activateApp("com.google.android.gm");
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[@text='Allow']")))
					.click();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[@text='GOT IT']")))
					.click();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[@text='Add an email address']"))).click();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[@text='Google']")))
					.click();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[@text='Sign in']"))).click();;

			driver.findElement(By.xpath("//android.widget.TextView[@index='2']")).click();

			// Enter Email Address
			driver.findElement(By.xpath("//android.widget.EditText[@text='']")).sendKeys("pandyapurvish1708@gmail.com");
			
			//Hide the keyboard
			driver.hideKeyboard();
			
			driver.findElement(By.xpath("//android.widget.Button[@text='Next']")).click();

			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Show password']")));
			driver.findElement(By.xpath("//android.widget.EditText[@index='0']")).click();

			// Enter Password
			driver.findElement(By.xpath("//android.widget.EditText[@text='']")).sendKeys("jayshreekrishna@789");
			driver.hideKeyboard();
			driver.findElement(By.xpath("//android.widget.Button[@text='Next']")).click();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[@text='I agree']")))
					.click();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[@text='MORE']")))
					.click();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[@text='ACCEPT']")))
					.click();

			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//android.widget.TextView[@text='pandyapurvish1708@gmail.com']")));

			driver.findElement(By.xpath("//android.widget.TextView[@text='TAKE ME TO GMAIL']")).click();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[@text='Got it']")))
					.click();

			driver.terminateApp("com.google.android.gm");

			driver.startActivity(new Activity("com.impossible_research.sandbox.starboard",
					"com.impossible_research.sandbox.starboard.ui.splash.view.SplashActivity"));

			WebElement skip = wait.until(ExpectedConditions
					.elementToBeClickable(By.id("com.impossible_research.sandbox.starboard:id/skip_btn")));

			if (skip.isDisplayed()) {

				skip.click();

			}

		}

		catch (Exception e2)

		{

			System.out.println(e2);
			Allure.addAttachment("AllureSelenide",
					new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

		}

		login();

	}

	@AfterSuite
	public void TearDown() throws Exception

	{

		driver.terminateApp("com.google.android.gm");

		Reporter.log("==========Gamil closed==========", true);

		driver.terminateApp("com.impossible_research.sandbox.starboard");

		Reporter.log("==========Starboard closed==========", true);

	}

}