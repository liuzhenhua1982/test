package cn.ikyou.interfaces.pc.vo.meterial;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PCMeterialUpdateReqVO {
    @ApiModelProperty(value = "ID")
    private Integer id;
    @ApiModelProperty(value = "组织机构ID")
    private Integer orgId;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "数量")
    private Integer amount;
    @ApiModelProperty(value = "操作类型（消耗，修复）")
    private Integer operationType;
    @ApiModelProperty(value = "物品规格型号")
    private String model;
    @ApiModelProperty(value = "单位名称")
    private String unitName;
    @ApiModelProperty(value = "版本号")
    private Integer version;
}
