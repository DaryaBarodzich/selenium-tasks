package ICanWinTask.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class PasteCreationResults {
    private WebDriver driver;
    private String searchTerm;
    @FindBy(xpath = "//div[contains(@class,'de1') and contains(.,'Hello from WebDriver')]")
    private List<WebElement> generalPasteResults;

    public PasteCreationResults(WebDriver driver, String searchTerm) {
        this.searchTerm = searchTerm;
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int countPasteSearchResultsWithTerm() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'de1') and contains(.,'Hello from WebDriver')]")));
        System.out.println("Paste creation results number:" + generalPasteResults.size());
        return generalPasteResults.size();
    }
}