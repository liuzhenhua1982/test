package cn.ikyou.domain.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;


@Data
@TableName("t_app_version")
public class TAppVersion implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("app_version")
    private String appVersion;
    @TableField("remarks")
    private String remarks;
    @TableField("platform")
    private String platform;
    @TableField("file_type")
    private String fileType;
    @TableField("wgt_name")
    private String wgtName;
    @TableField("app_url")
    private String appUrl;
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