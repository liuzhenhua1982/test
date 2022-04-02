package cn.ikyou.interfaces.base.vo.user.req;

import lombok.Data;

import java.util.List;

/**
 * 用户添加
 */
@Data
public class UserAddReqVO {

    private Integer userId;
    private String username;
    private Integer orgId;
    private String mobile;
    private String email;
    private String nickname;
    private String headPortrait;
    private Integer status;
    private String password;
    private String password1;
    private List<Integer> roles;

}
