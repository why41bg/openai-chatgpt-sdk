package cn.why41bg.chatgpt.session;

import cn.why41bg.chatgpt.IOpenAiApi;
import lombok.*;
import okhttp3.OkHttpClient;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSources;
import org.jetbrains.annotations.NotNull;

/**
 * @Classname Configuration
 * @Description API 相关配置
 * @Author 魏弘宇
 * @Date 2024/3/11 17:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Configuration {

    @NotNull
    private String apiKey;

    private String apiHost;

    private String authToken;

    private IOpenAiApi openAiApi;

    private OkHttpClient okHttpClient;

    public EventSource.Factory createRequestFactory() {
        return EventSources.createFactory(okHttpClient);
    }

}
