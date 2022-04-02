package cn.ikyou.interfaces.base.vo.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 暂时无用
 */
@Data
public class MetaVO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String title;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String icon;

}
