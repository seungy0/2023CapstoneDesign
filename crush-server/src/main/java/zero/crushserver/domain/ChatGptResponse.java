package zero.crushserver.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public class ChatGptResponse {
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
        public Integer getCompletionTokens() {
            return completionTokens;
        }
        public Integer getTotalTokens() {
            return totalTokens;
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
