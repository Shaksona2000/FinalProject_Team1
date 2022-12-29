package Frontend.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;

public class BookStorePage {
    public By Books = By.cssSelector(".mr-2 > a");
    ChromeOptions options;
    public BookStorePage(ChromeOptions options)
    {
        this.options = options;
    }
}
