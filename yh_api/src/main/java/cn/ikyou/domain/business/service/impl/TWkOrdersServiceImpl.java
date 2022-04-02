package cn.ikyou.domain.business.service.impl;

import cn.ikyou.domain.business.dao.TWkOrdersMapper;
import cn.ikyou.domain.business.entity.TWkOrders;
import cn.ikyou.domain.business.service.TWkOrdersDomainService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TWkOrdersServiceImpl extends ServiceImpl<TWkOrdersMapper, TWkOrders> implements TWkOrdersDomainService {

}
