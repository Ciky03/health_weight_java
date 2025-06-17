package cloud.ciky.mapper;

import cloud.ciky.entity.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: ciky
 * @Description: 用户表mapper
 * @DateTime: 2025/6/17 11:17
 **/
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
