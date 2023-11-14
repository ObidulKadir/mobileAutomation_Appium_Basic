package Package.mobileAutomation;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
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
	
	@Test (priority = 0)
	public void mainActivityPage() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Algeria\"]")).click();	
		driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.androidsample.generalstore:id/nameField\"]")).sendKeys("Hello");
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
	}
	
	@Test (priority = 1)
	public void productAddToCart() {
		driver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])[2]")).click();	
	}
	
	@Test (priority = 2)
	public void cartPage() throws InterruptedException {	
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		Thread.sleep(2000);
	}
	
	@AfterTest
	public void removeApp() {
		driver.removeApp("com.androidsample.generalstore");
	}
}