package cn.ikyou.domain.business.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
@TableName("t_cars")
public class TCars implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("business_brand")
    private String businessBrand;
    @TableField("license_car")
    private String licenseCar;
    @TableField("org_id")
    private Integer orgId;
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