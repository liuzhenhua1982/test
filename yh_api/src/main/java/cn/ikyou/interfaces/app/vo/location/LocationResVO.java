package cn.ikyou.interfaces.app.vo.location;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 开发人：石聪辉
 * 开发时间：2022/1/22
 * 说明：
 */
@Data
public class LocationResVO {

    @ApiModelProperty(value = "经度")
    private String longitude;
    @ApiModelProperty(value = "纬度")
    private String latitude;
    @ApiModelProperty(value = "固定")
    private String formname="ZH_matching";
}
