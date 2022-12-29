package Frontend.Pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$x;

public class BookPage {
    public By backToBookStore = byText("Back To Book Store");
    public By addToYourCollection = byText("Add To Your Collection");
    public SelenideElement profile = $x("//div[@class = 'element-group'][6]/div/ul/li[3]");
    ChromeOptions options;
    public BookPage(ChromeOptions options)
    {
        this.options = options;
    }
}
