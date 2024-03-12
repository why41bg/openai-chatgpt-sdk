package cn.why41bg.chatgpt.domain.other;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Classname OpenAiResponse
 * @Description OpenAi 通用返回体
 * @Author 魏弘宇
 * @Date 2024/3/12 13:45
 */
@Data
public class OpenAiResponse<T> implements Serializable {

    private String object;

    private List<T> data;

    private Error error;

    @Data
    public static class Error {

        private String message;

        private String type;

        private String param;

        private String code;
    }

}
