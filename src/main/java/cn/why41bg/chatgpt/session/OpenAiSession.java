package cn.why41bg.chatgpt.session;

import cn.hutool.http.ContentType;
import cn.why41bg.chatgpt.IOpenAiApi;
import cn.why41bg.chatgpt.domain.chat.ChatCompletionRequest;
import cn.why41bg.chatgpt.domain.chat.ChatCompletionResponse;
import cn.why41bg.chatgpt.domain.images.ImageRequest;
import cn.why41bg.chatgpt.domain.images.ImageResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

/**
 * @Classname OpenAiSession
 * @Description OpenAi 会话
 * @Author 魏弘宇
 * @Date 2024/3/11 17:58
 */
public class OpenAiSession implements IOpenAiSession {

    private final Configuration configuration;

    private final IOpenAiApi openAiApi;

    private final EventSource.Factory factory;

    public OpenAiSession(Configuration configuration) {
        this.configuration = configuration;
        this.openAiApi = configuration.getOpenAiApi();
        this.factory = configuration.createRequestFactory();
    }

    @Override
    public ChatCompletionResponse chatCompletions(ChatCompletionRequest chatCompletionRequest) {
        return openAiApi.completions(chatCompletionRequest).blockingGet();
    }

    @Override
    public EventSource chatCompletions(ChatCompletionRequest chatCompletionRequest, EventSourceListener eventSourceListener) throws JsonProcessingException {
        // 构建请求信息
        Request request = new Request.Builder()
                .url(configuration.getApiHost().concat(IOpenAiApi.V1_CHAT_COMPLETIONS))
                .post(RequestBody.create(MediaType.parse(ContentType.JSON.getValue()), new ObjectMapper().writeValueAsString(chatCompletionRequest)))
                .build();

        // 返回结果
        return factory.newEventSource(request, eventSourceListener);
    }

    @Override
    public ImageResponse generateImages(String prompt) {
        ImageRequest imageRequest = ImageRequest.builder().prompt(prompt).build();
        return this.generateImages(imageRequest);
    }

    @Override
    public ImageResponse generateImages(ImageRequest imageRequest) {
        return this.openAiApi.generateImages(imageRequest).blockingGet();
    }
}
