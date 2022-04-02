package cn.ikyou.interfaces.pc.vo.orders;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PCOrdersResVO {
    @ApiModelProperty(value="工单ID")
    private Integer id;
    @ApiModelProperty(value="工单日期")
    private String date;
    @ApiModelProperty(value="二级项目")
    private String workItem;
    @ApiModelProperty(value="一级项目")
    private String itemOne;
    @ApiModelProperty(value="高速")
    private String workGs;
    @ApiModelProperty(value="方向")
    private String workFx;
    @ApiModelProperty(value="起始桩号")
    private String workLoationStart;
    @ApiModelProperty(value="结束桩号")
    private String workLocatonEnd;
    @ApiModelProperty(value="天气")
    private String weather;
    @ApiModelProperty(value="工长签名")
    private String workLeaderQm;
    @ApiModelProperty(value="工长姓名")
    private String workLeaderName;
    @ApiModelProperty(value="工作区")
    private String orgName;
    @ApiModelProperty(value="状态")
    private String status;
    @ApiModelProperty(value="开始时间")
    private String startTime;
    @ApiModelProperty(value="结束时间")
    private String endTime;

}
