package cn.why41bg.chatgpt.session;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.ContentType;
import cn.why41bg.chatgpt.IOpenAiApi;
import cn.why41bg.chatgpt.domain.chat.ChatCompletionRequest;
import cn.why41bg.chatgpt.domain.chat.ChatCompletionResponse;
import cn.why41bg.chatgpt.domain.files.DeleteFileResponse;
import cn.why41bg.chatgpt.domain.files.UploadFileResponse;
import cn.why41bg.chatgpt.domain.images.ImageRequest;
import cn.why41bg.chatgpt.domain.images.ImageResponse;
import cn.why41bg.chatgpt.domain.other.OpenAiResponse;
import cn.why41bg.chatgpt.domain.whisper.TranscriptionsRequest;
import cn.why41bg.chatgpt.domain.whisper.TranslationsRequest;
import cn.why41bg.chatgpt.domain.whisper.WhisperResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

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

    @Override
    public WhisperResponse audio2Text(File file, TranscriptionsRequest request) {
        // 1. 音频文件
        RequestBody fileBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part multipartBody = MultipartBody.Part.createFormData("file", file.getName(), fileBody);

        // 2. 参数封装
        Map<String, RequestBody> requestBodyMap = new HashMap<>();
        if (StrUtil.isNotBlank(request.getLanguage())) {
            requestBodyMap.put(TranscriptionsRequest.Fields.language, RequestBody.create(MediaType.parse("multipart/form-data"), request.getLanguage()));
        }
        if (StrUtil.isNotBlank(request.getModel())) {
            requestBodyMap.put(TranscriptionsRequest.Fields.model, RequestBody.create(MediaType.parse("multipart/form-data"), request.getModel()));
        }
        if (StrUtil.isNotBlank(request.getPrompt())) {
            requestBodyMap.put(TranscriptionsRequest.Fields.prompt, RequestBody.create(MediaType.parse("multipart/form-data"), request.getPrompt()));
        }
        if (StrUtil.isNotBlank(request.getResponseFormat())) {
            requestBodyMap.put(TranscriptionsRequest.Fields.responseFormat, RequestBody.create(MediaType.parse("multipart/form-data"), request.getResponseFormat()));
        }
        requestBodyMap.put(TranscriptionsRequest.Fields.temperature, RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(request.getTemperature())));
        return this.openAiApi.audio2TextTranscriptions(multipartBody, requestBodyMap).blockingGet();

    }

    @Override
    public WhisperResponse audio2Text(File file, TranslationsRequest request) {
        // 1.音频文件
        RequestBody fileBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part multipartBody = MultipartBody.Part.createFormData("file", file.getName(), fileBody);

        // 2.参数封装
        Map<String, RequestBody> requestBodyMap = new HashMap<>();
        if (StrUtil.isNotBlank(request.getModel())) {
            requestBodyMap.put(TranscriptionsRequest.Fields.model, RequestBody.create(MediaType.parse("multipart/form-data"), request.getModel()));
        }
        if (StrUtil.isNotBlank(request.getPrompt())) {
            requestBodyMap.put(TranscriptionsRequest.Fields.prompt, RequestBody.create(MediaType.parse("multipart/form-data"), request.getPrompt()));
        }
        if (StrUtil.isNotBlank(request.getResponseFormat())) {
            requestBodyMap.put(TranscriptionsRequest.Fields.responseFormat, RequestBody.create(MediaType.parse("multipart/form-data"), request.getResponseFormat()));
        }
        requestBodyMap.put(TranscriptionsRequest.Fields.temperature, RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(request.getTemperature())));
        return this.openAiApi.audio2TestTranslation(multipartBody, requestBodyMap).blockingGet();
    }

    @Override
    public UploadFileResponse uploadFile(String purpose, File file) {
        RequestBody fileBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part multipartBody = MultipartBody.Part.createFormData("file", file.getName(), fileBody);
        RequestBody purposeBody = RequestBody.create(MediaType.parse("multipart/form-data"), purpose);
        return this.openAiApi.uploadFile(multipartBody, purposeBody).blockingGet();

    }

    @Override
    public DeleteFileResponse deleteFile(String fileId) {
        return this.openAiApi.deleteFile(fileId).blockingGet();
    }

    @Override
    public OpenAiResponse<cn.why41bg.chatgpt.domain.files.File> files() {
        return this.openAiApi.files().blockingGet();
    }
}
