package com.chat.controller;

import com.chat.model.request.ClientRequest;
import com.chat.model.response.ChatGptResponse;
import com.chat.service.RestClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @project: chatGPT
 * @author: Sarvar55
 */
@RestController
@RequestMapping("/api/1.0.0/askToChat")
@Slf4j
public class RestClientController {

    private final RestClientService restClientService;

    public RestClientController(RestClientService restClientService) {
        this.restClientService = restClientService;
    }

    @PostMapping
    public ChatGptResponse askQuestion(@RequestBody ClientRequest clientRequest) {
        log.info("İstek buraya ulastı:{}", clientRequest.getMessage());
        return restClientService.askQuestion(clientRequest);
    }

}
