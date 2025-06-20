package cloud.ciky.controller;

import cloud.ciky.entity.Result;
import cloud.ciky.entity.dto.UserLoginDTO;
import cloud.ciky.entity.dto.UserProfileDTO;
import cloud.ciky.entity.vo.UserLoginVo;
import cloud.ciky.entity.vo.UserProfileVo;
import cloud.ciky.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: ciky
 * @Description: 用户相关接口
 * @DateTime: 2025/6/16 17:01
 **/
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    //测试userId
    private static final Long TEST_USER_ID = 1L;

    @Autowired
    private UserService userService;

    /**
     * 微信登录
     * @param userLoginDTO 登录参数
     * @return
     */
    @PostMapping("/login")
    public Result<UserLoginVo> login(@RequestBody UserLoginDTO userLoginDTO){
        log.info("微信用户登录:{}",userLoginDTO);
        return userService.login(userLoginDTO);
    }

    /**
     * 保存用户健康数据
     * @param userProfileDTO
     * @return
     */
    @PostMapping("/profile/save")
    public Result updateUserProfile(@RequestBody UserProfileDTO userProfileDTO){
        log.info("当前登录用户id:{}",TEST_USER_ID);
        log.info("用户健康数据:{}",userProfileDTO);
        return userService.updateUserProfile(userProfileDTO);
    }

    /**
     * 根据userId获取用户健康数据
     * @return
     */
    public Result<UserProfileVo> getUserProfileByUserId(){
        log.info("当前登录用户id:{}",TEST_USER_ID);
        return userService.getUserProfileByUserId(TEST_USER_ID);
    }

}
