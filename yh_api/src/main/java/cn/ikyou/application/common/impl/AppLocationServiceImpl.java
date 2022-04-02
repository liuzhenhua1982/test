package cn.ikyou.application.common.impl;

import cn.ikyou.application.common.AppLocationService;
import cn.ikyou.application.dto.LocationDTO;
import cn.ikyou.infrastructure.common.HttpUrls;
import cn.ikyou.infrastructure.execption.ServiceCheckException;
import cn.ikyou.infrastructure.util.OkHttpUtil;
import cn.ikyou.interfaces.app.vo.location.LocationReqVO;
import cn.ikyou.interfaces.app.vo.location.LocationResVO;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * 开发人：刘振华
 * 开发时间：2022/1/22
 * 说明：
 */
@Slf4j
@Service
@AllArgsConstructor
public class AppLocationServiceImpl implements AppLocationService {

    @Override
    public LocationReqVO getLocation(LocationResVO locationResVO) throws IOException {

       String params = JSON.toJSONString(locationResVO);
       String respons = OkHttpUtil.post(HttpUrls.LOCATION_URL,params);
       LocationDTO dto = JSON.parseObject(respons,LocationDTO.class);
        LocationReqVO resVo = new LocationReqVO();
       if("0".equals(dto.getCode())){
           for (Map<String, String> map : dto.getBody()) {
               resVo.setDistance(map.get("distance"));
               resVo.setGs(map.get("GS"));
               resVo.setZhm(map.get("ZHM"));
               resVo.setZh_m(map.get("ZH_m"));
               resVo.setZh(map.get("ZH"));
           }
       }else{
           throw new ServiceCheckException("获取固定桩失败");
       }
        return resVo;
    }
}
