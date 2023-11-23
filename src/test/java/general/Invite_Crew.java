package general;

import org.testng.Assert;
import org.testng.annotations.Test;

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
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Allure;

import java.sql.Timestamp;

//public class Invite_Crew extends Login_test
public class Invite_Crew extends Base

{

	@Test(priority = 1)
	public static void sent_invite() throws Exception {

		try

		{
			Home();

			WebDriverWait wait = new WebDriverWait(driver, 20);

			WebElement sent_invite = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Settings']")));

			// Click on Settings
			driver.findElement(By.xpath("//android.widget.TextView[@text='Settings']")).click();

			// Confirm that you are on Settings page
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//android.widget.TextView[@text='Settings' and @resource-id='com.impossible_research.sandbox.starboard:id/head_txt']")));

			// Confirm that you are on Yacht Settings
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Yacht Settings']")));

			driver.findElement(By.xpath("//android.widget.TextView[@text='Yacht Settings']")).click();

			// Click on Manage crew
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Manage Crew']"))).click();

			// Confirm that you are on Manage crew page
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Manage Crew']")));

			// Click on Add invite crew button
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//android.widget.ImageView[@text='' and @resource-id='com.impossible_research.sandbox.starboard:id/end_button_icon']")))
					.click();

			Timestamp timestamp = new Timestamp(System.currentTimeMillis());

			// Click on Enter Details button
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Enter Details']"))).click();

			// Confirm that you are on Send Crew Request page
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Send Crew Request']")));

			// Add Position name
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//android.widget.EditText[@text='' and @resource-id='com.impossible_research.sandbox.starboard:id/designation']")))
					.click();
			driver.findElement(By.xpath(
					"//android.widget.EditText[@text='' and @resource-id='com.impossible_research.sandbox.starboard:id/designation']"))
					.sendKeys("QA");
			driver.hideKeyboard();

			// Add First name
			driver.findElement(By.xpath(
					"//android.widget.EditText[@text='' and @resource-id='com.impossible_research.sandbox.starboard:id/name']"))
					.click();

			driver.findElement(By.xpath(
					"//android.widget.EditText[@text='' and @resource-id='com.impossible_research.sandbox.starboard:id/name']"))
					.sendKeys("Nia" + timestamp);
			driver.hideKeyboard();

			// Add Last name
			driver.findElement(By.xpath(
					"//android.widget.EditText[@text='' and @resource-id='com.impossible_research.sandbox.starboard:id/last_name']"))
					.click();

			driver.findElement(By.xpath(
					"//android.widget.EditText[@text='' and @resource-id='com.impossible_research.sandbox.starboard:id/last_name']"))
					.sendKeys("Shah" + timestamp);
			driver.hideKeyboard();

			// Add Email
			driver.findElement(By.xpath(
					"//android.widget.EditText[@text='' and @resource-id='com.impossible_research.sandbox.starboard:id/email']"))
					.click();

			String myEmailAddress = "purvish+" + System.nanoTime() + "@Gmail.com";

			driver.findElement(By.xpath(
					"//android.widget.EditText[@text='' and @resource-id='com.impossible_research.sandbox.starboard:id/email']"))
					.sendKeys(myEmailAddress);
			
			driver.hideKeyboard();

			// Clicked on Send Magic link button
			driver.findElement(By.xpath("//android.widget.TextView[@text='Send Magic Link']")).click();

			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//android.widget.TextView[@text='Crew Invite sent successfully.']")));

			driver.findElement(By.xpath("//android.widget.TextView[@text='OK']")).click();

		}

		catch (Exception e)

		{
			Allure.addAttachment("AllureSelenide",
					new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
			Assert.fail(e.getMessage());

		}

	}

}