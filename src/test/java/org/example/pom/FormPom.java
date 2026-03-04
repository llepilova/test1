package org.example.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.Keys;
import org.example.utils.NewClass;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class FormPom {

    static public WebDriver driver;
    static public JavascriptExecutor js;

    @FindBy(xpath = "//*[text()='Forms']")
    WebElement forms;

    @FindBy(xpath = "//*[text()='Practice Form']")
    WebElement practiceForm;

    @FindBy(xpath = "//*[@id='firstName']")
    WebElement firstName;

    @FindBy(xpath = "//*[@id='lastName']")
    WebElement lastName;

    @FindBy(xpath = "//*[@id='userEmail']")
    WebElement userEmail;

    @FindBy(xpath = "//*[@id='userNumber']")
    WebElement mobile;

    @FindBy(xpath = "//*[@id='dateOfBirthInput']")
    WebElement dateOfBirth;

    @FindBy(xpath = "//*[@id='subjectsInput']")
    WebElement subjects;

    @FindBy(xpath = "//*[@id='currentAddress']")
    WebElement address;

    @FindBy(xpath = "//*[@id='state']")
    WebElement state;

    @FindBy(xpath = "//*[@id='city']")
    WebElement city;

    @FindBy(xpath = "//*[@id='submit']")
    WebElement submit;

    public FormPom(WebDriver driverParam) {
        driver = driverParam;
        js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public void setGender(String genderParam) {
        WebElement gender = driver.findElement(
                By.xpath("//*[@id='genterWrapper']//label[text()='" + genderParam + "']")
        );
        gender.click();
    }

    public void setEmail(String emailParam) {
        userEmail.clear();
        userEmail.sendKeys(emailParam);
    }

    public void setLastName(String lastNameParam) {
        lastName.clear();
        lastName.sendKeys(lastNameParam);
    }

    public void setFirstName(String firstNameParam) {
        firstName.clear();
        firstName.sendKeys(firstNameParam);
    }

    public void setMobile(String mobileParam) {
        mobile.clear();
        mobile.sendKeys(mobileParam);
    }


    public void setDate(String date) {
        js.executeScript("arguments[0].value = arguments[1];",
                dateOfBirth, date);
    }

    public void setSubject(String subject) {
        subjects.clear();
        subjects.sendKeys(subject);
        subjects.sendKeys(Keys.ENTER);
    }

    public void setHobby(String hobby) {
        WebElement hobbyEl = driver.findElement(
                By.xpath("//label[text()='" + hobby + "']")
        );
        hobbyEl.click();
    }

    public void setAddress(String addr) {
        address.clear();
        address.sendKeys(addr);
    }

    public void setState(String stateName) {
        state.click();
        WebElement option = driver.findElement(By.xpath("//*[text()='" + stateName + "']"));
        option.click();
    }

    public void setCity(String cityName) {
        city.click();
        WebElement option = driver.findElement(By.xpath("//*[text()='" + cityName + "']"));
        option.click();
    }

    public void clickSubmit() {
        closeAdvert();
        js.executeScript("arguments[0].scrollIntoView(true);", submit);
        js.executeScript("arguments[0].click();", submit);
    }

    public String getTableDataByLabel(String label) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement valueEl = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//td[text()='" + label + "']/following-sibling::td")
                )
        );

        return valueEl.getText();
    }

    public void clickPracticeForm() {
        practiceForm.click();
    }

    public void clickForms() {
        NewClass.explicitWait(driver,
                ExpectedConditions.visibilityOf(forms),
                10
        );

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", forms);
        js.executeScript("arguments[0].click();", forms);
    }

    public void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeAdvert() {
        try {
            js.executeScript(
                    "var elem = document.evaluate(\"//*[@id='adplus-anchor']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                            "elem.parentNode.removeChild(elem);"
            );
        } catch (Exception ignored) {}

        try {
            js.executeScript(
                    "var elem = document.evaluate(\"//footer\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                            "elem.parentNode.removeChild(elem);"
            );
        } catch (Exception ignored) {}
    }
}
