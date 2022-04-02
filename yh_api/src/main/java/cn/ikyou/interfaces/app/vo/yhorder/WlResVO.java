package cn.ikyou.interfaces.app.vo.yhorder;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 开发人：石聪辉
 * 开发时间：2022/1/22
 * 说明：
 */
@Data
public class WlResVO {

    @ApiModelProperty(value="ID")
    private Integer id;
    @ApiModelProperty(value="工单ID")
    private Integer wkId;
    @ApiModelProperty(value="物料ID")
    private Integer meterialId;
    @ApiModelProperty(value="名称")
    private String name;
    @ApiModelProperty(value="数量")
    private Integer amount;
    @ApiModelProperty(value="操作类型")
    private Integer operationType;
    @ApiModelProperty(value="规格")
    private String model;
    @ApiModelProperty(value="单位")
    private String unitName;
}
