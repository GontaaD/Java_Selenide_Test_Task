package org.trello.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

public class Clients {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Step("Create board")
    public Response createBoard(String boardName) {
        logger.info("Create board. Board name: {}", boardName);
        return given()
                .spec(Models.requestSpec())
                .queryParam("name", boardName)
                .when()
                .post("/boards");
    }

    @Step("Create list")
    public Response createList(String boardId, String listName){
        logger.info("Create list. List name: {}", listName);
        return given()
                .spec(Models.requestSpec())
                .queryParam("name", listName)
                .queryParam("idBoard", boardId)
                .when()
                .post("/lists");
    }

    @Step("Create card")
    public Response createCard(String listId, String cardName, String description) {
        logger.info("Create card. Card name: {}, description {}", cardName, description);
        return given()
                .spec(Models.requestSpec())
                .queryParam("name", cardName)
                .queryParam("idList", listId)
                .queryParam("desc", description)
                .when()
                .post("/cards");
    }
}