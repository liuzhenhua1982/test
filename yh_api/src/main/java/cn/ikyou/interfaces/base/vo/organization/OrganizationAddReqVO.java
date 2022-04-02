package cn.ikyou.interfaces.base.vo.organization;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrganizationAddReqVO {

    @ApiModelProperty(value = "上级ID")
    private Integer pid;
    @ApiModelProperty(value = "机构名称")
    private String orgName;
    @ApiModelProperty(value = "机构编码")
    private String orgCode;
    @ApiModelProperty(value = "负责人")
    private Integer userId;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "版本号")
    private Integer version;

}
