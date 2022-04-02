package cn.ikyou.domain.usercenter.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrganizationTreeDomainDTO{

    private Integer id;
    private Integer pid;
    private String orgName;
    private Integer userId;
    private String status;
    private Integer version;
    private List<OrganizationTreeDomainDTO> children;
}
