package cn.why41bg.chatgpt.domain.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Classname ChatChoice
 * @Description TODO 类描述
 * @Author 魏弘宇
 * @Date 2024/3/11 14:51
 */
@Data
public class ChatChoice implements Serializable {
    private long index;

    /**
     * stream = true
     */
    @JsonProperty("delta")
    private Message delta;

    /**
     * stream = false
     */
    @JsonProperty("message")
    private Message message;

    @JsonProperty("finish_reason")
    private String finishReason;

    private String logprobs;
}
