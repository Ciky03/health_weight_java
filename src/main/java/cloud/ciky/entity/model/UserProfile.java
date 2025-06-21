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
     *活动程度(0->几乎不运动,1->每周运动1-3天,2->每周运动3-5天,3->每周运动6-7天,4->体力劳动或每天高强度训练)
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
