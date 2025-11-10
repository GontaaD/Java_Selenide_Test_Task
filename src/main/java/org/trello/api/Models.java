package org.trello.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.trello.SecretsManager;

public class Models {
    public static RequestSpecification requestSpec(){
        return new RequestSpecBuilder()
                .setBaseUri(SecretsManager.get("URI"))
                .setContentType(ContentType.JSON)
                .addQueryParam("key", SecretsManager.get("API_KEY"))
                .addQueryParam("token", SecretsManager.get("API_TOKEN"))
                .build();
    }
}
