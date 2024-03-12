package cn.why41bg.chatgpt;

import cn.why41bg.chatgpt.domain.chat.ChatCompletionRequest;
import cn.why41bg.chatgpt.domain.chat.ChatCompletionResponse;
import cn.why41bg.chatgpt.domain.files.DeleteFileResponse;
import cn.why41bg.chatgpt.domain.files.File;
import cn.why41bg.chatgpt.domain.files.UploadFileResponse;
import cn.why41bg.chatgpt.domain.images.ImageRequest;
import cn.why41bg.chatgpt.domain.images.ImageResponse;
import cn.why41bg.chatgpt.domain.other.OpenAiResponse;
import cn.why41bg.chatgpt.domain.whisper.WhisperResponse;
import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.*;

import java.util.Map;

/**
 * @Interface IOpenAiApi
 * @Description 按照 ChatGPT <a href="https://platform.openai.com/playground"> 官网 API 模型的说明 </a> ，定义接口。
 * @Author 魏弘宇
 * @Date 2024/3/11 14:44
 */
public interface IOpenAiApi {

    String V1_CHAT_COMPLETIONS = "v1/chat/completions";

    String V1_IMAGES_GENERATIONS = "v1/images/generations";

    String V1_AUDIO_TRANSCRIPTIONS = "v1/audio/transcriptions";

    String V1_FILES = "v1/files";

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

    /**
     *
     * @param file 音频文件
     * @param requestBodyMap 请求体信息
     * @return 音频转文本结果
     */
    @Multipart
    @POST(V1_AUDIO_TRANSCRIPTIONS)
    Single<WhisperResponse> audio2TextTranscriptions(@Part MultipartBody.Part file, @PartMap Map<String, RequestBody> requestBodyMap);

    /**
     *
     * @param file 音频文件
     * @param requestBodyMap 请求体信息
     * @return 音频转文本结果
     */
    @Multipart
    @POST(V1_AUDIO_TRANSCRIPTIONS)
    Single<WhisperResponse> audio2TestTranslation(@Part MultipartBody.Part file, @PartMap Map<String, RequestBody> requestBodyMap);

    @Multipart
    @POST(V1_FILES)
    Single<OpenAiResponse<File>> files();

    @Multipart
    @POST
    Single<UploadFileResponse> uploadFile(@Part MultipartBody.Part file, @Part("purpose") RequestBody purpose);

    @Multipart
    @POST(V1_FILES + "/{file_id}")
    Single<DeleteFileResponse> deleteFile(@Path("file_id") String fileId);
}
