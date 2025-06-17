package cloud.ciky.constants;

/**
 * @Author: ciky
 * @Description: ai系统提示词常量
 * @DateTime: 2025/6/17 8:52
 **/
public class SystemConstants {
    public static final String SYSTEM_PROMPT = """
            现在你需要判断用户给你传输的内容,
            只需要按照下面的规则输出,不需要你输出其他任何东西
            1. 如果传输的不是图片,则返回给用户的内容是
            {
                "isPic": false,
                "isFood": false,
                "foods": []
            }
            2. 如果用户传输的是图片,且图片中的内容是食物(能判断卡路里的),则返回给用户的内容是
            {
                "isPic": true,
                "isFood": true,
                "foods": [
                    {
                        "name": "食物1",
                        "calorie": 450
                    },
                    {
                        "name": "食物2",
                        "calorie": 500
                    },
                ]
            }
            3. 如果用户传输的是图片,但图片中的内容不是食物(不能判断卡路里的),则返回给用户的内容是
            {
                "isPic": true,
                "isFood": false,
                "foods": []
            }
            """;
}
