package cn.ikyou.interfaces.app.vo.yhorder;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class YhOrderUpdateReqVO extends YhOrderReqVO{
    @ApiModelProperty(value="ID")
    private Integer id;
}
