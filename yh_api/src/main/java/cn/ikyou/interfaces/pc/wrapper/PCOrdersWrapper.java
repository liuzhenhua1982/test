package cn.ikyou.interfaces.pc.wrapper;

import cn.ikyou.domain.business.entity.TWkOrders;
import cn.ikyou.domain.usercenter.entity.Organization;
import cn.ikyou.interfaces.app.vo.yhorder.YhOrderResVO;
import cn.ikyou.interfaces.pc.vo.orders.PCOrdersResVO;
import cn.ikyou.interfaces.pc.vo.orders.PCOrgResVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PCOrdersWrapper {

    public List<PCOrdersResVO> ordersToVO(List<TWkOrders> pages) {
        List<PCOrdersResVO> list = new ArrayList<>();
        for (TWkOrders page : pages) {
            PCOrdersResVO resVO = new PCOrdersResVO();
            BeanUtils.copyProperties(page,resVO);
            list.add(resVO);
        }
        return list;
    }
    public List<PCOrgResVO> orgListToVO(List<Organization> pages) {
        List<PCOrgResVO> list = new ArrayList<>();
        for (Organization page : pages) {
            PCOrgResVO resVO = new PCOrgResVO();
            BeanUtils.copyProperties(page,resVO);
            list.add(resVO);
        }
        return list;
    }
}
