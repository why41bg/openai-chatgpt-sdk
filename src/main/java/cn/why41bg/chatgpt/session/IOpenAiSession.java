package cn.why41bg.chatgpt.session;

import cn.why41bg.chatgpt.domain.chat.ChatCompletionRequest;
import cn.why41bg.chatgpt.domain.chat.ChatCompletionResponse;
import cn.why41bg.chatgpt.domain.images.ImageRequest;
import cn.why41bg.chatgpt.domain.images.ImageResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

/**
 * @Interface  IOpenAiSession
 * @Description OpenAiSession 规范
 * @Author 魏弘宇
 * @Date 2024/3/11 17:51
 */
public interface IOpenAiSession {

    /**
     * GPT-3.5/4.0
     * @param chatCompletionRequest 请求信息
     * @return                      返回结果
     */
    ChatCompletionResponse chatCompletions(ChatCompletionRequest chatCompletionRequest);

    /**
     * GPT-3.5/4.0 & 流式反馈
     * @param chatCompletionRequest 请求信息
     * @param eventSourceListener   实现监听
     * @return                      返回结果
     */
    EventSource chatCompletions(ChatCompletionRequest chatCompletionRequest, EventSourceListener eventSourceListener) throws JsonProcessingException;

    /**
     * 生成图片
     * @param prompt 图片描述
     * @return 生成图片
     */
    ImageResponse generateImages(String prompt);

    /**
     * 生成图片
     * @param imageRequest 图片描述
     * @return 生成图片
     */
    ImageResponse generateImages(ImageRequest imageRequest);
}
