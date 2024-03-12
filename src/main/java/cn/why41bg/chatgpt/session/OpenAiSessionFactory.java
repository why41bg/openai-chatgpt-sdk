package cn.why41bg.chatgpt.session;

import cn.why41bg.chatgpt.IOpenAiApi;
import cn.why41bg.chatgpt.interceptor.OpenAiInterceptor;
import lombok.AllArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * @Classname OpenAiSessionFactory
 * @Description TODO 类描述
 * @Author 魏弘宇
 * @Date 2024/3/11 17:57
 */
@AllArgsConstructor
public class OpenAiSessionFactory implements IOpenAiSessionFactory {

    private final Configuration configuration;

    @Override
    public IOpenAiSession openSession() {
        // 配置日志
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // 配置 Http 客户端
        OkHttpClient httpClient = new OkHttpClient
                .Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new OpenAiInterceptor(configuration.getApiKey(), configuration.getAuthToken()))
                .connectTimeout(600, TimeUnit.SECONDS)
                .readTimeout(600, TimeUnit.SECONDS)
                .writeTimeout(600, TimeUnit.SECONDS)
                .build();
        configuration.setOkHttpClient(httpClient);

        // 创建 API 服务
        IOpenAiApi openAiApi = new Retrofit.Builder()
                .baseUrl(configuration.getApiHost())
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build().create(IOpenAiApi.class);
        configuration.setOpenAiApi(openAiApi);

        return new OpenAiSession(configuration);
    }
}
