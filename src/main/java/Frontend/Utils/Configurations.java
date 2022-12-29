package Frontend.Utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;

public class Configurations {
    public static ChromeOptions options;
    public Configurations() {
        options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--disable-notifications");
        Configuration.browserCapabilities = options;
        Configuration.driverManagerEnabled = true;
        Configuration.browserSize = null;
        baseUrl="https://demoqa.com/login";
        timeout=20000;
        reopenBrowserOnFail = true;
        fastSetValue=false;
        savePageSource=false;
        reportsFolder="src/main/resources/FailedTests";
        open(baseUrl);
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }

    public static WebDriver GetWebDriver() {
        return WebDriverRunner.getWebDriver();
    }
}
