package cn.ikyou.domain.common.service;

import cn.ikyou.domain.common.entity.TAppVersion;
import cn.ikyou.interfaces.app.vo.version.AppVersionReqVO;
import cn.ikyou.interfaces.app.vo.version.AppVersionResVO;
import com.baomidou.mybatisplus.extension.service.IService;


public interface TAppVersionDomainService extends IService<TAppVersion>{

    AppVersionResVO get(AppVersionReqVO apiAddVO);

}
