package cn.ikyou.domain.business.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
@TableName("t_wk_material")
public class TWkMaterial implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("wk_id")
    private Integer wkId;
    @TableField("meterial_id")
    private Integer meterialId;
    @TableField("name")
    private String name;
    @TableField("amount")
    private Integer amount;
    @TableField("operation_type")
    private Integer operationType;
    @TableField("model")
    private String model;
    @TableField("unit_name")
    private String unitName;
    @TableField("create_id")
    private Integer createId;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_id")
    private Integer updateId;
    @TableField("update_time")
    private Date updateTime;
    @TableField("is_del")
    @TableLogic
    private Integer isDel;
    @TableField("version")
    @Version
    private Integer version;
}