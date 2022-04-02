package cn.ikyou.domain.usercenter.service.impl;

import cn.ikyou.domain.usercenter.dao.SysApiMapper;
import cn.ikyou.domain.usercenter.entity.SysApi;
import cn.ikyou.domain.usercenter.service.SysApiDomainService;
import cn.ikyou.infrastructure.execption.ServiceCheckException;
import cn.ikyou.infrastructure.util.UserInfoUtil;
import cn.ikyou.interfaces.base.vo.api.ApiAddVO;
import cn.ikyou.interfaces.base.vo.api.ApiQueryVO;
import cn.ikyou.interfaces.base.vo.api.ApiUpdateVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SysApiDomainServiceImpl extends ServiceImpl<SysApiMapper, SysApi> implements SysApiDomainService {

    @Override
    public int delete(Integer id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public IPage<SysApi> searchPage(ApiQueryVO query) {
        QueryWrapper<SysApi> queryWrapper = new QueryWrapper<>();
        Page<SysApi> page = new Page<>(query.getPage(), query.getNum());
        return baseMapper.selectPage(page,queryWrapper);
    }

    @Override
    public int updateApi(ApiUpdateVO apiUpdateVO) {
        SysApi api=baseMapper.selectById(apiUpdateVO.getId());
        if(null==api){
            throw new ServiceCheckException("未发现API");
        }
        BeanUtils.copyProperties(apiUpdateVO,api);
        api.setUpdateBy(UserInfoUtil.getCurrentUser().getUserId());
        api.setUpdateTime(new Date());
        return baseMapper.updateById(api);
    }

    @Override
    public int add(ApiAddVO apiAddVO) {
        SysApi api=new SysApi();
        BeanUtils.copyProperties(apiAddVO,api);
        api.setCreateBy(UserInfoUtil.getCurrentUser().getUserId());
        api.setCreateTime(new Date());
        return baseMapper.insert(api);
    }
}
