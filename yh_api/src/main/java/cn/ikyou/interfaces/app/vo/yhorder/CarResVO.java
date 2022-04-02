package cn.ikyou.interfaces.app.vo.yhorder;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 开发人：石聪辉
 * 开发时间：2022/1/22
 * 说明：
 */
@Data
public class CarResVO {

    @ApiModelProperty(value = "车辆ID")
    private Integer id;
    @ApiModelProperty(value = "车牌号")
    private String licenseCar;

}
