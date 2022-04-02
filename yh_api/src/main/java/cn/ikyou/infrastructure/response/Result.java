package cn.ikyou.infrastructure.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * 返回结果数据
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {

    /**
     * 状态
     */
    private int status;

    /**
     * 内容提示
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String msg;

    /**
     * 错误内容
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String error;

    /**
     * 返回的数据
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;


    public static <T> Result<T> build() {
        return new Result<>();
    }

}
