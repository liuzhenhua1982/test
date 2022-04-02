package cn.ikyou.interfaces.pc.vo.app;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PCAppAddReqVO {
    @ApiModelProperty(value = "应用平台")
    private String platform;
    @ApiModelProperty(value = "版本号")
    private String appVersion;
    @ApiModelProperty(value = "说明")
    private String remarks;
    @ApiModelProperty(value = "文件类型")
    private String fileType;
    @ApiModelProperty(value = "appURL，android为文件名称（通过上传接口获取），ios为appstore地址")
    private String appUrl;
    @ApiModelProperty(value = "文件")
    private  String fileName;
    @ApiModelProperty(value = "wgt文件名称(通过上传接口获取）")
    private  String wgtName;
}
