package cn.ikyou.domain.usercenter.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
@TableName("sys_api")
public class SysApi implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("url")
    private String url;
    @TableField("method")
    private String method;
    @TableField("name")
    private String name;
    @TableField("remarks")
    private String remarks;
    @TableField("status")
    private String status;
    @TableField("create_by")
    private Integer createBy;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_by")
    private Integer updateBy;
    @TableField("update_time")
    private Date updateTime;
    @TableField("is_del")
    @TableLogic
    private Integer isDel;
    @TableField("version")
    @Version
    private Integer version;
}