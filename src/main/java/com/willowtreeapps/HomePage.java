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

    public void validateClickingIncorrectPhotoResetsStreakCounter() throws IOException {
        // wait for page load
        sleep(3000);

        // start streak
        List<WebElement> names = driver.findElements(By.className("name"));
        String name = driver.findElement(By.id("name")).getText();

        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).getText().equals(name)) {
                driver.findElements(By.className("photo")).get(i).click();
            }
        }

        // wait for the page to reload
        sleep(5000);

        // assign variable streak to integer value in element w/className "streak" text
        int streak = Integer.parseInt(driver.findElement(By.className("streak")).getText());

        // recall variables to prevent stale element exception
        names = driver.findElements(By.className("name"));
        name = driver.findElement(By.id("name")).getText();

        // if the first photo name doesn't match the name queried, click it; otherwise, click the next photo
        if (names.get(0).getText().equals(name)) {

            driver.findElements(By.className("photo")).get(1).click();

        } else {

            driver.findElements(By.className("photo")).get(0).click();
        }

        // wait element update
        sleep(3000);

        // assign variable streak to integer value in element w/className "streak" text
        int newStreak = Integer.parseInt(driver.findElement(By.className("streak")).getText());

        // assert variable newStreak is less than streak; clicking incorrect photo decreases streak counter
        Assert.assertTrue(newStreak < streak);
        takeScreenShot("validateStreakCounterReset");
    }

    public void validateClicking10PhotosIncreasesCountersCorrectly() throws IOException {
        // wait for page load
        sleep(3000);

        // initialize variables both "correct" and "attempts" count start at zerp
        int correctCount = 0;
        int attemptsCount = 0;

        // Loop through below actions while variable attemptsCount is less than 10; click through 10 attempts
        while (attemptsCount < 10) {

            // create a list of photos associated w/className "photo"
            List<WebElement> photos = driver.findElements(By.className("photo"));

            // loop through all photos
            for (int i = 0; i < photos.size() && attemptsCount < 10; i++) {
                String name = driver.findElement(By.id("name")).getText();

                // click on the next photo in the loop
                driver.findElements(By.className("photo")).get(i).click();

                // if the name matches the photo, increment both counters, exit loop
                if (driver.findElements(By.className("name")).get(i).getText().equals(name)) {

                    // add to existing count number; increment each counter by one
                    attemptsCount++;
                    correctCount++;

                    break;

                    // otherwise the photo and name do not match, only increment the attempts counter
                } else {
                    attemptsCount++;
                }

            }

            // wait for page reload
            sleep(5000);

        }

        // assign variables for counts after 10 attempts
        int attemptsCountAfter = Integer.parseInt(driver.findElement(By.className("attempts")).getText());
        int correctCountAfter = Integer.parseInt(driver.findElement(By.className("correct")).getText());

        // assert variables are equal; counters increment correctly
        Assert.assertTrue(attemptsCount == attemptsCountAfter);
        Assert.assertTrue(correctCount == correctCountAfter);
        takeScreenShot("validateCountersIncreaseAfter10Attempts");
    }


}
