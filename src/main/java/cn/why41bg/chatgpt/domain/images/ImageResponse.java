package cn.why41bg.chatgpt.domain.images;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Classname ImageResponse
 * @Description 图片生成相应
 * @Author 魏弘宇
 * @Date 2024/3/12 00:58
 */
@Data
public class ImageResponse implements Serializable {

    /**
     * 图片条目
     */
    private List<Item> data;

    /**
     * 创建时间
     */
    private long created;

}
