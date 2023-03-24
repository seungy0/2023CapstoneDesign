package zero.crushserver.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public class ChatGptResponse {
    // {
    //   "id":"chatcmpl-abc123",
    //   "object":"chat.completion",
    //   "created":1677858242,
    //   "model":"gpt-3.5-turbo-0301",
    //   "usage":{
    //      "prompt_tokens":13,
    //      "completion_tokens":7,
    //      "total_tokens":20
    //   },
    //   "choices":[
    //      {
    //         "message":{
    //            "role":"assistant",
    //            "content":"\n\nThis is a test!"
    //         },
    //         "finish_reason":"stop",
    //         "index":0
    //      }
    //   ]
    //}
    // 위의 json 데이터를 ChatGptResponse 클래스로 매핑해줘
    // 아래에 코드를 작성해주세요.
    private String id;
    private String object;
    private LocalDate created;
    private String model;
    private Usage usage;
    private List<Choice> choices;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getObject() {
        return object;
    }
    public void setObject(String object) {
        this.object = object;
    }
    public LocalDate getCreated() {
        return created;
    }
    public void setCreated(LocalDate created) {
        this.created = created;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public Usage getUsage() {
        return usage;
    }
    public void setUsage(Usage usage) {
        this.usage = usage;
    }
    public List<Choice> getChoices() {
        return choices;
    }
    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
    public static class Usage {
        @JsonProperty("prompt_tokens")
        private Integer promptTokens;
        @JsonProperty("completion_tokens")
        private Integer completionTokens;
        @JsonProperty("total_tokens")
        private Integer totalTokens;

        public Integer getPromptTokens() {
            return promptTokens;
        }
        public void setPromptTokens(Integer promptTokens) {
            this.promptTokens = promptTokens;
        }
        public Integer getCompletionTokens() {
            return completionTokens;
        }
        public void setCompletionTokens(Integer completionTokens) {
            this.completionTokens = completionTokens;
        }
        public Integer getTotalTokens() {
            return totalTokens;
        }
        public void setTotalTokens(Integer totalTokens) {
            this.totalTokens = totalTokens;
        }
    }
    public static class Choice {
        private ChatGptMessage message;
        @JsonProperty("finish_reason")
        private String finishReason;
        private Integer index;

        public ChatGptMessage getMessage() {
            return message;
        }
        public void setMessage(ChatGptMessage message) {
            this.message = message;
        }
        public String getFinishReason() {
            return finishReason;
        }
        public void setFinishReason(String finishReason) {
            this.finishReason = finishReason;
        }
        public Integer getIndex() {
            return index;
        }
        public void setIndex(Integer index) {
            this.index = index;
        }
    }
}
