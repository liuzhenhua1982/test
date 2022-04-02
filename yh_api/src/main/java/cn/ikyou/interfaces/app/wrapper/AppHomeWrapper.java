package cn.ikyou.interfaces.app.wrapper;

import cn.ikyou.application.dto.UserInfoDTO;
import cn.ikyou.interfaces.app.vo.AppUserInfoResVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AppHomeWrapper {

    public AppUserInfoResVO userInfoToVO(UserInfoDTO user) {
        AppUserInfoResVO userInfoResVO=new AppUserInfoResVO();
        if(null==user){
            return userInfoResVO;
        }
        BeanUtils.copyProperties(user,userInfoResVO);
        return userInfoResVO;
    }

}
