package BringItOn.Page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class SecondPasteCreationResult {
    public final String splitRegex = "\\s";
    private WebDriver driver;
    private String searchTerm;
    private String defaultLocator = "//*[contains(@class,'bash')%s]";
    private String containsPart = " and contains(.,'%s')";
    @FindBy(xpath = "//*[text()='how to gain dominance among developers']")
    private List<WebElement> PasteTitle;
    @FindBy(xpath = "//*[contains(@class,'bash')]")
    private List<WebElement> PasteData;

    public SecondPasteCreationResult(WebDriver driver, String searchTerm) {
        this.searchTerm = searchTerm;
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public int countSecondPasteSearchResultsWithTerm(){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[contains(@class,'bash')]")));
        List<WebElement> searchResultsWithTerm = driver.findElements(By.xpath(buildLocatorForSearch()));
        System.out.println("Search results number for paste text and title:" + (searchResultsWithTerm.size() + PasteTitle.size()));
        return (searchResultsWithTerm.size() + PasteTitle.size());
    }
    private String buildLocatorForSearch(){
        String partWithSearchTerm = "";
        String[] terms = searchTerm.split(splitRegex);
        for (String term : terms) {
            partWithSearchTerm += String.format(containsPart, term);
        }
        String locatorForSearch = String.format(defaultLocator, partWithSearchTerm);
        System.out.println("DEBUG:Final locator with search term: " + locatorForSearch);
        return locatorForSearch;
    }
}