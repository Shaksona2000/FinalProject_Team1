package Frontend.Steps;

import Frontend.Data.User;
import Frontend.Pages.LoginPage;
import io.qameta.allure.Step;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.$;

public class LoginPageSteps {
    ChromeOptions options;
    User user = new User();
    public LoginPageSteps(ChromeOptions options)
    {
        this.options = options;
    }

    LoginPage loginPage = new LoginPage(options);

    @Step("Fill User Name")
    public LoginPageSteps setUserName()
    {
        $(loginPage.userName).setValue(user.userName);
        return this;
    }

    @Step("Fill Password")
    public LoginPageSteps setPassword()
    {
        $(loginPage.password).setValue(user.password);
        return this;
    }

    @Step("Login")
    public LoginPageSteps login()
    {
        $(loginPage.login).click();
        return this;
    }
}
