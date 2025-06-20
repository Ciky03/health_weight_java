package cloud.ciky.service;

import cloud.ciky.entity.Result;
import cloud.ciky.entity.dto.UserLoginDTO;
import cloud.ciky.entity.vo.UserLoginVo;
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
}
