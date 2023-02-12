package com.chat.service;

import com.chat.config.ConstantForChatGpt;
import com.chat.model.request.ChatGptRequest;
import com.chat.model.request.ClientRequest;
import com.chat.model.response.ChatGptResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @project: chatGPT
 * @author: Sarvar55
 */
@Service
public class RestClientService {


    private final RestTemplate restTemplate;

    public RestClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public HttpEntity<ChatGptRequest> buildHttpHeaders(ChatGptRequest chatGptRequest) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(MediaType.parseMediaTypes((ConstantForChatGpt.MEDIA_TYPE)));
        httpHeaders.set(ConstantForChatGpt.AUTHORIZATION, ConstantForChatGpt.BEARER + ConstantForChatGpt.API_KEY);
        return new HttpEntity<ChatGptRequest>(chatGptRequest, httpHeaders);
    }

    public ChatGptResponse response(HttpEntity<ChatGptRequest> gptRequestHttpEntity) {
        ResponseEntity<ChatGptResponse> response = restTemplate.postForEntity(ConstantForChatGpt.URL,
                gptRequestHttpEntity, ChatGptResponse.class);
        return response.getBody();
    }

    public ChatGptResponse askQuestion(ClientRequest clientRequest) {
        ChatGptRequest chatGptRequest = clientRequestConvertToChatGptRequest(clientRequest);
        HttpEntity<ChatGptRequest> httpEntity = buildHttpHeaders(chatGptRequest);
        return response(httpEntity);
    }

    private ChatGptRequest clientRequestConvertToChatGptRequest(ClientRequest clientRequest) {
        return new ChatGptRequest(
                ConstantForChatGpt.MODEL,
                clientRequest.getMessage(),
                ConstantForChatGpt.TEMPERATURE,
                ConstantForChatGpt.MAX_TOKEN,
                ConstantForChatGpt.TOP_P
        );
    }

}
