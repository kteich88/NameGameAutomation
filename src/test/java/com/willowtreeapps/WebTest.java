package com.willowtreeapps;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;

public class WebTest {

    private WebDriver driver;

    /**
     * Change the prop if you are on Windows or Linux to the corresponding file type
     * The chrome WebDrivers are included on the root of this project, to get the
     * latest versions go to https://sites.google.com/a/chromium.org/chromedriver/downloads
     */
    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.mac");
        Capabilities capabilities = DesiredCapabilities.chrome();
        driver = new ChromeDriver(capabilities);
        driver.navigate().to("http://www.ericrochester.com/name-game/");
    }

    @Test
    public void test_validate_title_is_present() {
        new HomePage(driver)
                .validateTitleIsPresent();
    }

    @Test
    public void test_clicking_photo_increases_tries_counter() throws IOException {
        new HomePage(driver)
                .validateClickingFirstPhotoIncreasesTriesCounter();
    }

    @Test
    public void test_clicking_correct_photo_increases_streak_counter() throws IOException {
        new HomePage(driver)
                .validateClickingCorrectPhotoIncreasesStreakCounter();
    }

    @Test
    public void test_clicking_incorrect_photo_resets_streak_counter() throws IOException {
        new HomePage(driver)
                .validateClickingIncorrectPhotoResetsStreakCounter();
    }

    @Test
    public void test_clicking_10_photos_increases_counters_correctly() throws IOException {
        new HomePage(driver)
                .validateClicking10PhotosIncreasesCountersCorrectly();
    }

    @Test
    public void test_clicking_correct_photo_changes_photos_and_names() throws IOException {
        new HomePage(driver)
                .validateClickingCorrectPhotoChangesPhotoAndNames();
    }

    @After
    public void teardown() {
        driver.quit();
        System.clearProperty("webdriver.chrome.driver");
    }

}
