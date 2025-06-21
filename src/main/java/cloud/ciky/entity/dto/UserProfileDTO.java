package cloud.ciky.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ciky
 * @Description: c端用户健康数据参数
 * @DateTime: 2025/6/20 10:55
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDTO {

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
     *活动程度(0->几乎不运动,1->每周运动1-3天,2->每周运动3-5天,3->每周运动6-7天,4->体力劳动或每天高强度训练)
     */
    private String activityLevel;

    /**
     * 每日卡路里目标(千卡)
     */
    private Integer dailyCalorie;
}
