package cn.ikyou.interfaces.base.vo.user;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 用户信息
 */
@Data
public class UserInfoVO {

    private Integer userId;
    private Integer orgId;
    private String orgName;
    private String username;
    private String mobile;
    private String email;
    private String nickname;
    private String headPortrait;
    private Integer status;
    private Date createTime;
    //角色集合
    private List<UserRoleResVO> roles;
    //菜单集合
    private List<UserMenuResVO> menus;

}
