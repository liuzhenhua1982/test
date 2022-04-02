package cn.ikyou.application.business;

import cn.ikyou.domain.business.entity.TWkOrders;
import cn.ikyou.domain.usercenter.entity.Organization;
import cn.ikyou.interfaces.pc.vo.orders.PCOrderDetailsResVO;
import cn.ikyou.interfaces.pc.vo.orders.PCOrdersReqVO;
import cn.ikyou.interfaces.pc.vo.orders.PCOrdersResVO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface PCYhOrderService {

    IPage<PCOrdersResVO> selectPage(PCOrdersReqVO reqVO);

    PCOrderDetailsResVO details(Integer wkId);

    List<TWkOrders> selectList(PCOrdersReqVO reqVO);
    List<Organization> orgList();
    boolean del(Integer id);
    boolean archive(Integer id);
    String report(Integer id);
}
