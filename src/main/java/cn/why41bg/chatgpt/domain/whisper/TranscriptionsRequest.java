package cn.why41bg.chatgpt.domain.whisper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;

/**
 * @Classname TranscriptionsRequest
 * @Description 语音转文本请求对象
 * @Author 魏弘宇
 * @Date 2024/3/12 11:06
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Setter
@Getter
@FieldNameConstants
public class TranscriptionsRequest implements Serializable {

    /**
     * 语音转文本指定的模型；WHISPER_1
     */
    @Builder.Default
    private String model = WhisperEnum.Model.WHISPER_1.getCode();

    private String prompt;

    /**
     * 返回格式
     */
    @JsonProperty("response_format")
    @Builder.Default
    private String responseFormat = WhisperEnum.ResponseFormat.JSON.getCode();

    private double temperature = 0.2;

    /**
     * 音频语言；ISO-639-1
     */
    private String language;

}
