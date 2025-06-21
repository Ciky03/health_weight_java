package cloud.ciky.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ciky
 * @Description: 用户卡路里数据VO
 * @DateTime: 2025/6/21 14:54
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCalorieVo {
    /**
     * 体重(kg)
     */
    private Double weight;

    /**
     * 体重增减量(kg)
     */
    @TableField("weight_goal")
    private Double weightGoal;

    /**
     * 每日卡路里目标(千卡)
     */
    @TableField("daily_calorie")
    private Integer dailyCalorie;

}
