package cn.why41bg.chatgpt.session;

/**
 * @Interface  IOpenAiSessionFactory
 * @Description OpenAiSession 工厂规范
 * @Author 魏弘宇
 * @Date 2024/3/11 17:51
 */
public interface IOpenAiSessionFactory {
    IOpenAiSession openSession();
}
