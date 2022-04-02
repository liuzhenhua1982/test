package cn.ikyou.application.common;

import cn.ikyou.interfaces.app.vo.location.LocationReqVO;
import cn.ikyou.interfaces.app.vo.location.LocationResVO;

import java.io.IOException;

public interface AppLocationService {
    LocationReqVO getLocation(LocationResVO locationResVO) throws IOException;
}
