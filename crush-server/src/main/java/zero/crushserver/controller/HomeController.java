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

    /*
    @GetMapping("/")
    public String home() {
        return "home";
    }
     */

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

        //사전설정 메시지
        //3.5-chatting API의 system_role과 유사하게 프롬프트를 구현하였다.
        String system_role = "You are a competent fashion stylist. Look at a given set of clothes and their conditions and recommend suitable combinations in Korean. It must be appropriate for the given options."
                + "Follow the output form unconditionally: {[cloth1,cloth2,describe Why we recommend it],[ cloth1, cloth2, describe Why we recommend it], ...}. Up to 3 combinations.";

        //새로운 메시지 만들기
        String combined_message = system_role + " user_input: "+ clientMessage.getContent();

        ChatGptMessage combinedChatGptMessage = new ChatGptMessage();
        combinedChatGptMessage.setRole("user");
        combinedChatGptMessage.setContent(combined_message);

        List<ChatGptMessage> chatGptMessages = List.of(combinedChatGptMessage);

        //OpenAPI에 요청을 보내기
        ChatGptRequest chatGptRequest = ChatGptRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(chatGptMessages)
                .maxTokens(500)
                .temperature(0.5)
                .topP(0.9)
                .build();

        //OpenAPI의 응답을 받아서 클라이언트에게 전달하기
        ChatGptResponse chatGptResponse = chatService.getResponse(chatService.buildHttpEntity(chatGptRequest));

        return chatGptResponse;
    }
}
