package org.trello.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;

public class CardPage {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SelenideElement cardNameLocator = $(By.xpath("//textarea[@data-testid='card-back-title-input']"));
    private final SelenideElement cardDescriptionLocator = $(By.xpath("//div[@data-testid='description-content-area']//p"));
    private final SelenideElement closeButton = $(By.xpath("//div[@role='presentation']//span[@data-testid='CloseIcon']"));

    @Step("Get card name")
    public String getCardName() {
        logger.info("Get card name");
        return cardNameLocator.getText();
    }

    @Step("Get card description")
    public String getCardDescription() {
        logger.info("Get card description");
        return cardDescriptionLocator.getText();
    }

    @Step("Close button click")
    public void closeButtonClick() {
        logger.info("Close button click");
        closeButton.click();
    }
}