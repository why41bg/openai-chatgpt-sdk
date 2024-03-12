package cn.why41bg.chatgpt.domain.files;

import lombok.Data;

import java.io.Serializable;

/**
 * @Classname DeleteFileResponse
 * @Description 删除文件返回体
 * @Author 魏弘宇
 * @Date 2024/3/12 11:56
 */
@Data
public class DeleteFileResponse implements Serializable {

    private String id;

    /**
     * 删除的文件类型，如：file
     */
    private String object;

    /**
     * 是否删除成功，删除成功为 true
     */
    private boolean deleted;

}
