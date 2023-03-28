package zero.crushserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import zero.crushserver.domain.ChatGptMessage;
import zero.crushserver.domain.ChatGptRequest;
import zero.crushserver.domain.ChatGptResponse;

import java.util.List;

@SpringBootTest
class ChatServiceTest {

    @Autowired
    ChatService chatService;
    @Test
    void buildHttpEntity() {
    }

    @Test
    void getResponse() {
        ChatGptMessage chatGptMessage = new ChatGptMessage();
        chatGptMessage.setRole("user");
        chatGptMessage.setContent("Hello!");
        ChatGptRequest chatGptRequest = ChatGptRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(List.of(chatGptMessage))
                .maxTokens(10)
                .temperature(0.5)
                .topP(1.0)
                .build();
        HttpEntity<ChatGptRequest> chatGptRequestHttpEntity = chatService.buildHttpEntity(chatGptRequest);
        ChatGptResponse response = chatService.getResponse(chatGptRequestHttpEntity);
        System.out.println(response.getChoices().get(0).getMessage().getContent());
    }
}