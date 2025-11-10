package org.trello.api.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Board {
    private String id;
    private String name;
    private String url;

    public static String generateBoardName() {
        String uuid = UUID.randomUUID().toString().substring(0, 4);
        return uuid + "_Task_Board";
    }
}

