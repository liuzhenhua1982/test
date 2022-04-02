package cn.ikyou.interfaces.pc.wrapper;

import cn.ikyou.domain.business.entity.TCars;
import cn.ikyou.domain.common.entity.TAppVersion;
import cn.ikyou.interfaces.pc.vo.app.PCAppResVO;
import cn.ikyou.interfaces.pc.vo.car.PCCarsResVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PCAppWrapper {
    public List<PCAppResVO> appToVO(List<TAppVersion> tAppVersions) {
        List<PCAppResVO> list = new ArrayList<>();
        for (TAppVersion page : tAppVersions) {
            PCAppResVO resVO = new PCAppResVO();
            BeanUtils.copyProperties(page,resVO);
            list.add(resVO);
        }
        return list;
    }
}
