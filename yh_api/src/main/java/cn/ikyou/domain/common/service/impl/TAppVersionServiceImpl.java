package cn.ikyou.domain.common.service.impl;

import cn.ikyou.domain.common.dao.TAppVersionMapper;
import cn.ikyou.domain.common.entity.TAppVersion;
import cn.ikyou.domain.common.service.TAppVersionDomainService;
import cn.ikyou.interfaces.app.vo.version.AppVersionReqVO;
import cn.ikyou.interfaces.app.vo.version.AppVersionResVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TAppVersionServiceImpl extends ServiceImpl<TAppVersionMapper, TAppVersion> implements TAppVersionDomainService {

    @Override
    public AppVersionResVO get(AppVersionReqVO appVersionReqVO) {
        AppVersionResVO appVersionResVO=new AppVersionResVO();
        TAppVersion appVersion=baseMapper.selectAppVersion(appVersionReqVO.getPlatform());
        if(null!=appVersion && !appVersion.getAppVersion().equals(appVersionReqVO.getVersion())){
            appVersionResVO.setForceupdate(0);
            appVersionResVO.setUpdateFlag(1);
            appVersionResVO.setUpdateTips(appVersion.getRemarks());
            if("wgt".equals(appVersion.getFileType())){
                appVersionResVO.setWgtFlag(1);
                appVersionResVO.setWgtUrl(appVersion.getWgtName());
            }else{
                appVersionResVO.setWgtFlag(0);
                appVersionResVO.setUpdateUrl(appVersion.getAppUrl());
            }
            appVersionResVO.setVersion(appVersion.getAppVersion());
        }else{
            appVersionResVO.setForceupdate(0);
            appVersionResVO.setUpdateFlag(0);
            appVersionResVO.setUpdateTips("暂无升级版本");
        }
        return appVersionResVO;
    }




}
