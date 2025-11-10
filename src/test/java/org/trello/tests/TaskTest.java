package org.trello.tests;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.trello.BaseTest;
import org.trello.SecretsManager;
import org.trello.TOTPGenerator;
import org.trello.api.Clients;
import org.trello.api.POJO.Board;
import org.trello.api.POJO.List;
import org.trello.ui.pages.BoardPage;
import org.trello.ui.pages.CardPage;
import org.trello.ui.pages.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

public class TaskTest extends BaseTest {
    Clients clients = new Clients();
    String toDoListName = "To Do";
    String inProgressListName = "In Progress";
    String doneListName = "Done";
    String cardName = "New Task";
    String cardDescription = "This is a new task card";

    @BeforeClass(alwaysRun = true)
    public void preCondition() {
        Response responseBoard = clients.createBoard(Board.generateBoardName());
        logger.info("Create Board. Status Code: [{}]", responseBoard.getStatusCode());
        responseBoard.then().statusCode(200);
        Board board = responseBoard.as(Board.class);
        System.setProperty("url", board.getUrl());

        Response responseToDoList = clients.createList(board.getId(), toDoListName);
        logger.info("Create List. Status Code: [{}]", responseToDoList.getStatusCode());
        responseToDoList.then().statusCode(200);
        List toDoList = responseToDoList.as(List.class);

        Response responseInProgressList = clients.createList(board.getId(), inProgressListName);
        logger.info("Create List. Status Code: [{}]", responseInProgressList.getStatusCode());
        responseInProgressList.then().statusCode(200);

        Response responseDoneList = clients.createList(board.getId(), doneListName);
        logger.info("Create List. Status Code: [{}]", responseDoneList.getStatusCode());
        responseDoneList.then().statusCode(200);

        Response responseCard = clients.createCard(toDoList.getId(), cardName, cardDescription);
        logger.info("Create Card. Status Code: [{}]", responseCard.getStatusCode());
        responseCard.then().statusCode(200);
    }

    @Test
    public void taskTest(){
        LoginPage loginPage = new LoginPage();

        loginPage
                .loginButtonClick()
                .inputEmail(SecretsManager.get("EMAIL"))
                .submitButtonClick()
                .inputPassword(SecretsManager.get("PASSWORD"))
                .submitButtonClick();

        String totpCode = TOTPGenerator.generateTOTP(SecretsManager.get("SECRET"));

        BoardPage boardPage = loginPage
                .inputTotp(totpCode);

        assertThat(boardPage.isCardInList(toDoListName, cardName))
                .as("No cards found in the list")
                .isTrue();

        logger.info("Card {} present in list {}", cardName, toDoListName);

        CardPage cardPage = boardPage
                .openCard(cardName);

        assertThat(cardPage.getCardName())
                .as("Incorrect card name")
                .isEqualTo(cardName);

        logger.info("Correct card name");

        assertThat(cardPage.getCardDescription())
                .as("Incorrect card description")
                .isEqualTo(cardDescription);

        logger.info("Correct card description");

        cardPage.closeButtonClick();

        boardPage.dragAndDropCard(cardName, inProgressListName);

        assertThat(boardPage.isCardInList(inProgressListName, cardName))
                .as("No cards found in the list")
                .isTrue();

        logger.info("Card {} present in list {}", cardName, inProgressListName);
    }
}
