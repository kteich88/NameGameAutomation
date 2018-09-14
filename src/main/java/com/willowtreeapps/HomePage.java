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

        // assign variable streak to integer value in element w/className "streak" text
        int streak = Integer.parseInt(driver.findElement(By.className("streak")).getText());

        // assign variable name to string value in element w/id "name" text
        String name = driver.findElement(By.id("name")).getText();

        // create a list of names associated w/className "name"
        List<WebElement> names = driver.findElements(By.className("name"));

        // loop through all names in the list
        for (int i = 0; i < names.size(); i++) {

            // find the name from the list that matches name queried and click it
            if (names.get(i).getText().equals(name)) {

                driver.findElements(By.className("photo")).get(i).click();
            }
        }

        // wait element update
        sleep(3000);

        // assign variable streak to integer value in element w/className "streak" text
        int newStreak = Integer.parseInt(driver.findElement(By.className("streak")).getText());

        // assert variable newStreak is greater than streak; clicking correct photo increases streak counter
        Assert.assertTrue(newStreak > streak);
        takeScreenShot("validateStreakCounter");

    }
}
