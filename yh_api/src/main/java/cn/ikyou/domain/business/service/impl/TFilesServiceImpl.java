package cn.ikyou.domain.business.service.impl;

import cn.ikyou.domain.business.dao.TFilesMapper;
import cn.ikyou.domain.business.entity.TFiles;
import cn.ikyou.domain.business.service.TFilesDomainService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TFilesServiceImpl extends ServiceImpl<TFilesMapper, TFiles> implements TFilesDomainService {

}
