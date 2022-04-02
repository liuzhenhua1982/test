package cn.ikyou.domain.business.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
@TableName("t_wk_moreinfo")
public class TWkMoreinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("wk_id")
    private Integer wkId;
    @TableField("zy_name")
    private String zyName;
    @TableField("zy_type")
    private String zyType;
    @TableField("length")
    private Float length;
    @TableField("height")
    private Float height;
    @TableField("wide")
    private Float wide;
    @TableField("area")
    private Float area;
    @TableField("volume")
    private Float volume;
    @TableField("amount")
    private Integer amount;
    @TableField("lane")
    private String lane;
    @TableField("location")
    private String location;
    @TableField("location_start")
    private Integer locationStart;
    @TableField("location_end")
    private Integer locationEnd;
    @TableField("remarks")
    private String remarks;
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