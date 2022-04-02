package cn.ikyou.interfaces.base.vo.role;

import cn.ikyou.interfaces.base.vo.menu.MenuVO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class RoleVO {

    private Integer roleId;
    private String roleName;
    private String roleCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer roleStatus;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<MenuVO> menus;

}
