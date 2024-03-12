package cn.why41bg.chatgpt.domain.whisper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @Classname TranslationsRequest
 * @Description 语音翻译请求对象
 * @Author 魏弘宇
 * @Date 2024/3/12 11:09
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Getter
@Setter
public class TranslationsRequest implements Serializable {

    @Builder.Default
    private String model = WhisperEnum.Model.WHISPER_1.getCode();

    private String prompt;

    @JsonProperty("response_format")
    @Builder.Default
    private String responseFormat = WhisperEnum.ResponseFormat.JSON.getCode();

    private double temperature = 0.2;

}
