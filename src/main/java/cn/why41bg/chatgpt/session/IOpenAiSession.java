package cn.why41bg.chatgpt.session;

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
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

import java.io.File;

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

    /**
     * 音频转文本
     * @param file 音频文件
     * @param request 请求信息
     * @return 文本结果
     */
    WhisperResponse audio2Text(File file, TranscriptionsRequest request);

    /**
     * 音频转文本
     * @param file 音频文件
     * @param request 请求信息
     * @return 文本结果
     */
    WhisperResponse audio2Text(File file, TranslationsRequest request);

    /**
     * 上传文件
     * @param file 上传的文件
     * @return 上传的文件相关信息
     */
    UploadFileResponse uploadFile(String purpose, File file);

    /**
     * 根据文件 Id 删除文件
     * @param fileId 要删除的文件 Id
     * @return 删除文件返回结果信息
     */
    DeleteFileResponse deleteFile(String fileId);

    /**
     * 查看已上传文件列表
     * @return 已上传文件信息
     */
    OpenAiResponse<cn.why41bg.chatgpt.domain.files.File> files();
}
