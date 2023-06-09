package zero.crushserver.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import zero.crushserver.domain.ChatGptRequest;
import zero.crushserver.domain.ChatGptResponse;

@Service
public class ChatService {
    @Value("${chatgpt.api.key}")
    private String apiKey;

    private static final RestTemplate restTemplate = new RestTemplate();
    public HttpEntity<ChatGptRequest> buildHttpEntity(ChatGptRequest chatRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization","Bearer " + apiKey);
        return new HttpEntity<>(chatRequest,headers);
    }

    public ChatGptResponse getResponse(HttpEntity<ChatGptRequest> chatRequestHttpEntity) {
        ResponseEntity<ChatGptResponse> responseEntity = restTemplate.postForEntity(
                "https://api.openai.com/v1/chat/completions",
                chatRequestHttpEntity,
                ChatGptResponse.class
        );
        return responseEntity.getBody();
    }
}
