package cn.ikyou.interfaces.pc.vo.orders;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
@Data
public class PCTWkMoreinfoResVO  {
    @ApiModelProperty(value = "工单详细ID")
    private Integer id;
    @ApiModelProperty(value = "作业名称")
    private String zyName;
    @ApiModelProperty(value = "作业类型.1基础作业，2坑槽")
    private String zyType;
    @ApiModelProperty(value = "长度")
    private Float length;
    @ApiModelProperty(value = "高度")
    private Float height;
    @ApiModelProperty(value = "宽度")
    private Float wide;
    @ApiModelProperty(value = "面积")
    private Float area;
    @ApiModelProperty(value = "体积")
    private Float volume;
    @ApiModelProperty(value = "数量")
    private Integer amount;
    @ApiModelProperty(value = "车道，多个车道使用逗号分隔")
    private String lane;
    @ApiModelProperty(value = "位置，可选手动填写")
    private String location;
    @ApiModelProperty(value = "起始庄号")
    private Integer locationStart;
    @ApiModelProperty(value = "结束庄号")
    private Integer locationEnd;
    @ApiModelProperty(value = "内容")
    private String remarks;
    @ApiModelProperty(value = "创建人ID")
    private Integer createId;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新人ID")
    private Integer updateId;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @ApiModelProperty(value = "版本号")
    private Integer version;
    @ApiModelProperty(value = "作业详情")
    private String workDetails;
    @ApiModelProperty(value = "位置，页面显示")
    private String locationGroup;

    public String getWorkDetails(){
        return zyName+" 【"+length+"*"+height+"*"+wide+"】 "+area+" "+volume;
    }


}
