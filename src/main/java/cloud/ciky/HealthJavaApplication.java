package cloud.ciky;

import cloud.ciky.properties.WxChatProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(WxChatProperties.class)
public class HealthJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthJavaApplication.class, args);
    }

}
