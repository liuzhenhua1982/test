package cn.ikyou.interfaces.app.vo.yhorder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 开发人：石聪辉
 * 开发时间：2022/1/22
 * 说明：
 */
@Data
@ApiModel(value = "养护工单")
public class YhOrderResVO extends YhOrderReqVO{

    @ApiModelProperty(value="工单ID")
    private Integer id;
    @ApiModelProperty("创建人")
    private String name;
}
