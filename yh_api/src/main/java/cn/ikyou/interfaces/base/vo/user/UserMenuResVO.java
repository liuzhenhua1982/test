package cn.ikyou.interfaces.base.vo.user;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("菜单")
public class UserMenuResVO {

    private Integer menuId;
    private String menuName;
    private Integer parentId;
    private Integer orderNum;
    private String path;
    private String component;
    private Integer isOpen;
    private Integer menuType;
    private Integer visible;
    private String perms;
    private String icon;
    private Integer status;

    private List<UserMenuResVO> children;

}
