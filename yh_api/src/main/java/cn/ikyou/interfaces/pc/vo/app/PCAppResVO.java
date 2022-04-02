package cn.ikyou.interfaces.pc.vo.app;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PCAppResVO {
    @ApiModelProperty(value = "APP ID")
    private Integer id;
    @ApiModelProperty(value = "版本")
    private String appVersion;
    @ApiModelProperty(value = "备注")
    private String remarks;
    @ApiModelProperty(value = "应用类型，ios,android")
    private String platform;
    @ApiModelProperty(value = "文件类型 wgt或app")
    private String fileType;
    @ApiModelProperty(value = "wgt文件名称(通过上传接口获取）")
    private String wgtName;
    @ApiModelProperty(value = "appURL，android为文件名称（通过上传接口获取），ios为appstore地址")
    private String appUrl;
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
}
