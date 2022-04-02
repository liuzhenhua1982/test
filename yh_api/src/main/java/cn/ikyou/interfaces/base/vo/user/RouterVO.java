package cn.ikyou.interfaces.base.vo.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * 暂时无用，后续可以优化
 */
@Data
public class RouterVO {
    private String path;
    private String component;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String redirect;
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MetaVO meta;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<RouterVO> children;

}
