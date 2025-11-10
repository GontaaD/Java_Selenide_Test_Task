package org.trello.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SelenideElement loginButton = $(By.xpath("//a[text()='Увійти']"));
    private final SelenideElement emailField = $(By.xpath("//input[@id='username-uid1']"));
    private final SelenideElement passwordField = $(By.xpath("//input[@id='password']"));
    private final SelenideElement submitButton = $(By.xpath("//button[@id='login-submit']"));
    private final SelenideElement totpInput = $(By.xpath("//input[@id='two-step-verification-otp-code-input']"));

    @Step("Login button click")
    public LoginPage loginButtonClick() {
        logger.info("Login button click");
        loginButton.click();
        return this;
    }

    @Step("Input email")
    public LoginPage inputEmail(String email) {
        logger.info("Input email");
        emailField.setValue(email);
        return this;
    }

    @Step("Input password")
    public LoginPage inputPassword(String password) {
        logger.info("Input password");
        passwordField.setValue(password);
        return this;
    }

    @Step("Submit button click")
    public LoginPage submitButtonClick() {
        logger.info("Submit button click");
        submitButton.click();
        return this;
    }

    @Step("Input TOTP")
    public BoardPage inputTotp(String totp) {
        logger.info("Input TOTP");
        totpInput.setValue(totp);
        return new BoardPage();
    }
}
