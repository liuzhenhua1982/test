package cn.ikyou.interfaces.base.vo.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 开发人：石聪辉
 * 开发时间：2021/9/9
 * 说明：
 */
@Data
public class ApiAddVO {

    @ApiModelProperty(value = "URL地址")
    private String url;
    @ApiModelProperty(value = "请求方法，通过字典，API_METHOD获取")
    private String method;
    @ApiModelProperty(value = "api名称")
    private String name;
    @ApiModelProperty(value = "状态，通过字典，API_STATUS获取")
    private String status;
    @ApiModelProperty(value = "api备注")
    private String remarks;
}
