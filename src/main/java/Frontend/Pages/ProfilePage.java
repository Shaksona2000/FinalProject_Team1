package Frontend.Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.*;


public class ProfilePage {
    public By navigateToBookstore = By.id("gotoStore");
    public ElementsCollection books = $$(".mr-2 > a");
    public ElementsCollection deleteButtons = $$("#delete-record-undefined");
    public SelenideElement deletionConfirmation = $("#closeSmallModal-ok");
    ChromeOptions options;
    public ProfilePage(ChromeOptions options)
    {
        this.options = options;
    }
}
