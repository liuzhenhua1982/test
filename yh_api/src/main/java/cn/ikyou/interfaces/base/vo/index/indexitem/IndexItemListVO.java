package cn.ikyou.interfaces.base.vo.index.indexitem;


import java.util.Date;

import lombok.Data;


@Data
public class IndexItemListVO {

    private Integer id;
    private String sn;
    private String name;
    private String unit;
    private String status;
    private String sort;
    private Date createTime;
}