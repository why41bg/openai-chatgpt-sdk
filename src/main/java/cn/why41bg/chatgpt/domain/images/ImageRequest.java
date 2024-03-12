package cn.why41bg.chatgpt.domain.images;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @Classname ImageRequest
 * @Description 图片生成请求
 * @Author 魏弘宇
 * @Date 2024/3/12 00:57
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
public class ImageRequest implements Serializable {

    /**
     * 图片描述，必选项
     */
    @NonNull
    private String prompt;

    /**
     * 完成次数，可选
     */
    @Builder.Default
    private Integer n = 1;

    /**
     * 图片大小，可选
     */
    @Builder.Default
    private String size = Size.SIZE_256.getCode();

    /**
     * 图片返回格式，可选
     * URL 或者 B64_JSON
     */
    @JsonProperty("response_format")
    @Builder.Default
    private String responseFormat = ResponseFormat.URL.getCode();

    /**
     * 发送用户，可选
     */
    private String user;

    @Getter
    @AllArgsConstructor
    public enum Size {
        SIZE_256("256x256"),
        SIZE_512("512x512"),
        SIZE_1024("1024x1024"),
        ;
        private final String code;
    }

    @Getter
    @AllArgsConstructor
    public enum ResponseFormat {
        URL("url"),
        B64_JSON("b64_json"),
        ;
        private final String code;
    }
}
