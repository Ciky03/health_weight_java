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

    SEDENTARY("0", "久坐不活动"),
    LIGHT("1", "轻度活动"),
    MODERATE("2", "中度活动"),
    HIGH("3", "重度活动"),
    EXTREME("4", "极重度活动");

    private final String code;
    private final String desc;

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
