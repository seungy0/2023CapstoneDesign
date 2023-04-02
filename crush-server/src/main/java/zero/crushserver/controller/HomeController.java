package zero.crushserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zero.crushserver.domain.ChatGptMessage;
import zero.crushserver.domain.ChatGptRequest;
import zero.crushserver.domain.ChatGptResponse;
import zero.crushserver.service.ChatService;

import java.util.List;

@Controller
public class HomeController {
    private final ChatService chatService;
    private final ChatGptMessage chatGptMessage;

    @Autowired
    HomeController(ChatService chatService, ChatGptMessage chatGptMessage) {
        this.chatService = chatService;
        this.chatGptMessage = chatGptMessage;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    /**
     * Chat with GPT-3
     * @param chatGptMessages List of ChatGptMessage
     * @return ChatGptResponse
     */
    @PostMapping("/chat")
    @ResponseBody
    public ChatGptResponse chat(@RequestBody List<ChatGptMessage> chatGptMessages) {
        ChatGptRequest chatGptRequest = ChatGptRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(chatGptMessages)
                .maxTokens(200)
                .temperature(0.5)
                .topP(1.0)
                .build();
        return chatService.getResponse(chatService.buildHttpEntity(chatGptRequest));
    }

    /**
     * Process client message and return OpenAI response
     * @param clientMessage ChatGptMessage from the client
     * @return ChatGptResponse
     */
    @PostMapping("/recommend")
    @ResponseBody
    public ChatGptResponse recommend(@RequestBody ChatGptMessage clientMessage) {

        String combinedMessage = chatService.getSystemRoleMessage() + " user_input: "+ clientMessage.getContent();

        chatGptMessage.setRole("user");
        chatGptMessage.setContent(combinedMessage);

        List<ChatGptMessage> chatGptMessages = List.of(chatGptMessage);

        ChatGptRequest chatGptRequest = ChatGptRequest.builder() //OpenAPI에 요청을 보내기
                .model("gpt-3.5-turbo")
                .messages(chatGptMessages)
                .maxTokens(500)
                .temperature(0.5)
                .topP(0.9)
                .build();

        ChatGptResponse chatGptResponse = chatService.getResponse(chatService.buildHttpEntity(chatGptRequest));
        return chatGptResponse; //OpenAPI의 응답을 받아서 클라이언트에게 전달하기
    }
}
