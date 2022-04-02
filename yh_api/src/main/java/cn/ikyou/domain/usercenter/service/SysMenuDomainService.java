package cn.ikyou.domain.usercenter.service;

import cn.ikyou.domain.usercenter.dto.MenuDTO;
import cn.ikyou.domain.usercenter.entity.SysMenu;
import cn.ikyou.interfaces.base.vo.menu.MenuAddReqVO;
import cn.ikyou.interfaces.base.vo.menu.MenuApiAddVO;
import cn.ikyou.interfaces.base.vo.menu.MenuVO;
import cn.ikyou.interfaces.base.vo.menu.req.MenuQueryVO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface SysMenuDomainService {

    int update(MenuVO menuVO);

    IPage<SysMenu> searchPage(MenuQueryVO query);

    int delete(Integer menuId);

    SysMenu detail(Integer menuId);

    int add(MenuAddReqVO menuVO);

    List<MenuDTO> selectMenuByRoleIds(List<Integer> roleIds);

    int menuApi(MenuApiAddVO menuApiAddVO);

}
