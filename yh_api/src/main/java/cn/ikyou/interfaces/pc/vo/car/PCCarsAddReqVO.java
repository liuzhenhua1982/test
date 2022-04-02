package cn.ikyou.interfaces.pc.vo.car;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PCCarsAddReqVO {
    @ApiModelProperty(value = "商业品牌")
    private String businessBrand;
    @ApiModelProperty(value = "车牌号")
    private String licenseCar;
    @ApiModelProperty(value = "组织机构ID")
    private Integer orgId;

}
