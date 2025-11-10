package org.trello.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BoardPage {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final By cardInListLocator = By.xpath(".//li[@data-testid='list-card']");
    private final By cardLocator = By.xpath("//li[@data-testid='list-card']");
    private final By listLocator = By.xpath("//div[@data-testid='list']");
    private final SelenideElement boardTitle = $(By.xpath("//h1[@data-testid='board-name-display']"));

    @Step("Get list by name")
    public SelenideElement getListByName(String listName) {
        logger.info("Get list by name: {}", listName);
        return $$(listLocator).findBy(text(listName));
    }

    @Step("Get card")
    public SelenideElement getCardByName(String  cardName) {
        logger.info("Get card by name: {}", cardName);
        return $$(cardLocator).findBy(text(cardName));
    }

    @Step("Get cardin list")
    public SelenideElement getCardinList(String listName, String cardName) {
        logger.info("Get card: {} in list: {}", cardName, listName);
        return $$(listLocator).findBy(text(listName)).$$(cardInListLocator).findBy(text(cardName));
    }

    @Step("Is card in list")
    public Boolean isCardInList(String listName,  String cardName) {
        boardTitle.shouldBe(Condition.visible);
        return getCardinList(listName, cardName).exists();
    }

    @Step("Open card")
    public CardPage openCard(String cardName) {
        logger.info("Open card: {}", cardName);
        getCardByName(cardName).click();
        return new CardPage();
    }

    @Step("Drag and drop card")
    public void dragAndDropCard(String cardName, String listName) {
        SelenideElement card = getCardByName(cardName);
        SelenideElement list = getListByName(listName);

        logger.info("Drag card: {} in list: {}", cardName, listName);
        Actions actions = new Actions(getWebDriver());
        actions
                .clickAndHold(card)
                .moveToElement(list)
                .release()
                .perform();
    }
}