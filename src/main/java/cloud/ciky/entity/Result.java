package cloud.ciky.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ciky
 * @Description: 统一返回结果
 * @DateTime: 2025/6/17 9:41
 **/
@Data
public class Result<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    public static <T> Result<T> success(){
        Result<T> result = new Result<>();
        result.code = 1;
        return result;
    }

    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>();
        result.code = 1;
        result.data = data;
        return result;
    }

    public static <T> Result<T> error(String msg){
        Result<T> result = new Result<>();
        result.code = 0;
        result.msg = msg;
        return result;
    }
}
