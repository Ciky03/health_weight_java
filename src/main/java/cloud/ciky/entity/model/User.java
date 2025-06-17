package cloud.ciky.entity.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.NonLeaked;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: ciky
 * @Description: 用户表实体类
 * @DateTime: 2025/6/17 11:21
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("health_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type= IdType.AUTO)
    private Long id;

    /**
     * 微信用户唯一标识
     */
    private String openid;

    /**
     * 微信昵称
     */
    private String name;

    /**
     *  性别
     */
    private String sex;

    /**
     * 头像URL
     */
    private String avatar;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;
}
