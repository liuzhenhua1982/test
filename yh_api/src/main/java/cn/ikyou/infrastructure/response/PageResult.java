package cn.ikyou.infrastructure.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 返回分页数据
 * @param <T>
 */
@Data
public class PageResult<T> implements Serializable {

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
     * 总记录数
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long total;
    /**
     * 分页数据
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<T> list;


    public static <T> PageResult<T> build() {
        return new PageResult<>();
    }


}
