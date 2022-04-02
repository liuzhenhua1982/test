package cn.ikyou.application.dict;

import cn.ikyou.domain.dictionary.entity.Dictionary;
import cn.ikyou.interfaces.pc.vo.dict.PCDictAddReqVO;
import cn.ikyou.interfaces.pc.vo.dict.PCDictReqVO;
import cn.ikyou.interfaces.pc.vo.dict.PCDictResVO;
import cn.ikyou.interfaces.pc.vo.dict.PCDictUpdateReqVO;
import com.baomidou.mybatisplus.core.metadata.IPage;


public interface PCDictService {
    IPage<PCDictResVO> searchPage(PCDictReqVO reqVO);
    boolean add(PCDictAddReqVO addReqVO);
    boolean update(PCDictUpdateReqVO updateReqVO);
    boolean del(Integer id);
    Dictionary details(Integer id);
}
