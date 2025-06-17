package cloud.ciky.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.model.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @Author: ciky
 * @Description: AI对话接口
 * @DateTime: 2025/6/16 18:09
 **/
@RestController
@RequestMapping("/ai")
public class ChatController {

    @Autowired
    private ChatClient chatClient;

    /**
     * 聊天接口(ai识图)
     * @param prompt 提示词
     * @param files  文件
     * @return
     */
    @RequestMapping(value = "/chat", produces = "text/html;charset=utf-8")
    public Flux<String> chat(
            @RequestParam("prompt") String prompt,
            @RequestParam(value = "files", required = false) List<MultipartFile> files) {

        if (files == null || files.isEmpty()) {
            return textChat(prompt);
        } else {
            return multiModalChat(prompt, files);
        }
    }

    /**
     * 多模态聊天
     */
    private Flux<String> multiModalChat(String prompt, List<MultipartFile> files) {
        //解析多媒体
        List<Media> medias = files.stream()
                .map(file -> new Media(
                                MimeType.valueOf(file.getContentType()),
                                file.getResource()
                        )
                )
                .toList();

        //请求模型
        return chatClient.prompt()
                .user(p -> p.text(prompt).media(medias.toArray(Media[]::new)))
                .stream()
                .content();
    }

    /**
     * 纯文字聊天
     */
    private Flux<String> textChat(String prompt) {
        return chatClient.prompt()
                .user(prompt)
                .stream()
                .content();
    }
}
