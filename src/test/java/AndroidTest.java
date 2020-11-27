import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.testproject.sdk.drivers.ReportingDriver;
import io.testproject.sdk.drivers.android.AndroidDriver;
import io.testproject.sdk.interfaces.junit5.ExceptionsReporter;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class AndroidTest implements ExceptionsReporter {

    private static AndroidDriver<? extends MobileElement> driver;


    @BeforeEach
    public void setup() throws Exception {
        driver = new AndroidDriver<>(System.getenv("TP_DEV_TOKEN"), getCapabilities(), "Android Test");
        driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
        driver.resetApp();
    }

    @Override
    public ReportingDriver getDriver() {
        return driver;
    }

    @Test
    public void loginTest() {

        MobileElement name = driver.findElement(By.id("io.testproject.demo:id/name"));
        MobileElement password = driver.findElement(By.id("io.testproject.demo:id/password"));
        MobileElement loginButton = driver.findElement(By.id("io.testproject.demo:id/login"));

        new TouchAction(driver).tap(TapOptions.tapOptions().withElement(ElementOption.element(name))).perform();
        name.sendKeys("Andreea");
        new TouchAction(driver).tap(TapOptions.tapOptions().withElement(ElementOption.element(password))).perform();
        password.sendKeys("12345");
        new TouchAction(driver).tap(TapOptions.tapOptions().withElement(ElementOption.element(loginButton))).perform();

        MobileElement greeting = driver.findElement(By.id("io.testproject.demo:id/greetings"));
        Assert.assertTrue(greeting.getText().contains("Andreea"));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private static DesiredCapabilities getCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "io.testproject.demo");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "io.testproject.demo.MainActivity");
        return capabilities;
    }

}
