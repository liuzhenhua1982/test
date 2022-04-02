package cn.ikyou.interfaces.pc.vo.dict;

import cn.ikyou.infrastructure.request.PageQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PCDictReqVO extends PageQuery {
    @ApiModelProperty(value = "工区ID")
    private Integer orgId;
    @ApiModelProperty(value = "类型")
    private String type;
    @ApiModelProperty(value = "关键字")
    private String keys;
}
