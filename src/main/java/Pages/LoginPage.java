package Pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.testproject.sdk.drivers.android.AndroidDriver;
import org.openqa.selenium.By;

public class LoginPage {

    AndroidDriver driver;

    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
    }

    private MobileElement getName() {
        return (MobileElement) driver.findElement(By.id("io.testproject.demo:id/name"));
    }
    private MobileElement getPassword() {
        return (MobileElement) driver.findElement(By.id("io.testproject.demo:id/password"));
    }
    private MobileElement getLoginButton() {
        return (MobileElement) driver.findElement(By.id("io.testproject.demo:id/login"));
    }
    private MobileElement getGreeting() {
        return (MobileElement) driver.findElement(By.id("io.testproject.demo:id/greetings"));
    }

    public void login(String user, String pass) {
        new TouchAction(driver).tap(TapOptions.tapOptions().withElement(ElementOption.element(getName()))).perform();
        getName().sendKeys(user);
        new TouchAction(driver).tap(TapOptions.tapOptions().withElement(ElementOption.element(getPassword()))).perform();
        getPassword().sendKeys(pass);
        new TouchAction(driver).tap(TapOptions.tapOptions().withElement(ElementOption.element(getLoginButton()))).perform();
    }

    public boolean isLoginSuccessful(String name) {
        return getGreeting().getText().contains(name);
    }

}
