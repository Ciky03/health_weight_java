package cloud.ciky.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ciky
 * @Description: c端用户健康数据VO
 * @DateTime: 2025/6/20 14:58
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileVo {

    /**
     * 用户头像url
     */
    private String avatar;

    /**
     * 昵称
     */
    private String name;

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
    private Double weightGoal;

    /**
     *活动程度(0->久坐不动,1->轻度活动,2->中度活动,3->重度活动,4->极度活动)
     */
    private String activityLevel;

    /**
     * 每日卡路里目标(千卡)
     */
    private Integer dailyCalorie;

    /**
     * 每日推荐卡路里目标(千卡)
     */
    private Integer recommendedDailyCalorie;


}
