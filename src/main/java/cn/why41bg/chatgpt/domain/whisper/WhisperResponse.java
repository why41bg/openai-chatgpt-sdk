package cn.why41bg.chatgpt.domain.whisper;

import lombok.Data;

import java.io.Serializable;

/**
 * @Classname TranscriptionsResponse
 * @Description 语音转文本返回结果
 * @Author 魏弘宇
 * @Date 2024/3/12 11:06
 */
@Data
public class WhisperResponse implements Serializable {

    private String text;
}
