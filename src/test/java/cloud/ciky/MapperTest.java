package cloud.ciky;

import cloud.ciky.entity.dto.UserProfileDTO;
import cloud.ciky.entity.model.User;
import cloud.ciky.entity.model.UserProfile;
import cloud.ciky.entity.vo.UserProfileVo;
import cloud.ciky.mapper.UserMapper;
import cloud.ciky.mapper.UserProfileMapper;
import cloud.ciky.service.UserService;
import cloud.ciky.utils.JwtUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.*;

/**
 * @Author: ciky
 * @Description: TODO
 * @DateTime: 2025/6/17 14:39
 **/
@SpringBootTest
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Autowired
    private UserService userService;

    @Test
    public void testUserMapper(){
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getOpenid, "123"));
        System.out.println(user);
    }

    @Test
    public void testUserProfileMapper(){
        UserProfile userProfile = userProfileMapper.selectOne(new LambdaQueryWrapper<UserProfile>().eq(UserProfile::getUserId, 1));
        System.out.println(userProfile);
    }

    @Test
    public void testSaveUserProfile(){

        UserProfileDTO userProfileDTO = new UserProfileDTO("1", 21, 63.0, 170.0, -3.0, "0", null);
        userService.updateUserProfile(userProfileDTO);
    }

    @Test
    public void testGetUserProfile() {
        UserProfileVo userProfileVo = userProfileMapper.getUserProfileByUserId(3L);
        System.out.println(userProfileVo);
    }


}
