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

    @Autowired
    HomeController(ChatService chatService) {
        this.chatService = chatService;
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
        ChatGptRequest chatGptRequest = new ChatGptRequest();
        chatGptRequest.setModel("gpt-3.5-turbo");
        chatGptRequest.setMessages(chatGptMessages);
        chatGptRequest.setMaxTokens(200);
        chatGptRequest.setTemperature(0.5);
        chatGptRequest.setTopP(1.0);
        return chatService.getResponse(chatService.buildHttpEntity(chatGptRequest));
    }
}
