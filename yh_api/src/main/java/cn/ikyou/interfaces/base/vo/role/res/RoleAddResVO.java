package cn.ikyou.interfaces.base.vo.role.res;


import lombok.Data;

import java.util.List;

@Data
public class RoleAddResVO {

    private Integer roleId;
    private String roleName;
    private String roleCode;
    private Integer roleStatus;

    private List<Integer> menus;


}
