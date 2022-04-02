package cn.ikyou.interfaces.base.vo.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 开发人：石聪辉
 * 开发时间：2021/9/9
 * 说明：
 */
@Data
public class ApiUpdateVO extends ApiAddVO{

    @ApiModelProperty(value = "ID")
    private Integer id;
}
