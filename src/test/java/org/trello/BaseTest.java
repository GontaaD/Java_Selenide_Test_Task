package org.trello;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import static com.codeborne.selenide.Selenide.*;

@Listeners({
        TestListener.class
})
public abstract class BaseTest {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeforeClass(alwaysRun = true)
    public void setupAllureListener() {
        if (SelenideLogger.hasListener("AllureSelenide")) {
            SelenideLogger.removeListener("AllureSelenide");
        }

        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
        );
    }

    @BeforeMethod(alwaysRun = true)
    public void startBrowser(){
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 6000;

        open(System.getProperty("url"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        closeWebDriver();
    }
}