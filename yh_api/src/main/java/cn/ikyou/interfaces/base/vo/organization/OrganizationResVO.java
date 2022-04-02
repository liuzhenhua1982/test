package cn.ikyou.interfaces.base.vo.organization;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "OrganizationVO对象", description = "OrganizationVO对象")
public class OrganizationResVO {

    private Integer id;
    private Integer pid;
    private String orgName;
    private String orgCode;
    private Integer userId;
    private String status;
    private Integer version;
}
