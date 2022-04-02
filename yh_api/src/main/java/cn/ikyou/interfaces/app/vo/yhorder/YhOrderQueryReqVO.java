package cn.ikyou.interfaces.app.vo.yhorder;

import cn.ikyou.infrastructure.request.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 开发人：石聪辉
 * 开发时间：2022/1/22
 * 说明：
 */
@Data
@ApiModel(value = "工单查询参数")
public class YhOrderQueryReqVO extends PageQuery {
    @ApiModelProperty(value="开始时间")
    private String startTime;
    @ApiModelProperty(value="结束时间")
    private String endTime;
    @ApiModelProperty(value="状态")
    private String status;

}
