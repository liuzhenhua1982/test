package cn.ikyou.domain.business.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
@TableName("t_wk_orders")
public class TWkOrders implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("date")
    private String date;
    @TableField("work_item")
    private String workItem;
    @TableField("item_one")
    private String itemOne;
    @TableField("work_gs")
    private String workGs;
    @TableField("work_fx")
    private String workFx;
    @TableField("work_loation_start")
    private String workLoationStart;
    @TableField("work_locaton_end")
    private String workLocatonEnd;
    @TableField("work_carlist")
    private String workCarlist;
    @TableField("work_machinelist")
    private String workMachinelist;
    @TableField("work_user_list")
    private String workUserList;
    @TableField("work_user_other")
    private Integer workUserOther;
    @TableField("work_before_image")
    private String workBeforeImage;
    @TableField("work_after_image")
    private String workAfterImage;
    @TableField("work_leader_name")
    private String workLeaderName;
    @TableField("work_leader_qm")
    private String workLeaderQm;
    @TableField("org_id")
    private Integer orgId;
    @TableField("org_name")
    private String orgName;
    @TableField("weather")
    private String weather;
    @TableField("temperature")
    private String temperature;
    @TableField("status")
    private String status;
    @TableField("start_time")
    private String startTime;
    @TableField("end_time")
    private String endTime;
    @TableField("create_id")
    private Integer createId;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_id")
    private Integer updateId;
    @TableField("update_time")
    private Date updateTime;
    @TableField("remarks")
    private String remarks;
    @TableField("version")
    @Version
    private Integer version;
    @TableField("is_del")
    @TableLogic
    private Integer isDel;
}