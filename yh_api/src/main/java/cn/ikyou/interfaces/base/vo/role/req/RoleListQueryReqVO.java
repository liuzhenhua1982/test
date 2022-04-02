package cn.ikyou.interfaces.base.vo.role.req;

import cn.ikyou.infrastructure.request.PageQuery;
import lombok.Data;

@Data
public class RoleListQueryReqVO extends PageQuery {
    //角色名称
    private String roleName;
}
