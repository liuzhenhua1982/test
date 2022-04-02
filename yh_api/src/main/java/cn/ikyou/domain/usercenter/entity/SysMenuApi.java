package cn.ikyou.domain.usercenter.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


@Data
@TableName("sys_menu_api")
public class SysMenuApi implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("menu_id")
    private Integer menuId;
    @TableField("api_id")
    private Integer apiId;
}