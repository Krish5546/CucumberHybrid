package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ElementUtils;

public class PimPage {
    
    WebDriver driver;
    ElementUtils elementUtils;
    
    public PimPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        elementUtils = new ElementUtils(driver);
    }
    
    // =========================
    // Navigation
    // =========================
    
    @FindBy(xpath = "//span[text()='PIM']")
    WebElement sideMenuPimButton;
    
    public void goToPimPage() {
        sideMenuPimButton.click();
        elementUtils.waitForVisibilityOfElement(
            By.xpath("//div[@class='oxd-table-body']"), 10);
    }
    
    // =========================
    // Search Section
    // =========================
    
    @FindBy(xpath = "//label[text()='Job Title']/following::div[contains(@class,'oxd-select-text-input')][1]")
    WebElement jobTitleDropDown;
    
    @FindBy(xpath = "//div[@role='listbox']//span")
    List<WebElement> jobtitlesList;
    
    @FindBy(xpath = "//button[@type='submit']")
    WebElement searchButton;
    
    public void searchEmployee(String jobtitle) {
        jobTitleDropDown.click();
        
        elementUtils.waitForVisibilityOfElement(
            By.xpath("//div[@role='listbox']"), 10);

        for (WebElement job : jobtitlesList) {
            if (job.getText().trim().equalsIgnoreCase(jobtitle)) {
                job.click();
                break;
            }
        }
        
        searchButton.click();

        // wait for results refresh
        elementUtils.waitForVisibilityOfElement(
            By.xpath("//div[@class='oxd-table-body']//div[@class='oxd-table-card']"), 10);
    }
    
    // =========================
    // Results Table (FIXED)
    // =========================
    
    @FindBy(xpath = "//div[@class='oxd-table-body']//div[@class='oxd-table-card']")
    List<WebElement> rows;
    
    public boolean searchResults(String jobtitle) {

        elementUtils.waitForVisibilityOfElement(
            By.xpath("//div[@class='oxd-table-body']"), 10);

        if (rows.size() == 0) {
            throw new RuntimeException("No records found");
        }

        for (WebElement row : rows) {

            // 🔥 Directly target Job Title column (stable)
            WebElement jobCell = row.findElement(
                By.xpath(".//div[@role='cell'][5]//div"));

            String actualJob = jobCell.getText().trim();

            System.out.println("Expected: " + jobtitle + " | Actual: " + actualJob);

            if (!actualJob.equalsIgnoreCase(jobtitle)) {
                return false;
            }
        }

        return true;
    }
}