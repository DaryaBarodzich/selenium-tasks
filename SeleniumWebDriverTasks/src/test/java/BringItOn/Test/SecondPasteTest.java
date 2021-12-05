package BringItOn.Test;

import BringItOn.Page.SecondMainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SecondPasteTest {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
//        driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
    }
    @Test (description = "Second test")
    public void SecondPaste() {
        int searchPasteResultsNumber = new SecondMainPage(driver)
                .secondPastePage()
                .secondPasteCreation("git config --global user.name  \"New Sheriff in Town\"\n" +
                        "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
                        "git push origin master --force")
                .countSecondPasteSearchResultsWithTerm();
        Assert.assertTrue(searchPasteResultsNumber > 1, "Paste was not created!");
    }
    @AfterMethod(alwaysRun = true)
        public void browserTearDown(){
            driver.quit();
            driver=null;
        }
    }
