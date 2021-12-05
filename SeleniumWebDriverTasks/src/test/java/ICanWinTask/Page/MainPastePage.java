package ICanWinTask.Page;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class MainPastePage {
    private static final String PAGE_URL = "https://pastebin.com";
    private WebDriver driver;
    @FindBy(xpath = "//*[@id='postform-text']")
    private WebElement textInput;
    @FindBy(xpath = "//*[@id='select2-postform-expiration-container']")
    private WebElement PasteExpiration;
    @FindBy(xpath = "//*[@id='select2-postform-expiration-result-loms-10M']")
    private WebElement TimeElement;
    @FindBy(xpath = "//*[@id='postform-name']")
    private WebElement PasteName;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;
    @FindBy(xpath = "//ul[contains(@class,'select2-results')]/li")
    private List<WebElement> dropdownOptions;

    public MainPastePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public MainPastePage newPastePage() {
        driver.get(PAGE_URL);
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(10))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .withMessage("Timeout exceeded!");
        return this;
    }
    public PasteCreationResults newPasteCreation(String term) {
        textInput.sendKeys(term);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'})", PasteExpiration);
        PasteExpiration.click();
        dropdownOptions.stream().filter(e->e.getText().equals("10 Minutes")).findFirst().get().click();
        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'})", PasteName);
        PasteName.sendKeys("helloweb");
        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'})", submitButton);
        submitButton.click();
        return new PasteCreationResults(driver, term);
    }
}