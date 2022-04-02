package cn.ikyou.interfaces.app.vo.yhorder;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 开发人：石聪辉
 * 开发时间：2022/1/22
 * 说明：
 */
@Data
@ApiModel(value = "养护工单")
public class YhOrderReqVO {

    @ApiModelProperty(value="日期")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private String date;
    @ApiModelProperty("高速")
    private String workGs;
    @ApiModelProperty("方向")
    private String workFx;
    @ApiModelProperty("一级目录")
    private String itemOne;
    @ApiModelProperty("作业项目")
    private String workItem;
    @ApiModelProperty("起始")
    private String workLoationStart;
    @ApiModelProperty("结束")
    private String workLocatonEnd;
    @ApiModelProperty("使用车辆")
    private String workCarlist;
    @ApiModelProperty("机械列表")
    private String workMachinelist;
    @ApiModelProperty("作业人员")
    private String workUserList;
    @ApiModelProperty("外雇人数")
    private Integer workUserOther;
    @ApiModelProperty("作业前图片")
    private String workBeforeImage;
    @ApiModelProperty("作业后图片")
    private String workAfterImage;
    @ApiModelProperty("工长姓名")
    private String workLeaderName;
    @ApiModelProperty("签名")
    private String workLeaderQm;
    @ApiModelProperty("天气")
    private String weather;
    @ApiModelProperty("温度")
    private String temperature;
    @ApiModelProperty("备注")
    private String remarks;
    @ApiModelProperty("开始时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private String startTime;
    @ApiModelProperty("结束时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private String endTime;
    @ApiModelProperty("组织机构名称")
    private String orgName;
    @ApiModelProperty("状态")
    private String status;

}
