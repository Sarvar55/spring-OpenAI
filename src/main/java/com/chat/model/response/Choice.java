package com.chat.model.response;

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
public class Choice {
    private String text;
    private int index;
    @JsonProperty("finish_reason")
    private String finishReason;
}
