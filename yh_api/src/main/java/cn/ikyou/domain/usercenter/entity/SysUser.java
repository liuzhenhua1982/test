package cn.ikyou.domain.usercenter.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;
    @TableField("username")
    private String username;
    @TableField("user_code")
    private String userCode;
    @TableField("name")
    private String name;
    @TableField("org_id")
    private Integer orgId;
    @TableField("mobile")
    private String mobile;
    @TableField("password")
    private String password;
    @TableField("email")
    private String email;
    @TableField("nickname")
    private String nickname;
    @TableField("status")
    private Integer status;
    @TableField("is_del")
    @TableLogic
    private Integer isDel;
    @TableField("create_by")
    private String createBy;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_by")
    private String updateBy;
    @TableField("update_time")
    private Date updateTime;
    @TableField("version_no")
    private Integer versionNo;
    @TableField("token")
    private String token;
    @TableField("latitude")
    private String latitude;
    @TableField("longitude")
    private String longitude;

}
