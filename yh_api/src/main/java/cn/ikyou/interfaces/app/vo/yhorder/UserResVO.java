package cn.ikyou.interfaces.app.vo.yhorder;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 开发人：石聪辉
 * 开发时间：2022/1/22
 * 说明：
 */
@Data
public class UserResVO {

    @ApiModelProperty(value = "用户ID")
    private Integer userId;
    @ApiModelProperty(value = "用户帐号")
    private String username;
    @ApiModelProperty(value = "用户姓名")
    private String name;
}
