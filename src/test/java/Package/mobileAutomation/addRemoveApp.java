package Package.mobileAutomation;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class addRemoveApp {

	AndroidDriver driver;
	
	@BeforeTest
	public void start() throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		File file = new File("./resources/General-Store.apk");
		cap.setCapability("platformName", "android");
		cap.setCapability("automationName", "UiAutomator2");
		cap.setCapability("deviceName", "Pixel");
		cap.setCapability("udid", "emulator-5554");
		cap.setCapability("app", file.getAbsolutePath());
		driver = new AndroidDriver(new URL("http://192.168.0.191:4723/"), cap);
	}
	
	@Test
	public void mainActivityPage() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Algeria\"]")).click();
		
		driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.androidsample.generalstore:id/nameField\"]")).sendKeys("Hello");
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
	}
	
	@AfterTest
	public void removeApp() {
		driver.removeApp("com.androidsample.generalstore");
	}
}
