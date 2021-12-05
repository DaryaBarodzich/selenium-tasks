package BringItOn.Page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class SecondMainPage {
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
    @FindBy(xpath = "//*[@id='select2-postform-format-container']")
    private WebElement PasteHighlight;
    @FindBy(xpath = "//ul[contains(@class,'select2-results')]/li")
    private List<WebElement> dropdownHighlightOptions;

    public SecondMainPage(WebDriver driver){
        this.driver = driver;
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(10))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .withMessage("Timeout exceeded!");
        PageFactory.initElements(driver,this);
    }
    public SecondMainPage secondPastePage() {
        driver.get(PAGE_URL);
        return this;
    }
    public SecondPasteCreationResult secondPasteCreation(String term){
        textInput.sendKeys(term);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'})", PasteHighlight);
        PasteHighlight.click();
        dropdownHighlightOptions.stream().filter(e->e.getText().equals("Bash")).findFirst().get().click();
        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'})", PasteExpiration);
        PasteExpiration.click();
        dropdownOptions.stream().filter(e->e.getText().equals("10 Minutes")).findFirst().get().click();
        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'})", PasteName);
        PasteName.sendKeys("how to gain dominance among developers");
        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'})", submitButton);
        submitButton.click();
        return new SecondPasteCreationResult(driver, term);
    }
}