package cn.ikyou.domain.business.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
@TableName("t_sites")
public class TSites implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("addr")
    private String addr;
    @TableField("longitude")
    private String longitude;
    @TableField("latitude")
    private String latitude;
    @TableField("create_id")
    private Integer createId;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_id")
    private Integer updateId;
    @TableField("update_time")
    private Date updateTime;
    @TableField("version")
    @Version
    private Integer version;
    @TableField("is_del")
    @TableLogic
    private Integer isDel;
}