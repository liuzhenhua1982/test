package cn.ikyou.application.business;

import cn.ikyou.interfaces.app.vo.yhorder.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface AppYhOrderService {

    YhOrderResVO save(YhOrderReqVO reqVO);

    Integer saveDetails(ZyDetailReqVO detailReqVO);

    Integer saveWL(WlReqVO wlReqVO);

    List<UserResVO> userList();

    List<CarResVO> carList();

    IPage<YhOrderResVO> selectPage(YhOrderQueryReqVO yhOrderQueryReqVO);

    YhOrderResVO orderDetails(Integer wkId);

    int zyDel(Integer id);

    List<ZyDetailResVO> zyDetailList(Integer wkId,Integer zyType);

    int wlDel(Integer id);

    List<WlResVO> wlDetailList(Integer wkId);

    List<MaterialResVO> meterialList(MaterialReqVO materialReqVO);
    boolean update(YhOrderUpdateReqVO updateReqVO);
}
