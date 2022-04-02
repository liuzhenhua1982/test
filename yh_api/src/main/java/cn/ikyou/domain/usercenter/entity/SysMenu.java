package cn.ikyou.domain.usercenter.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("sys_menu")
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;
    @TableField("menu_name")
    private String menuName;
    @TableField("app_id")
    private Integer appId;
    @TableField("parent_id")
    private Integer parentId;
    @TableField("order_num")
    private Integer orderNum;
    @TableField("path")
    private String path;
    @TableField("component")
    private String component;
    @TableField("is_open")
    private Integer isOpen;
    @TableField("menu_type")
    private Integer menuType;
    @TableField("visible")
    private Integer visible;
    @TableField("status")
    private Integer status;
    @TableField("perms")
    private String perms;
    @TableField("icon")
    private String icon;
    @TableField("create_by")
    private String createBy;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_by")
    private String updateBy;
    @TableField("update_time")
    private Date updateTime;
    @TableField("remarks")
    private String remarks;
    @TableLogic
    @TableField("is_del")
    private Integer isDel;
}
