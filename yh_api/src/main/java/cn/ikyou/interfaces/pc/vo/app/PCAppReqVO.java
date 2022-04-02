package cn.ikyou.interfaces.pc.vo.app;

import cn.ikyou.infrastructure.request.PageQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PCAppReqVO extends PageQuery {
    @ApiModelProperty(value = "应用平台")
    private String platform;
    @ApiModelProperty(value = "版本号")
    private String appVersion;
    @ApiModelProperty(value = "说明")
    private String remarks;
    @ApiModelProperty(value = "类型")
    private String fileType;
    @ApiModelProperty(value = "类型")
    private String appUrl;

}
