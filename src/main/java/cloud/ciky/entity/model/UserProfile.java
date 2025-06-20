package cloud.ciky.entity.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: ciky
 * @Description: 用户健康数据表实体类
 * @DateTime: 2025/6/20 10:29
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("health_user_profile")
public class UserProfile implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type= IdType.AUTO)
    private Integer id;

    /**
     * 用户id(health_user表主键)
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 性别(0->女,1->男)
     */
    private String sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 体重(kg)
     */
    private Double weight;

    /**
     * 身高(cm)
     */
    private Double height;

    /**
     * 体重增减量(kg)
     */
    @TableField("weight_goal")
    private Double weightGoal;

    /**
     *活动程度(0->久坐不动,1->轻度活动,2->中度活动,3->重度活动,4->极度活动)
     */
    @TableField("activity_level")
    private String activityLevel;

    /**
     * 每日卡路里目标(千卡)
     */
    @TableField("daily_calorie")
    private Integer dailyCalorie;

    /**
     * 推荐的每日卡路里目标(千卡)
     */
    @TableField("recommended_daily_calorie")
    private Integer recommendedDailyCalorie;
}
