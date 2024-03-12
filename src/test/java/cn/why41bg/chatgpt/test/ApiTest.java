package cn.why41bg.chatgpt.test;

import cn.why41bg.chatgpt.common.Constants;
import cn.why41bg.chatgpt.domain.chat.ChatChoice;
import cn.why41bg.chatgpt.domain.chat.ChatCompletionRequest;
import cn.why41bg.chatgpt.domain.chat.ChatCompletionResponse;
import cn.why41bg.chatgpt.domain.chat.Message;
import cn.why41bg.chatgpt.domain.images.ImageRequest;
import cn.why41bg.chatgpt.domain.images.ImageResponse;
import cn.why41bg.chatgpt.session.Configuration;
import cn.why41bg.chatgpt.session.IOpenAiSession;
import cn.why41bg.chatgpt.session.OpenAiSessionFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.concurrent.CountDownLatch;

/**
 * @Classname ApiTest
 * @Description TODO 类描述
 * @Author 魏弘宇
 * @Date 2024/3/11 19:56
 */
@Slf4j
public class ApiTest {

    private IOpenAiSession openAiSession;

    /**
     * 配置并开启会话
     */
    @Before
    public void testOpenAiSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.setApiKey("sk-LKzZdkp3qU9rv0eaF9078bC6Fc9044B0A6Cf5a57F6365817");
        configuration.setApiHost("https://oneapi.xty.app/");
        OpenAiSessionFactory factory = new OpenAiSessionFactory(configuration);
        this.openAiSession = factory.openSession();
    }

    /**
     * GPT-3.5-turbo 测试
     */
    @Test
    public void testChatCompletions() {
        ChatCompletionRequest request = ChatCompletionRequest
                .builder()
                .messages(Collections.singletonList(Message.builder().role(Constants.Role.USER).content("Java冒泡排序").build()))
                .model(ChatCompletionRequest.Model.GPT_3_5_TURBO.getCode())
                .build();
        ChatCompletionResponse chatCompletionResponse = openAiSession.chatCompletions(request);
        for (ChatChoice choice : chatCompletionResponse.getChoices()) {
            log.info("测试结果：{}", choice.getMessage());
        }
    }

    /**
     * GPT-3.5-turbo 流式应答测试
     */
    @Test
    public void testChatCompletionsStream() throws JsonProcessingException, InterruptedException {
        // 1. 创建参数
        ChatCompletionRequest chatCompletion = ChatCompletionRequest
                .builder()
                .stream(true)
                .messages(Collections.singletonList(Message.builder().role(Constants.Role.USER).content("写一个java冒泡排序").build()))
                .model(ChatCompletionRequest.Model.GPT_3_5_TURBO.getCode())
                .build();
        // 2. 发起请求
        EventSource eventSource = openAiSession.chatCompletions(chatCompletion, new EventSourceListener() {
            @Override
            public void onEvent(@NotNull EventSource eventSource, String id, String type, @NotNull String data) {
                log.info("测试结果：{}", data);
            }
        });
        // 等待
        new CountDownLatch(1).await();
    }

    /**
     * 图片生成测试
     */
    @Test
    public void testGenImages() {
        ImageRequest imageRequest = ImageRequest.builder()
                .prompt("画出中国春节")
                .build();
        ImageResponse imageResponse = openAiSession.generateImages(imageRequest);
        log.info("测试结果 {}", imageResponse);
    }
}
