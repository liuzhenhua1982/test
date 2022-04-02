package cn.ikyou.interfaces.app.vo.location;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 开发人：石聪辉
 * 开发时间：2022/1/22
 * 说明：
 */
@Data
public class LocationReqVO {

    @ApiModelProperty(value = "桩号米数+距离桩号的距离 ")
    private String zhm;
    @ApiModelProperty(value = "高速公路标记")
    private String gs;
    @ApiModelProperty(value = "当前位置距离桩号的距离  距离超过 1代表  不在高速上")
    private String distance;
    @ApiModelProperty(value = "桩号以米表示")
    private String zh_m;
    @ApiModelProperty(value = "桩号")
    private String zh;
}
