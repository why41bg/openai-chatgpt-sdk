package cn.why41bg.chatgpt.domain.chat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Classname ChatCompletionRequest
 * @Description 对话聊天，请求信息依照；<a href="https://platform.openai.com/playground">OpenAI 官网 API 说明</a>构建参数
 * @Author 魏弘宇
 * @Date 2024/3/11 14:51
 */
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class ChatCompletionRequest implements Serializable {

    /**
     * 必选参数，调用的模型名称，默认为 gpt-3.5-turbo
     */
    private String model = Model.GPT_3_5_TURBO.getCode();

    /**
     * 必选参数，提示词，一个包含到目前为止对话的消息列表
     */
    private List<Message> messages;

    /**
     * 可选参数，取值范围为0-2，默认为1
     * 参数代表采样温度，数值越小，则模型会倾向于选择概率较高的词汇，生成的文本会更加保守
     * 反之则更加多样
     */
    private final double temperature = 0.2;

    /**
     * 可选参数，取值范围为0-1，默认为1
     * 控制输出文本的随机性，数值越接近1，随机性越强
     */
    @JsonProperty("top_p")
    private Double topP = 1d;

    /**
     * 可选参数，默认为1
     * 表示一个提示返回几个 Completion
     */
    private final Integer n = 1;

    /**
     * 是否为流式输出
     */
    private boolean stream = false;

    /**
     * 可选参数，默认为 null
     * 该参数接受一个或多个字符串，指定生成文本的停止信号
     * 可以用来控制模型的输出长度或格式
     */
    private List<String> stop;

    /**
     * 可选参数，默认为16
     * 返回结果的 token 数
     */
    @JsonProperty("max_tokens")
    private Integer maxTokens = 2048;

    /**
     * 可选参数，默认为0，取值范围为-2到2
     * 调整模型重复自身的倾向性，较低的值可能使模型重复自身
     * 当返回结果篇幅较大且存在前后语言重复时，可以提高该参数的取值
     */
    @JsonProperty("frequency_penalty")
    private double frequencyPenalty = 0;

    /**
     * 可选参数，默认为0，取值范围为-2到2
     * 调整模型生成新内容的倾向，较低的值可能使模型坚持已有的内容
     * 当返回结果篇幅较大且存在前后主题重复时，可以提高该参数的取值
     */
    @JsonProperty("presence_penalty")
    private double presencePenalty = 0;

    /**
     * 该参数接受一个字典，用于调整特定 token 的概率
     * 一般不建议修改
     */
    @JsonProperty("logit_bias")
    private Map logitBias;

    /**
     * 可选参数，注明当前使用者身份
     */
    private String user;

    @Getter
    @AllArgsConstructor
    public enum Model {
        GPT_3_5_TURBO("gpt-3.5-turbo"),
        GPT_4("gpt-4"),
        GPT_4_32K("gpt-4-32k"),
        ;
        private final String code;
    }

}
