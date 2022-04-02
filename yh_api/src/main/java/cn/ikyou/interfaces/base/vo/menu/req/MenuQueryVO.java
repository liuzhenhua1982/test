package cn.ikyou.interfaces.base.vo.menu.req;

import cn.ikyou.infrastructure.request.PageQuery;
import lombok.Data;

@Data
public class MenuQueryVO extends PageQuery {

    private String menuName;
    private Integer parentId;
    private Integer appId;
}
