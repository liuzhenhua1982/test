package cn.ikyou.interfaces.base.vo.menu;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("菜单")
public class MenuVO {
    private Integer menuId;
    private Integer appId;
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
}
