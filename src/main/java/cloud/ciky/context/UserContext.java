package cloud.ciky.context;

/**
 * @Author: ciky
 * @Description: 用户上下文
 * @DateTime: 2025/6/21 16:40
 **/
public class UserContext {
    public static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setUserId(Long userId) {
        threadLocal.set(userId);
    }

    public static Long getUserId() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }
}
