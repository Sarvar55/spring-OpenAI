package com.chat.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @project: chatGPT
 * @author: Sarvar55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatGptRequest {
    private String model;
    private String prompt;
    private Double temperature;
    @JsonProperty("max_tokens")
    private Integer maxTokens;
    @JsonProperty("top_p")
    private Double topP;
}
