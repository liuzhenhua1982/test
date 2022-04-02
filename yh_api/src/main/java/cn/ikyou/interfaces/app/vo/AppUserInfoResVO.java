package cn.ikyou.interfaces.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AppUserInfoResVO {

    @ApiModelProperty(value="用户id")
    private Integer userId;
    @ApiModelProperty(value="用户名")
    private String username;
    @ApiModelProperty(value="手机号")
    private String mobile;
    @ApiModelProperty(value="机构ID")
    private Integer orgId;
    @ApiModelProperty(value="机构名称")
    private String orgName;
    @ApiModelProperty(value="性别")
    private String sex;
    @ApiModelProperty(value="邮箱")
    private String email;
    @ApiModelProperty(value="昵称")
    private String nickname;
    @ApiModelProperty(value="姓名")
    private String name;
    @ApiModelProperty(value="头像")
    private String headPortrait;
    @ApiModelProperty(value="状态")
    private Integer status;
    @ApiModelProperty(value="所在城市")
    private String city;
}
