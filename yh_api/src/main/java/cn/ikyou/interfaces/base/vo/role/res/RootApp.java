package cn.ikyou.interfaces.base.vo.role.res;

import lombok.Data;

import java.util.List;

@Data
public class RootApp {
    private Integer menuId;
    private String menuName;
    private List<MenuTreeResVO> children;
}
