package com.willowtreeapps;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

/**
 *  Updated 9/14/18
 */
public class HomePage extends BasePage {


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void validateTitleIsPresent() {
        Assert.assertEquals("name game", driver.getTitle());
    }


    public void validateClickingFirstPhotoIncreasesTriesCounter() throws IOException {
        // wait for page load
        sleep(3000);

        int attempt = Integer.parseInt(driver.findElement(By.className("attempts")).getText());

        driver.findElement(By.className("photo")).click();

        sleep(3000);

        int attemptAfter = Integer.parseInt(driver.findElement(By.className("attempts")).getText());

        Assert.assertTrue(attemptAfter > attempt);
        takeScreenShot("validateTriesCounter");

    }

    public void validateClickingCorrectPhotoIncreasesStreakCounter() throws IOException {
        // wait for page load
        sleep(3000);

        takeScreenShot("validateStreakCounter");

    }
}
