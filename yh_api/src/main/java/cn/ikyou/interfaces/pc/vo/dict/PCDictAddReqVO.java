package cn.ikyou.interfaces.pc.vo.dict;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PCDictAddReqVO {
    @ApiModelProperty(value = "上级ID")
    private Integer pId;
    @ApiModelProperty(value = "组织机构ID")
    private Integer orgId;
    @ApiModelProperty(value = "类型")
    private String type;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "编码")
    private String code;
}
