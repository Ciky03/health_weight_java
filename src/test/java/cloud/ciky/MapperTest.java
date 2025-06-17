package cloud.ciky;

import cloud.ciky.entity.model.User;
import cloud.ciky.mapper.UserMapper;
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

    @Test
    public void testMapper(){
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getOpenid, "123"));
        System.out.println(user);
    }
}
