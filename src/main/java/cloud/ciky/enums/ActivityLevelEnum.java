package cloud.ciky.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: ciky
 * @Description: 活动程度枚举
 * @DateTime: 2025/6/20 11:29
 **/
@Getter
@AllArgsConstructor
public enum ActivityLevelEnum {

    SEDENTARY("0", "几乎不运动", 1.2),
    LIGHT("1", "每周运动1-3天", 1.375),
    MODERATE("2", "每周运动3-5天", 1.55),
    HIGH("3", "每周运动6-7天", 1.725),
    EXTREME("4", "体力劳动或每天高强度训练", 1.9);

    private final String code;
    private final String desc;
    private final double activeFactor;

    /**
     * 根据code获取枚举
     */
    public static ActivityLevelEnum getByCode(String code) {
        for (ActivityLevelEnum level : values()) {
            if (level.getCode().equals(code)) {
                return level;
            }
        }
        return null;
    }
}
