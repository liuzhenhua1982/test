package cn.ikyou.interfaces.pc.wrapper;

import cn.ikyou.domain.business.entity.TCars;
import cn.ikyou.domain.dictionary.entity.Dictionary;
import cn.ikyou.interfaces.pc.vo.car.PCCarsResVO;
import cn.ikyou.interfaces.pc.vo.dict.PCDictResVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PCCarsWrapper {
    public List<PCCarsResVO> carsToVO(List<TCars> tCars) {
        List<PCCarsResVO> list = new ArrayList<>();
        for (TCars page : tCars) {
            PCCarsResVO resVO = new PCCarsResVO();
            BeanUtils.copyProperties(page,resVO);
            list.add(resVO);
        }
        return list;
    }
}
