package cn.ikyou.interfaces.app.vo.yhorder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 开发人：石聪辉
 * 开发时间：2022/1/22
 * 说明：
 */
@Data
@ApiModel(value = "作业详情")
public class ZyDetailResVO {

    @ApiModelProperty(value="ID")
    private Integer id;
    @ApiModelProperty(value="工单ID")
    private Integer wkId;
    @ApiModelProperty(value="作业名称")
    private String zyName;
    @ApiModelProperty(value="作业类型")
    private String zyType;
    @ApiModelProperty(value="长度")
    private Float length;
    @ApiModelProperty(value="高度")
    private Float height;
    @ApiModelProperty(value="宽度")
    private Float wide;
    @ApiModelProperty(value="面积")
    private Float area;
    @ApiModelProperty(value="体积")
    private Float volume;
    @ApiModelProperty(value="车道,多个逗号分隔")
    private String lane;
    @ApiModelProperty(value="数量")
    private Integer amount;
    @ApiModelProperty(value="自定义位置")
    private String location;
    @ApiModelProperty(value="起始桩号")
    private Integer locationStart;
    @ApiModelProperty(value="结束桩号")
    private Integer locationEnd;
    @ApiModelProperty(value="备注内容")
    private String remarks;
}
