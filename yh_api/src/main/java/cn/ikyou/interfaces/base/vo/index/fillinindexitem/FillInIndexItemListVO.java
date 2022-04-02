package cn.ikyou.interfaces.base.vo.index.fillinindexitem;

import java.util.Date;

import lombok.Data;


@Data
public class FillInIndexItemListVO {
    private Integer id;
    private Integer fillInIndexId;
    private Integer indexId;
    private Integer pIndexId;
    private String sn;
    private String name;
    private String unit;
    private String sort;
    private String remarks;
    private String content;
    private String orgCode;
    private String orgName;
   
    private Integer createId;
    private Date createTime;
    private Integer updateId;
    private Date updateTime;
}