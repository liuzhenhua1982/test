package cn.ikyou.interfaces.pc.vo.orders;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PCOrderDetailsResVO{

    @ApiModelProperty(value="创建人")
    private String name;
    @ApiModelProperty(value="工单编号")
    private Integer id;
    @ApiModelProperty(value="工单日期")
    private String date;
    @ApiModelProperty(value="维修项目O一级分类")
    private String itemOne;
    @ApiModelProperty(value="子项目")
    private String workItem;
    @ApiModelProperty(value="作业高速")
    private String workGs;
    @ApiModelProperty(value="方向")
    private String workFx;
    @ApiModelProperty(value="桩号开始")
    private String workLoationStart;
    @ApiModelProperty(value="桩号结束")
    private String workLocatonEnd;
    @ApiModelProperty(value="使用车辆列表")
    private String workCarlist;
    @ApiModelProperty(value="使用机械列表")
    private String workMachinelist;
    @ApiModelProperty(value="作业人员列表")
    private String workUserList;
    @ApiModelProperty(value="外雇工人数量")
    private Integer workUserOther;
    @ApiModelProperty(value="作业前图片")
    private String workBeforeImage;
    @ApiModelProperty(value="作业后图片")
    private String workAfterImage;
    @ApiModelProperty(value="工长签名")
    private String workLeaderQm;
    @ApiModelProperty(value="工长姓名")
    private String workLeaderName;
    @ApiModelProperty(value="组织机构ID")
    private Integer orgId;
    @ApiModelProperty(value="所属工区")
    private String orgName;
    @ApiModelProperty(value="天气")
    private String weather;
    @ApiModelProperty(value="温度")
    private String temperature;
    @ApiModelProperty(value="状态，SUCCESS正常，COMPLETE归档，EXECUTE进行中")
    private String status;
    @ApiModelProperty(value="开始时间")
    private String startTime;
    @ApiModelProperty(value="结束时间")
    private String endTime;
    @ApiModelProperty(value="创建人ID")
    private Integer createId;
    @ApiModelProperty(value="创建时间")
    private Date createTime;
    @ApiModelProperty(value="更新人ID")
    private Integer updateId;
    @ApiModelProperty(value="更新时间")
    private Date updateTime;
    @ApiModelProperty(value="作业备注")
    private String remarks;
    @ApiModelProperty(value="版本号")
    private Integer version;
    @ApiModelProperty(value="作业列表")
    private List<PCTWkMoreinfoResVO> moreinfoResVOList;
    @ApiModelProperty(value="物料列表（使用材料）")
    private List<PCTWkMaterialResVO> materialResVOList;

}
