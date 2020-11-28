import Pages.LoginPage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.testproject.sdk.drivers.ReportingDriver;
import io.testproject.sdk.drivers.android.AndroidDriver;
import io.testproject.sdk.interfaces.junit5.ExceptionsReporter;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class AndroidTest implements ExceptionsReporter {

    private static AndroidDriver<? extends MobileElement> driver;
    private LoginPage loginPage;

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
        var user = "Andreea";
        var pass = "12345";
        loginPage = new LoginPage(driver);
        loginPage.login(user, pass);
        Assert.assertTrue(loginPage.isLoginSuccessful(user));
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
