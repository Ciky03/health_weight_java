package cloud.ciky;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cloud.ciky.mapper")
public class HealthJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthJavaApplication.class, args);
    }

}
