package cn.why41bg.chatgpt;

import cn.why41bg.chatgpt.domain.chat.ChatCompletionRequest;
import cn.why41bg.chatgpt.domain.chat.ChatCompletionResponse;
import cn.why41bg.chatgpt.domain.images.ImageRequest;
import cn.why41bg.chatgpt.domain.images.ImageResponse;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @Interface IOpenAiApi
 * @Description 按照 ChatGPT <a href="https://platform.openai.com/playground"> 官网 API 模型的说明 </a> ，定义接口。
 * @Author 魏弘宇
 * @Date 2024/3/11 14:44
 */
public interface IOpenAiApi {

    String V1_CHAT_COMPLETIONS = "v1/chat/completions";

    String V1_IMAGES_GENERATIONS = "v1/images/generations";

    /**
     * 文本问答接口
     * @param chatCompletionRequest 请求信息
     * @return 回答结果
     */
    @POST(V1_CHAT_COMPLETIONS)
    Single<ChatCompletionResponse> completions(@Body ChatCompletionRequest chatCompletionRequest);

    /**
     * 图片生成接口
     * @param imageRequest 请求信息
     * @return 生成图片
     */
    @POST(V1_IMAGES_GENERATIONS)
    Single<ImageResponse> generateImages(@Body ImageRequest imageRequest);

}
