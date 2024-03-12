package cn.why41bg.chatgpt.domain.images;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Classname Item
 * @Description 图片条目
 * @Author 魏弘宇
 * @Date 2024/3/12 01:02
 */
@Data
public class Item implements Serializable {

    private String url;

    @JsonProperty("b64_json")
    private String b64Json;


}
