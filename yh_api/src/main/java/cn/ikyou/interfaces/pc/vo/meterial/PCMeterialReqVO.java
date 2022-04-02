package cn.ikyou.interfaces.pc.vo.meterial;

import cn.ikyou.infrastructure.request.PageQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PCMeterialReqVO extends PageQuery {
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "组织机构ID")
    private Integer orgId;
    @ApiModelProperty(value = "关键字")
    private String keys;
}
