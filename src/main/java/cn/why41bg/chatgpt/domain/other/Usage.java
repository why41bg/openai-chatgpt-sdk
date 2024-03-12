package cn.why41bg.chatgpt.domain.other;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Classname Usage
 * @Description TODO 类描述
 * @Author 魏弘宇
 * @Date 2024/3/11 17:35
 */
@Setter
@Getter
public class Usage implements Serializable {

    @JsonProperty("prompt_tokens")
    private long promptTokens;

    @JsonProperty("completion_tokens")
    private long completionTokens;

    @JsonProperty("total_tokens")
    private long totalTokens;

}
