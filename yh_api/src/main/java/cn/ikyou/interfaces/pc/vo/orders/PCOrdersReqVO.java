package cn.ikyou.interfaces.pc.vo.orders;

import cn.ikyou.infrastructure.request.PageQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PCOrdersReqVO extends PageQuery {
    @ApiModelProperty(value="工作区ID")
    private Integer orgId;
    @ApiModelProperty(value="一级项目")
    private String itemOne;
    @ApiModelProperty(value="二级项目")
    private String workItem;
    @ApiModelProperty(value="起始日期")
    private String startTime;
    @ApiModelProperty(value="结束日期")
    private String endTime;
    @ApiModelProperty(value="排序顺序参数:ASC;DESC")
    private String sort;
    @ApiModelProperty(value="关键字")
    private String keys;

}
