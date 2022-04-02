package cn.ikyou.interfaces.base.vo.organization;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "OrganizationVO对象", description = "OrganizationVO对象")
public class OrganizationTreeResVO {

    private Integer id;
    private Integer pid;
    private String orgCode;
    private String orgName;
    private Integer userId;
    private String status;
    private Integer version;
    private List<OrganizationTreeResVO> children;
}
