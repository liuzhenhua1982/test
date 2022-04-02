package cn.ikyou.application.business;

import cn.ikyou.domain.business.entity.TMaterial;
import cn.ikyou.interfaces.pc.vo.meterial.PCMeterialAddReqVO;
import cn.ikyou.interfaces.pc.vo.meterial.PCMeterialReqVO;
import cn.ikyou.interfaces.pc.vo.meterial.PCMeterialResVO;
import cn.ikyou.interfaces.pc.vo.meterial.PCMeterialUpdateReqVO;
import com.baomidou.mybatisplus.core.metadata.IPage;


public interface PCMeterialService {
    IPage<PCMeterialResVO> searchPage(PCMeterialReqVO reqVO);
    boolean add(PCMeterialAddReqVO addReqVO);
    boolean update(PCMeterialUpdateReqVO updateReqVO);
    boolean del(Integer id);
    TMaterial details(Integer id);
}
