package cn.ikyou.interfaces.pc.vo.dict;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PCDictUpdateReqVO extends PCDictAddReqVO{
    @ApiModelProperty(value = "ID")
    private Integer id;
}
