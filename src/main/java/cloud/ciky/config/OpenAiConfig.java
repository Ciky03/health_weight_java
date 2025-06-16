package cloud.ciky.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: ciky
 * @Description: openAI配置类
 * @DateTime: 2025/6/16 18:01
 **/
@Configuration
public class OpenAiConfig {

    @Bean
    public ChatClient chatClient(OpenAiChatModel model){
        return ChatClient.builder(model)    //创建ChatClient工厂
                .defaultOptions(ChatOptions.builder().model("qwen-omni-turbo").build())
                .defaultAdvisors(
                        new SimpleLoggerAdvisor()                  //添加默认的Advisor记录日志
                )
                .build();   //构建ChatClient实例
    }


}
