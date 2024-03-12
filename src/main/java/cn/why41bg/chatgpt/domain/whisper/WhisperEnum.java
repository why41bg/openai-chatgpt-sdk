package cn.why41bg.chatgpt.domain.whisper;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * @Classname WhisperEnum
 * @Description Whisper模型通用的枚举类
 * @Author 魏弘宇
 * @Date 2024/3/12 11:06
 */
public class WhisperEnum implements Serializable {

    @Getter
    @AllArgsConstructor
    public enum Model {
        WHISPER_1("whisper-1"),
        ;
        private final String code;
    }

    @Getter
    @AllArgsConstructor
    public enum ResponseFormat {
        JSON("json"),
        TEXT("text"),
        SRT("srt"),
        VERBOSE_JSON("verbose_json"),
        VTT("vtt"),
        ;
        private final String code;
    }

}
