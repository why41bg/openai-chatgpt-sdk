package cn.why41bg.chatgpt.domain.files;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Classname UploadFileResponse
 * @Description 上传文件返回体
 * @Author 魏弘宇
 * @Date 2024/3/12 11:56
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UploadFileResponse extends File implements Serializable {
}
