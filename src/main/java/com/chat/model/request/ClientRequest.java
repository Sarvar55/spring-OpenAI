package com.chat.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @project: chatGPT
 * @author: Sarvar55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequest {
    private String message;
}
