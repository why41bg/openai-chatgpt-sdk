package cn.why41bg.chatgpt.interceptor;

import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import lombok.AllArgsConstructor;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @Classname OpenAiInterceptor
 * @Description 拦截HTTP请求并添加特定的认证信息
 * @Author 魏弘宇
 * @Date 2024/3/11 17:37
 */
@AllArgsConstructor
public class OpenAiInterceptor implements Interceptor {

    private String apiKey;

    private Request auth(String apiKey, Request original) {
        HttpUrl url = original.url().newBuilder()
                .build();

        // 设置 Authorization 和 Content-Type 头部信息并创建请求
        return original.newBuilder()
                .url(url)
                .header(Header.AUTHORIZATION.getValue(), "Bearer " + apiKey)
                .header(Header.CONTENT_TYPE.getValue(), ContentType.JSON.getValue())
                .method(original.method(), original.body())
                .build();
    }

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(this.auth(apiKey, chain.request()));
    }
}
