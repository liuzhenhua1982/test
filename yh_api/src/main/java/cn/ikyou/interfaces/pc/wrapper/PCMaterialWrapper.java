package cn.ikyou.interfaces.pc.wrapper;

import cn.ikyou.domain.business.entity.TMaterial;
import cn.ikyou.domain.dictionary.entity.Dictionary;
import cn.ikyou.interfaces.pc.vo.dict.PCDictResVO;
import cn.ikyou.interfaces.pc.vo.meterial.PCMeterialResVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PCMaterialWrapper {
    public List<PCMeterialResVO> materialToVO(List<TMaterial> materials) {
        List<PCMeterialResVO> list = new ArrayList<>();
        for (TMaterial page : materials) {
            PCMeterialResVO resVO = new PCMeterialResVO();
            BeanUtils.copyProperties(page,resVO);
            list.add(resVO);
        }
        return list;
    }
}
