package cn.ikyou.interfaces.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 开发人：石聪辉
 * 开发时间：2022/1/22
 * 说明：
 */
@Data
public class WeatherResVO {

    @ApiModelProperty(value = "日期")
    private String date;
    @ApiModelProperty(value = "温度")
    private String temperature;
    @ApiModelProperty(value = "天气")
    private String weather;
}
