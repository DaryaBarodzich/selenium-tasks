package ICanWinTask.Test;

import ICanWinTask.Page.MainPastePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class PasteTest {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
//        driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
    }
    @Test (description = "First test")
    public void NewPaste(){
        int searchResultsNumber = new MainPastePage(driver)
                .newPastePage()
                .newPasteCreation("Hello from WebDriver")
                .countPasteSearchResultsWithTerm();
        Assert.assertTrue(searchResultsNumber >0, "Paste was not created!");

        }
    @AfterMethod(alwaysRun = true)
    public void browserTearDown(){
        driver.quit();
        driver=null;
    }
}
