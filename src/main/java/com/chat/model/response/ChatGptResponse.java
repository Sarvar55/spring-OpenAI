package com.chat.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * @project: chatGPT
 * @author: Sarvar55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatGptResponse {

    private String id;
    private String object;
    private LocalDate created;
    private String model;
    private List<Choice> choices;

}
