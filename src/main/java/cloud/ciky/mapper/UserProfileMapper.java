package cloud.ciky.mapper;

import cloud.ciky.entity.model.UserProfile;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: ciky
 * @Description: 用户健康数据表mapper
 * @DateTime: 2025/6/20 10:36
 **/
@Mapper
public interface UserProfileMapper extends BaseMapper<UserProfile> {
}
