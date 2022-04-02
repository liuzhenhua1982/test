package cn.ikyou.infrastructure.util;

import cn.ikyou.application.dto.UserInfoDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserInfoUtil {

    /**
     * 获取当前用户信息
     * @return
     */
    public static UserInfoDTO getCurrentUser()  {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserInfoDTO)authentication.getPrincipal();
    }
}
