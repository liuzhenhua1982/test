package cn.ikyou.application.cars;

import cn.ikyou.domain.business.entity.TCars;
import cn.ikyou.interfaces.pc.vo.car.PCCarsAddReqVO;
import cn.ikyou.interfaces.pc.vo.car.PCCarsReqVO;
import cn.ikyou.interfaces.pc.vo.car.PCCarsResVO;
import cn.ikyou.interfaces.pc.vo.car.PCCarsUpdateReqVO;

import com.baomidou.mybatisplus.core.metadata.IPage;

public interface PCCarsService {
    IPage<PCCarsResVO> searchPage(PCCarsReqVO reqVO);
    boolean add(PCCarsAddReqVO addReqVO);
    boolean update(PCCarsUpdateReqVO updateReqVO);
    boolean del(Integer id);
    TCars details(Integer id);
}
