package cloud.ciky.service.impl;

import cloud.ciky.entity.Result;
import cloud.ciky.entity.dto.UserLoginDTO;
import cloud.ciky.entity.dto.UserProfileDTO;
import cloud.ciky.entity.model.User;
import cloud.ciky.entity.model.UserProfile;
import cloud.ciky.entity.vo.UserCalorieVo;
import cloud.ciky.entity.vo.UserLoginVo;
import cloud.ciky.entity.vo.UserProfileVo;
import cloud.ciky.enums.ActivityLevelEnum;
import cloud.ciky.mapper.UserMapper;
import cloud.ciky.mapper.UserProfileMapper;
import cloud.ciky.service.UserService;
import cloud.ciky.utils.JwtUtil;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ciky
 * @Description: 用户登录实现类
 * @DateTime: 2025/6/17 9:50
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private WxMaService wxMaService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.expire}")
    private Long expire;


    @Override
    public Result<UserLoginVo> login(UserLoginDTO userLoginDTO) {
        try {
            //1.用code换取session
            WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(userLoginDTO.getCode());

            String openid = session.getOpenid();
            String sessionKey = session.getSessionKey();

            //2.解密用户信息
            WxMaUserInfo userInfo = wxMaService.getUserService()
                    .getUserInfo(sessionKey, userLoginDTO.getEncryptedData(), userLoginDTO.getIv());


            //3.保存用户信息
            User user = userMapper.selectOne(
                    new LambdaQueryWrapper<>(User.class)
                            .eq(User::getOpenid, openid)
            );
            if (user == null) {
                user = saveUser(openid, userInfo);
            }

            //4.生成token
            Map<String,Object> claims = new HashMap<>();
            claims.put("userId", user.getId());
            String token = JwtUtil.createJWT(secretKey, expire, claims);

            //5.返回用户信息
            UserLoginVo userLoginVo = new UserLoginVo();
            BeanUtils.copyProperties(user, userLoginVo);
            userLoginVo.setToken(token);
            userLoginVo.setOpenid(null);

            return Result.success(userLoginVo);
        } catch (WxErrorException e) {
            return Result.error(e.getError().getErrorMsg());
        }
    }

    @Override
    public Result updateUserProfile(UserProfileDTO userProfileDTO,Long userId) {
        UserProfile userProfileDB = userProfileMapper.selectOne(new LambdaQueryWrapper<>(UserProfile.class).eq(UserProfile::getUserId, userId));
        if(userProfileDB == null){
            //新增
            UserProfile userProfile = new UserProfile();
            BeanUtils.copyProperties(userProfileDTO, userProfile);
            Integer dailyCalorie = userProfile.getDailyCalorie();
            Integer dailyCalorieCalculated = calculateDailyCalorie(userProfile);
            if(dailyCalorie == null){
                userProfile.setDailyCalorie(dailyCalorieCalculated);
            }
            userProfile.setRecommendedDailyCalorie(dailyCalorieCalculated);
            userProfile.setUserId(userId);
            int insert = userProfileMapper.insert(userProfile);

            //判断是否保存成功
            if(insert > 0){
                return Result.success();
            }
            return Result.error("保存失败");
        }else{
            //更新
            UserProfile userProfile = new UserProfile();
            BeanUtils.copyProperties(userProfileDTO, userProfile);
            Integer dailyCalorie = userProfile.getDailyCalorie();
            Integer dailyCalorieCalculated = calculateDailyCalorie(userProfile);
            if(dailyCalorie == null){
                userProfile.setDailyCalorie(dailyCalorieCalculated);
            }
            userProfile.setRecommendedDailyCalorie(dailyCalorieCalculated);
            userProfile.setUserId(userId);
            Integer id = userProfileDB.getId();
            userProfile.setId(id);
            int update = userProfileMapper.updateById(userProfile);

            //判断是否更新成功
            if(update > 0){
                return Result.success();
            }
            return Result.error("保存失败");
        }
    }

    @Override
    public Result<UserProfileVo> getUserProfileByUserId(Long userId) {
        UserProfileVo userProfileVo = userProfileMapper.getUserProfileByUserId(userId);
        if(userProfileVo == null){
            return Result.error("未查询到用户数据");
        }
        return Result.success(userProfileVo);
    }

    @Override
    public Result<UserCalorieVo> getDailyCalorieByUserId(Long userId) {
        UserCalorieVo userCalorieVo = userProfileMapper.selectDailyCalorieByUserId(userId);
        if(userCalorieVo == null){
            return Result.error("未查询到用户信息");
        }
        return Result.success(userCalorieVo);
    }

    /**
     * 保存用户信息
     */
    private User saveUser(String openid, WxMaUserInfo userInfo) {
        User user = new User();
        user.setOpenid(openid);
        user.setSex(userInfo.getGender());
        user.setName(userInfo.getNickName());
        user.setAvatar(userInfo.getAvatarUrl());
        user.setCreateTime(LocalDateTime.now());
        userMapper.insert(user);
        return user;
    }

    /**
     * 计算每日卡路里目标
     */
    private Integer calculateDailyCalorie(UserProfile userProfile) {
        //1.计算基础代谢率(BMR)
        double bmr = userProfile.getSex().equals("1")
                ? (10 * userProfile.getWeight() + 6.25 * userProfile.getHeight() - 5 * userProfile.getAge() + 5)
                : (10 * userProfile.getWeight() + 6.25 * userProfile.getHeight() - 5 * userProfile.getAge() - 161);

        //2.根据活动程度计算总能量消耗 (TDEE)
        String activityLevel = userProfile.getActivityLevel();
        double activeFactor = ActivityLevelEnum.getByCode(activityLevel).getActiveFactor();
        double tdee = bmr * activeFactor;

        //3.计算
        Double weightGoal = userProfile.getWeightGoal();
        if(weightGoal != 0){
            double calorieAdjustment = (weightGoal * 7700) / 7;
            tdee += calorieAdjustment;
        }

        //4.安全限制（不低于基础代谢的1.2倍）
        double minSafeCalorie = bmr * 1.2;
        tdee = Math.max(tdee, minSafeCalorie);

        return Math.toIntExact(Math.round(tdee));
    }


}
