package cn.ikyou.interfaces.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AppUserInfoReqVO {

    @ApiModelProperty(value="手机号")
    private String mobile;
    @ApiModelProperty(value="邮箱")
    private String email;
    @ApiModelProperty(value="昵称")
    private String nickname;
    @ApiModelProperty(value="性别")
    private String sex;
    @ApiModelProperty(value="状态")
    private Integer status;
    @ApiModelProperty(value="头像")
    private String headPortrait;
}
