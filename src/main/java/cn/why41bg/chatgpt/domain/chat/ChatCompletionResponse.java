package cn.why41bg.chatgpt.domain.chat;

import cn.why41bg.chatgpt.domain.other.Usage;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Classname ChatCompletionResponse
 * @Description TODO 类描述
 * @Author 魏弘宇
 * @Date 2024/3/11 14:51
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatCompletionResponse implements Serializable {
    private String id;
    private String object;
    private String model;
    private List<ChatChoice> choices;
    private long created;
    private Usage usage;
    @JsonProperty("system_fingerprint")
    private String systemFingerprint;
}
