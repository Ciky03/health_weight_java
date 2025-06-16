package cloud.ciky.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ciky
 * @Description: 用户相关接口
 * @DateTime: 2025/6/16 17:01
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/test")
    public String test(){
        return "success";
    }
}
