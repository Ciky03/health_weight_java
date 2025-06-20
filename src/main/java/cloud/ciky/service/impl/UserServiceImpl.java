package cloud.ciky.service.impl;

import cloud.ciky.entity.Result;
import cloud.ciky.entity.dto.UserLoginDTO;
import cloud.ciky.entity.dto.UserProfileDTO;
import cloud.ciky.entity.model.User;
import cloud.ciky.entity.model.UserProfile;
import cloud.ciky.entity.vo.UserLoginVo;
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

    //测试userId
    private static final Long TEST_USER_ID = 1L;

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
    public Result updateUserProfile(UserProfileDTO userProfileDTO) {
        UserProfile userProfileDB = userProfileMapper.selectOne(new LambdaQueryWrapper<>(UserProfile.class).eq(UserProfile::getUserId, TEST_USER_ID));
        if(userProfileDB == null){
            //新增
            UserProfile userProfile = new UserProfile();
            BeanUtils.copyProperties(userProfileDTO, userProfile);
            userProfile.setUserId(TEST_USER_ID);

            int insert = userProfileMapper.insert(userProfile);

            //判断是否保存成功
            if(insert > 0){
                return Result.success();
            }
            return Result.error("保存失败");
        }else{
            //更新
            Integer id = userProfileDB.getId();
            UserProfile userProfile = new UserProfile();
            BeanUtils.copyProperties(userProfileDTO, userProfile);
            userProfile.setId(id);

            int update = userProfileMapper.updateById(userProfile);
            if(update > 0){
                return Result.success();
            }
            return Result.error("保存失败");
        }
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
}
