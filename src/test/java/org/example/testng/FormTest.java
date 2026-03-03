package org.example.testng;

import org.example.pom.FormPom;
import org.example.utils.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class FormTest {

    static public WebDriver driver;
    static public String URL = "https://demoqa.com/";
    static public String FIRST_NAME = "Liza";
    static public String LAST_NAME = "Lepilova";
    static public String EMAIL = "test@gmail.com";
    static public String GENDER = "Female";
    static public String MOBILE = "1234567890";
    static public String DATE = "03 March,2026";
    static public String SUBJECT = "Maths";
    static public String HOBBY = "Music";
    static public String ADDRESS = "Chisinau";
    static public String STATE = "NCR";
    static public String CITY = "Delhi";

    @BeforeMethod
    public void beforeMethod() throws MalformedURLException {
        driver = Driver.getRemoteDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void formTest() {
        driver.get(URL);
        FormPom formPom = new FormPom(driver);

        formPom.clickForms();
        formPom.pause(1000);
        formPom.clickPracticeForm();
        formPom.closeAdvert();
        formPom.setFirstName(FIRST_NAME);
        formPom.setLastName(LAST_NAME);
        formPom.setEmail(EMAIL);
        formPom.setGender(GENDER);
        formPom.setMobile(MOBILE);
        formPom.setDate(DATE);
        formPom.setSubject(SUBJECT);
        formPom.setHobby(HOBBY);
        formPom.setAddress(ADDRESS);
        formPom.setState(STATE);
        formPom.setCity(CITY);

        formPom.clickSubmit();

        Assert.assertEquals(formPom.getTableDataByLabel("Student Name"),
                FIRST_NAME + " " + LAST_NAME);

        Assert.assertEquals(formPom.getTableDataByLabel("Student Email"),
                EMAIL);

        Assert.assertEquals(formPom.getTableDataByLabel("Gender"),
                GENDER);

        Assert.assertEquals(formPom.getTableDataByLabel("Mobile"),
                MOBILE);

        Assert.assertEquals(formPom.getTableDataByLabel("Date of Birth"),
                DATE);

        Assert.assertEquals(formPom.getTableDataByLabel("Subjects"),
                SUBJECT);

        Assert.assertEquals(formPom.getTableDataByLabel("Hobbies"),
                HOBBY);

        Assert.assertEquals(formPom.getTableDataByLabel("Address"),
                ADDRESS);

        Assert.assertEquals(formPom.getTableDataByLabel("State and City"),
                STATE + " " + CITY);
    }

    @AfterMethod
    public void afterMethod() {
        if (driver == null) {
            return;
        }

        String sessionId = null;
        if (driver instanceof RemoteWebDriver) {
            sessionId = ((RemoteWebDriver) driver).getSessionId().toString();
        }

        driver.quit();

        if (sessionId != null) {
            String remoteUrl = System.getProperty(
                    "remote.url",
                    System.getenv().getOrDefault("REMOTE_URL", "http://localhost:4444/wd/hub")
            );
            String videoBaseUrl = remoteUrl.replace("/wd/hub", "");
            System.out.println("VIDEO: " + videoBaseUrl + "/video/" + sessionId + ".mp4");
        }
    }
}
