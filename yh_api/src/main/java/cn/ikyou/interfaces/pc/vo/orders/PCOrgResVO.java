package cn.ikyou.interfaces.pc.vo.orders;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PCOrgResVO {
    @ApiModelProperty(value="组织机构ID")
    private Integer id;
    @ApiModelProperty(value="组织机构ID")
    private String orgName;
}
