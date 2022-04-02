package cn.ikyou.domain.usercenter.service;

import cn.ikyou.domain.usercenter.entity.SysApi;
import cn.ikyou.interfaces.base.vo.api.ApiAddVO;
import cn.ikyou.interfaces.base.vo.api.ApiQueryVO;
import cn.ikyou.interfaces.base.vo.api.ApiUpdateVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


public interface SysApiDomainService extends IService<SysApi>{

    int delete(Integer id);

    IPage<SysApi> searchPage(ApiQueryVO query);

    int updateApi(ApiUpdateVO apiUpdateVO);

    int add(ApiAddVO apiAddVO);
}
