package cn.ikyou.interfaces.base.vo.user;

import lombok.Data;

/**
 * 更新密码
 */
@Data
public class PasswordVO {

    private String oldPassword;
    private String password;
    private String password1;

}
