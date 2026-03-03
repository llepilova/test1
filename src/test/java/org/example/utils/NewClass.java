package org.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NewClass {

    public static <T> T explicitWait(WebDriver driver, ExpectedCondition<T> condition, long seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        return wait.until(condition);
    }
}