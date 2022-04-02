package cn.ikyou.domain.business.service.impl;

import cn.ikyou.domain.business.dao.TSitesMapper;
import cn.ikyou.domain.business.entity.TSites;
import cn.ikyou.domain.business.service.TSitesDomainService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TSitesServiceImpl extends ServiceImpl<TSitesMapper, TSites> implements TSitesDomainService {

}
