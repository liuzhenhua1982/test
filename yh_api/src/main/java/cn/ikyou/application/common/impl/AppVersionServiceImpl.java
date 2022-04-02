package cn.ikyou.application.common.impl;

import cn.ikyou.application.business.FileService;
import cn.ikyou.application.common.AppVersionService;
import cn.ikyou.application.dto.UserInfoDTO;
import cn.ikyou.domain.common.entity.TAppVersion;
import cn.ikyou.domain.common.service.TAppVersionDomainService;
import cn.ikyou.domain.common.service.impl.TAppVersionServiceImpl;
import cn.ikyou.infrastructure.util.UserInfoUtil;
import cn.ikyou.interfaces.pc.vo.app.PCAppAddReqVO;
import cn.ikyou.interfaces.pc.vo.app.PCAppReqVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Slf4j
@Service
@AllArgsConstructor
public class AppVersionServiceImpl implements AppVersionService {

    private final TAppVersionDomainService TAppVersionServiceImpl;
    @Override
    public IPage<TAppVersion> searchPage(PCAppReqVO reqVO) {
        IPage<TAppVersion> page = new Page<>(reqVO.getPage(), reqVO.getNum());
        QueryWrapper<TAppVersion> queryWrapper = new QueryWrapper();
        queryWrapper.eq(!StringUtils.isEmpty(reqVO.getPlatform()),"platform",reqVO.getPlatform());
        queryWrapper.eq(!StringUtils.isEmpty(reqVO.getAppVersion()),"app_version",reqVO.getAppVersion());
        queryWrapper.like(!StringUtils.isEmpty(reqVO.getRemarks()),"remarks",reqVO.getRemarks());
        queryWrapper.eq(!StringUtils.isEmpty(reqVO.getFileType()),"file_type",reqVO.getFileType());
        queryWrapper.like(!StringUtils.isEmpty(reqVO.getAppUrl()),"app_url",reqVO.getAppUrl());

        queryWrapper.orderByDesc("app_version");
        return TAppVersionServiceImpl.getBaseMapper().selectPage(page,queryWrapper);
    }

    @Override
    public boolean save(PCAppAddReqVO reqVO) {
        UserInfoDTO currentUser = UserInfoUtil.getCurrentUser();
        TAppVersion appVersion = new TAppVersion();
        BeanUtils.copyProperties(reqVO,appVersion);
        appVersion.setCreateId(currentUser.getUserId());
        appVersion.setCreateTime(new Date());
        return TAppVersionServiceImpl.save(appVersion);
    }


}
