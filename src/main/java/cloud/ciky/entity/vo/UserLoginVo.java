package cloud.ciky.entity.vo;

import cloud.ciky.entity.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ciky
 * @Description: c端用户登录VO
 * @DateTime: 2025/6/17 9:45
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginVo extends User {
    private String token;
}
