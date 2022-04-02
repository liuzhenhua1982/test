package cn.ikyou.domain.usercenter.dto;

import cn.ikyou.domain.usercenter.entity.SysUser;
import lombok.Data;

import java.util.List;

/**
 * 开发人：石聪辉
 * 开发时间：2021/9/9
 * 说明：
 */
@Data
public class UserDTO extends SysUser {

    private String orgName;
    private List<RoleDTO> roles;
    private List<MenuDTO> menus;

}
