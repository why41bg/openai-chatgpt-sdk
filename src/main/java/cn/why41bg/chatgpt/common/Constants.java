package cn.why41bg.chatgpt.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Classname Constants
 * @Description 请求上下文
 * @Author 魏弘宇
 * @Date 2024/3/11 15:27
 */
public class Constants {

    /**
     * <a href="https://platform.openai.com/docs/guides/chat/introduction">官网</a> 支持的请求角色类型；system、user、assistant
     */
    @Getter
    @AllArgsConstructor
    public enum Role {

        SYSTEM("system"),
        USER("user"),
        ASSISTANT("assistant"),
        ;

        private final String code;

    }

}

