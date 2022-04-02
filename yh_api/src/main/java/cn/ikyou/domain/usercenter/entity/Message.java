package cn.ikyou.domain.usercenter.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("message")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 消息标题
     */
    @TableField("title")
    private String title;
    /**
     * 消息内容
     */
    @TableField("content")
    private String content;
    /**
     * 消息接收人
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 消息类型
     */
    @TableField("info_type")
    private String infoType;
    /**
     * 是否读取，YES读取,NOT未读取
     */
    @TableField("is_read")
    private String isRead;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;
    /**
     * 读取时间
     */
    @TableField("read_time")
    private LocalDateTime readTime;
    /**
     * 扩展内容，可包含一些业务消息
     */
    @TableField("extend_content")
    private String extendContent;

    @TableLogic
    @TableField("is_del")
    private Integer isDel;
    @Version
    @TableField("version")
    private Integer version;

}
