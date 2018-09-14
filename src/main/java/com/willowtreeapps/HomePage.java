package com.willowtreeapps;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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


    public void validateClickingFirstPhotoIncreasesTriesCounter() {
        //Wait for page to load
        sleep(6000);

        int count = Integer.parseInt(driver.findElement(By.className("attempts")).getText());

        driver.findElement(By.className("photo")).click();

        sleep(6000);

        int countAfter = Integer.parseInt(driver.findElement(By.className("attempts")).getText());

        Assert.assertTrue(countAfter > count);

    }
}
