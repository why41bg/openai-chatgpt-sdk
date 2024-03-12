package cn.why41bg.chatgpt.domain.files;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Classname File
 * @Description 文件对象
 * @Author 魏弘宇
 * @Date 2024/3/12 11:56
 */
@Data
public class File implements Serializable {

    private String id;

    private String object;

    private long bytes;

    private long created_at;

    private String filename;

    private String purpose;

    private String status;

    @JsonProperty("status_details")
    private String statusDetails;

}
