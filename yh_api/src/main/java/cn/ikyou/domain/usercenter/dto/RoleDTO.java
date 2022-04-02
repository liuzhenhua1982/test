package cn.ikyou.domain.usercenter.dto;

import cn.ikyou.domain.usercenter.entity.SysMenu;
import cn.ikyou.domain.usercenter.entity.SysRole;
import lombok.Data;

import java.util.List;

@Data
public class RoleDTO extends SysRole {

    private List<SysMenu> menus;

}
