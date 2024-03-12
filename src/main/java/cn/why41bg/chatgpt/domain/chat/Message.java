package cn.why41bg.chatgpt.domain.chat;

import cn.why41bg.chatgpt.common.Constants;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Classname Message
 * @Description TODO 类描述
 * @Author 魏弘宇
 * @Date 2024/3/11 14:52
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class Message implements Serializable {
    /**
     * 角色，必填，指示消息作者的角色，可以是以下之一：system，user，assistant
     */
    private String role;

    /**
     * 内容，必填，指示消息的内容，可以为空
     */
    private String content;

    /**
     * 名称，可填，指示这条消息的作者名称，
     */
    private String name;

    @Builder
    public Message(Constants.Role role, String content, String name) {
        this.role = role.getCode();
        this.content = content;
        this.name = name;
    }

}
