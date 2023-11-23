
package general;

import com.codeborne.selenide.testng.TextReport;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.ByteArrayInputStream;
import java.net.URL;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.qameta.allure.Allure;

public class Base

{
	public TextReport report = new TextReport();

	public static DesiredCapabilities cap = new DesiredCapabilities();

	public static AndroidDriver<MobileElement> driver;

	public static void Home() throws Exception

	{
		// Confirm that you are on Home screen
		WebDriverWait wait = new WebDriverWait(driver, 20);
		// Wait to load the screen For Home
		WebElement div = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Home']")));
		// Print that You are on Home screen
		div.click();
	}

	@BeforeSuite
	public void LoginTOApplication() throws Exception {

		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);

		cap.setCapability(MobileCapabilityType.NO_RESET, true); // It will always clear the caches

		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
		// cap.setCapability(MobileCapabilityType.DEVICE_NAME,"a1e5e8e3");

		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub/"), cap);
		WebDriverWait wait = new WebDriverWait(driver, 20);

		driver.startActivity(new Activity("com.impossible_research.sandbox.starboard","com.impossible_research.sandbox.starboard.ui.splash.view.SplashActivity"));
		
		System.out.println("Starboard app opened");
		
		try {

			System.out.println("Installed App found");
			
			WebElement div1 = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//android.widget.TextView[@text='Sign in using your 5 digit pin.']")));

			if (div1.isDisplayed()) {

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

				WebElement div = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[@text='Home']")));

			}
		}

		catch (Exception e)

		{
			System.out.println(e);
			Allure.addAttachment("AllureSelenide",
					new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

		}

		Home();
	}

	@AfterSuite
	public void TearDown() throws Exception

	{

		//driver.terminateApp("com.impossible_research.sandbox.starboard");

		Reporter.log("==========Application closed==========", true);

	}

}
