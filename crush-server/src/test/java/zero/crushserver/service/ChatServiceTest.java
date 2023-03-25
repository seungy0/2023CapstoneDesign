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

    @Test
    void buildHttpEntity() {
    }

    @Autowired
    private ChatService chatService;

    @Test
    void getResponse() throws JsonProcessingException {
        ChatGptRequest chatGptRequest = new ChatGptRequest();
        chatGptRequest.setModel("gpt-3.5-turbo");
        ChatGptMessage chatGptMessage = new ChatGptMessage();
        chatGptMessage.setRole("user");
        chatGptMessage.setContent("hi, how are you?");
        List<ChatGptMessage> list = List.of(chatGptMessage);
        chatGptRequest.setMessages(list);
        chatGptRequest.setMaxTokens(10);
        chatGptRequest.setTemperature(0.5);
        chatGptRequest.setTopP(1.0);
        //ChatService chatService = new ChatService();
        HttpEntity<ChatGptRequest> chatGptRequestHttpEntity = chatService.buildHttpEntity(chatGptRequest);
        ChatGptResponse response = chatService.getResponse(chatGptRequestHttpEntity);
        System.out.println(response.getChoices().get(0).getMessage().getContent());
        //System.out.println(response.getChoices().get(0).getMessage().getContent());
    }
}