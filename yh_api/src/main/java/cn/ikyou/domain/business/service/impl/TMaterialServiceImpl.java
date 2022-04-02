package cn.ikyou.domain.business.service.impl;

import cn.ikyou.domain.business.dao.TMaterialMapper;
import cn.ikyou.domain.business.entity.TMaterial;
import cn.ikyou.domain.business.service.TMaterialDomainService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TMaterialServiceImpl extends ServiceImpl<TMaterialMapper, TMaterial> implements TMaterialDomainService {

}
