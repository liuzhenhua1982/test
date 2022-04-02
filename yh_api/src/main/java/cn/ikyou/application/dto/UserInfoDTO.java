package cn.ikyou.application.dto;

import lombok.Data;

/**
 * 用户信息
 */
@Data
public class UserInfoDTO {

    private Integer userId;
    private String username;
    private Integer orgId;
    private String orgName;
    private String mobile;
    private String password;
    private String email;
    private String name;
    private String nickname;
    private Integer status;
    private String token;
    private String latitude;
    private String longitude;
}
