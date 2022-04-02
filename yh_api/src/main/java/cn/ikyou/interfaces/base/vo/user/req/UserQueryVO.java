package cn.ikyou.interfaces.base.vo.user.req;

import cn.ikyou.infrastructure.request.PageQuery;
import lombok.Data;

/**
 * 用户查询
 */
@Data
public class UserQueryVO extends PageQuery {

    private String username;
    private Integer orgId;

}
