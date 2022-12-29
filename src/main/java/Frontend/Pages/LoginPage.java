package Frontend.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;

public class LoginPage {

    public By userName = By.id("userName");
    public By password = By.id("password");
    public By login = By.id("login");
    ChromeOptions options;
    public LoginPage(ChromeOptions options)
    {
        this.options = options;
    }
}
