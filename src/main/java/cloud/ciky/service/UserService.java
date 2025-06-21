package cloud.ciky.service;

import cloud.ciky.entity.Result;
import cloud.ciky.entity.dto.UserLoginDTO;
import cloud.ciky.entity.dto.UserProfileDTO;
import cloud.ciky.entity.vo.UserCalorieVo;
import cloud.ciky.entity.vo.UserLoginVo;
import cloud.ciky.entity.vo.UserProfileVo;
import org.springframework.stereotype.Service;

/**
 * @Author: ciky
 * @DateTime: 2025/6/17 9:49
 **/
public interface UserService {


    /**
     * 微信登录
     * @param userLoginDTO
     * @return
     */
    public Result<UserLoginVo> login(UserLoginDTO userLoginDTO);

    /**
     * 保存用户健康数据
     * @param userProfileDTO
     * @param userId 用户id
     * @return
     */
    public Result updateUserProfile(UserProfileDTO userProfileDTO,Long userId);

    /**
     * 获取用户健康数据
     * @param userId
     * @return
     */
    Result<UserProfileVo> getUserProfileByUserId(Long userId);

    /**
     * 根据userId获取用户每日卡路里
     * @param userId 用户id
     * @return
     */
    Result<UserCalorieVo> getDailyCalorieByUserId(Long userId);
}
