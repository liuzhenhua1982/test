package cn.ikyou.interfaces.app.vo.yhorder;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 开发人：石聪辉
 * 开发时间：2022/1/26
 * 说明：
 */
@Data
public class MaterialReqVO {

    @ApiModelProperty(value="物料名称")
    private String name;
}
