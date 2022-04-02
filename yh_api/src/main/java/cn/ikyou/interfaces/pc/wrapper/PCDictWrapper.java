package cn.ikyou.interfaces.pc.wrapper;

import cn.ikyou.domain.dictionary.entity.Dictionary;
import cn.ikyou.interfaces.pc.vo.dict.PCDictResVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PCDictWrapper {
    public List<PCDictResVO> ordersToVO(List<Dictionary> dicts) {
        List<PCDictResVO> list = new ArrayList<>();
        for (Dictionary page : dicts) {
            PCDictResVO resVO = new PCDictResVO();
            BeanUtils.copyProperties(page,resVO);
            list.add(resVO);
        }
        return list;
    }
}
