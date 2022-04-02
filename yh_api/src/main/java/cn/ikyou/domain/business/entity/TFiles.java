package cn.ikyou.domain.business.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
@TableName("t_files")
public class TFiles implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("old_name")
    private String oldName;
    @TableField("file_name")
    private String fileName;
    @TableField("root_path")
    private String rootPath;
    @TableField("file_path")
    private String filePath;
    @TableField("file_type")
    private String fileType;
    @TableField("file_flag")
    private String fileFlag;
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