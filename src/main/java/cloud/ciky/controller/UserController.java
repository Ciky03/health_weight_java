package cloud.ciky.controller;

import cloud.ciky.context.UserContext;
import cloud.ciky.entity.Result;
import cloud.ciky.entity.dto.UserLoginDTO;
import cloud.ciky.entity.dto.UserProfileDTO;
import cloud.ciky.entity.vo.UserCalorieVo;
import cloud.ciky.entity.vo.UserLoginVo;
import cloud.ciky.entity.vo.UserProfileVo;
import cloud.ciky.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Author: ciky
 * @Description: 用户相关接口
 * @DateTime: 2025/6/16 17:01
 **/
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

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
        Long userId = UserContext.getUserId();
        log.info("登录用户id:{}",userId);
        log.info("用户健康数据:{}",userProfileDTO);
        return userService.updateUserProfile(userProfileDTO,userId);
    }

    /**
     * 根据userId获取用户健康数据
     * @return
     */
    @GetMapping("/profile/list")
    public Result<UserProfileVo> getUserProfileByUserId(){
        Long userId = UserContext.getUserId();
        log.info("登录用户id:{}",userId);
        return userService.getUserProfileByUserId(userId);
    }

    /**
     * 根据userId获取用户每日卡路里/体重/体重增减目标
     * @return
     */
    @GetMapping("/profile/info")
    public Result<UserCalorieVo> getDailyCalorieByUserId(){
        Long userId = UserContext.getUserId();
        log.info("登录用户id:{}",userId);
        //TODO 还需要返回当日摄入的卡路里值
        return userService.getDailyCalorieByUserId(userId);
    }

}
