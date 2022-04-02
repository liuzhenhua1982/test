package cn.ikyou.application.index;


import cn.ikyou.domain.usercenter.dto.UserDTO;
import cn.ikyou.interfaces.app.vo.WeatherResVO;
import cn.ikyou.interfaces.base.vo.user.PasswordVO;

import java.io.IOException;

public interface IndexService {

    int password(PasswordVO password);

    UserDTO userInfo();

    WeatherResVO weather(String city) throws IOException;

    String cityByOrgId(Integer orgId);

}
