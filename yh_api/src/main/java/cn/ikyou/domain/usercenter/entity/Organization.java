package cn.ikyou.domain.usercenter.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("organization")
public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("pid")
    private Integer pid;
    @TableField("org_name")
    private String orgName;
    @TableField("org_code")
    private String orgCode;
    @TableField("city")
    private String city;
    @TableField("user_id")
    private Integer userId;
    /**
     * 状态
     */
    @TableField("status")
    private String status;
    /**
     * 是否删除
     */
    @TableLogic
    @TableField("is_del")
    private Integer isDel;
    /**
     * 乐观锁
     */
    @Version
    @TableField("version")
    private Integer version;
    /**
     * 创建人
     */
    @TableField("create_by")
    private Integer createBy;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新人
     */
    @TableField("update_by")
    private Integer updateBy;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
}
