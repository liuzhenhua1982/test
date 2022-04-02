package cn.ikyou.domain.business.service.impl;

import cn.ikyou.domain.business.dao.TCarsMapper;
import cn.ikyou.domain.business.entity.TCars;
import cn.ikyou.domain.business.service.TCarsDomainService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TCarsServiceImpl extends ServiceImpl<TCarsMapper, TCars> implements TCarsDomainService {

}
