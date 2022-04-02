package cn.ikyou.domain.usercenter.service.impl;

import cn.ikyou.domain.usercenter.dao.SysMenuApiMapper;
import cn.ikyou.domain.usercenter.dao.SysMenuMapper;
import cn.ikyou.domain.usercenter.dto.MenuDTO;
import cn.ikyou.domain.usercenter.entity.SysMenu;
import cn.ikyou.domain.usercenter.entity.SysMenuApi;
import cn.ikyou.domain.usercenter.service.SysMenuDomainService;
import cn.ikyou.infrastructure.execption.ServiceCheckException;
import cn.ikyou.infrastructure.util.UserInfoUtil;
import cn.ikyou.interfaces.base.vo.menu.MenuAddReqVO;
import cn.ikyou.interfaces.base.vo.menu.MenuApiAddVO;
import cn.ikyou.interfaces.base.vo.menu.MenuVO;
import cn.ikyou.interfaces.base.vo.menu.req.MenuQueryVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;


@Service
@AllArgsConstructor
public class SysMenuDomainServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuDomainService {

    private final SysMenuApiMapper menuApiMapper;

    @Override
    @Transactional
    public int menuApi(MenuApiAddVO menuApiAddVO) {
        SysMenu menu=baseMapper.selectById(menuApiAddVO.getMenuId());
        if(null==menu){
            throw new ServiceCheckException("未发现菜单信息");
        }
        if(CollectionUtils.isEmpty(menuApiAddVO.getApiId())){
            throw new ServiceCheckException("未发现匹配的API");
        }
        for(Integer apiId : menuApiAddVO.getApiId()){
            SysMenuApi menuApi=new SysMenuApi();
            menuApi.setApiId(apiId);
            menuApi.setMenuId(menu.getMenuId());
            int len=menuApiMapper.insert(menuApi);
            if(len<=0){
                throw new ServiceCheckException("关联API失败");
            }
        }
        return 1;
    }

    @Override
    public int update(MenuVO menuVO) {
        SysMenu menu=new SysMenu();
        BeanUtils.copyProperties(menuVO,menu);
        menu.setUpdateBy(UserInfoUtil.getCurrentUser().getUsername());
        menu.setUpdateTime(new Date());
        return this.baseMapper.updateById(menu);
    }


    @Override
    public IPage<SysMenu> searchPage(MenuQueryVO query) {
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(query.getMenuName())){
            queryWrapper.eq("menu_name",query.getMenuName());
        }
        if(null!=query.getParentId()){
            queryWrapper.eq("parent_id",query.getParentId());
        }
        if(null!=query.getAppId()){
            queryWrapper.eq("app_id",query.getAppId());
        }
        //构建分页查询
        Page<SysMenu> page = new Page<>(query.getPage(), query.getNum());
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public int delete(Integer menuId) {
        return this.baseMapper.deleteById(menuId);
    }

    @Override
    public SysMenu detail(Integer menuId) {
        return baseMapper.selectById(menuId);
    }

    @Override
    public int add(MenuAddReqVO menuVO) {
        SysMenu menu=new SysMenu();
        BeanUtils.copyProperties(menuVO,menu);
        menu.setCreateBy(UserInfoUtil.getCurrentUser().getUsername());
        menu.setCreateTime(new Date());
        return baseMapper.insert(menu);
    }

    @Override
    public List<MenuDTO> selectMenuByRoleIds(List<Integer> roleIds) {
        return baseMapper.selectMenuByRoleIds(roleIds);
    }


}
