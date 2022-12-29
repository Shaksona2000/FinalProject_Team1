package Frontend.Steps;

import Frontend.Pages.BookStorePage;
import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$;

public class BookStorePageSteps {
    ChromeOptions options;
    public BookStorePageSteps(ChromeOptions options)
    {
        this.options = options;
    }
    BookStorePage bookStorePage = new BookStorePage(options);

    public List<String> bookTitles =new ArrayList<>();

    @Step("Navigate To Book")
    public BookStorePageSteps navigateToBook(Integer index)
    {
        SelenideElement book = $$(bookStorePage.Books).get(index);
        bookTitles.add(book.getText());
        book.click(ClickOptions.usingJavaScript());
        return this;
    }
}
