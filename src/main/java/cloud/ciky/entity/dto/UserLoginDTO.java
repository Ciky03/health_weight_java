package cloud.ciky.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @Author: ciky
 * @Description: c端用户登录参数
 * @DateTime: 2025/6/17 9:39
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {
    /**
     * 微信token
     */
    private String code;

    /**
     * 加密数据
     */
    private String encryptedData;

    /**
     * 随机串(与session_key配合解密encryptedData)
     */
    private String iv;


    /**
     * 签名
     */
    private String signature;
}
