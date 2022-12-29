package Frontend.Steps;

import Frontend.Pages.ProfilePage;
import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.CollectionCondition;
import io.qameta.allure.Step;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class ProfilePageSteps {
    ChromeOptions options;
    public ProfilePageSteps(ChromeOptions options)
    {
        this.options = options;
    }
    ProfilePage profilePage = new ProfilePage(options);

    @Step("Navigate To BookStore")
    public ProfilePageSteps navigateToBookstore()
    {
        $(profilePage.navigateToBookstore).scrollTo().click();
        return this;
    }

    @Step
    public ProfilePageSteps validateBookTitle(Integer index, String customText){
        profilePage.books.get(index).shouldHave(text(customText));
        return this;
    }
    @Step
    public ProfilePageSteps deleteBook(){
        profilePage.deleteButtons.first().click(ClickOptions.usingJavaScript());
        return this;
    }

    @Step
    public ProfilePageSteps bookDeletionFinalization()
    {
        profilePage.deletionConfirmation.click(ClickOptions.usingJavaScript());
        return this;
    }

    @Step
    public ProfilePageSteps bookDeletionPopup()
    {
        switchTo().alert().accept();
        return this;
    }
    @Step
    public ProfilePageSteps validateCollectionSize(Integer size){
        profilePage.books.shouldHave(CollectionCondition.size(size));
        return this;
    }


}
