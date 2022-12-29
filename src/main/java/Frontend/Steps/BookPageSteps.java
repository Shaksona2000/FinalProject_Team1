package Frontend.Steps;

import Frontend.Pages.BookPage;
import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class BookPageSteps {
    ChromeOptions options;
    public BookPageSteps(ChromeOptions options)
    {
        this.options = options;
    }
    BookPage bookPage = new BookPage(options);

    @Step("Navigate To Book Store")
    public BookPageSteps navigateToBookStore()
    {
        $(bookPage.backToBookStore).scrollTo().click();
        return this;
    }

    @Step("Add Book To Collection")
    public BookPageSteps addBookToCollection()
    {
        $(bookPage.addToYourCollection).scrollTo().click(ClickOptions.usingJavaScript());
        return this;
    }

    @Step("Validate Popup")
    public BookPageSteps validatePopup()
    {
        Assert.assertEquals(switchTo().alert().getText(),"Book added to your collection.");
        switchTo().alert().accept();
        return this;
    }

    @Step
    public BookPageSteps goBackToProfile(){
        bookPage.profile.click();
        return this;
    }


}
