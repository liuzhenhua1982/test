package cn.ikyou.infrastructure.request;

import lombok.Data;

@Data
public class PageQuery {

    /**
     * 当前页
     */
    private Integer page;

    /**
     * 每页的数量
     */
    private Integer num;

}
