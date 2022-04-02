package cn.ikyou.interfaces.base.wrapper;

import cn.ikyou.domain.usercenter.dto.RoleDTO;
import cn.ikyou.domain.usercenter.entity.SysMenu;
import cn.ikyou.domain.usercenter.entity.SysRole;
import cn.ikyou.interfaces.base.vo.menu.MenuVO;
import cn.ikyou.interfaces.base.vo.role.RoleVO;
import cn.ikyou.interfaces.base.vo.role.res.MenuTreeResVO;
import cn.ikyou.interfaces.base.vo.role.res.RootApp;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleWrapper {

    public List<RoleVO> pageVO(List<SysRole> list) {
        List<RoleVO> roleList=new ArrayList<RoleVO>();
        for(SysRole role : list){
            RoleVO vo=new RoleVO();
            BeanUtils.copyProperties(role,vo);
            roleList.add(vo);
        }
        return roleList;
    }

    public RoleVO roleToVO(RoleDTO role) {
        RoleVO vo=new RoleVO();
        BeanUtils.copyProperties(role,vo);
        List<MenuVO> menus=new ArrayList<MenuVO>();
        if(!CollectionUtils.isEmpty(role.getMenus())){
            for(SysMenu menu : role.getMenus()){
                MenuVO menuVO=new MenuVO();
                BeanUtils.copyProperties(menu,menuVO);
                menus.add(menuVO);
            }
        }
        vo.setMenus(menus);
        return vo;
    }

    /**
     * 获取功能菜单
     * @param list
     * @return
     */
    public List<MenuTreeResVO> menuVO(List<SysMenu> list) {
        List<MenuTreeResVO> result=new ArrayList<>();
        if(!CollectionUtils.isEmpty(list)){
            for(SysMenu menu : list){
                if(menu.getParentId()==0){
                    MenuTreeResVO menuTreeReqVO=new MenuTreeResVO();
                    menuTreeReqVO.setMenuId(menu.getMenuId());
                    menuTreeReqVO.setMenuName(menu.getMenuName());
                    //查找子菜单
                    searchChildren(menuTreeReqVO,list);
                    //加入结果集
                    result.add(menuTreeReqVO);
                }
            }
        }
        return result;
    }


    private void searchChildren(MenuTreeResVO menuTreeReqVO, List<SysMenu> list) {
        if(menuTreeReqVO==null){
            return;
        }
        if(!CollectionUtils.isEmpty(list)) {
            List<MenuTreeResVO> children=new ArrayList<>();
            for (SysMenu menu : list) {
                if(menu.getParentId()==menuTreeReqVO.getMenuId()){
                    MenuTreeResVO _menuTreeReqVO=new MenuTreeResVO();
                    _menuTreeReqVO.setMenuId(menu.getMenuId());
                    _menuTreeReqVO.setMenuName(menu.getMenuName());
                    //继续查询
                    searchChildren(_menuTreeReqVO,list);
                    //加入集合
                    children.add(_menuTreeReqVO);
                }
            }
            menuTreeReqVO.setChildren(children);
        }
    }


    public List<RootApp> menuVOTree(List<SysMenu> list) {
        List<RootApp> result=new ArrayList<>();
        RootApp app=new RootApp();
        app.setMenuName("APP应用");
        app.setMenuId(0);
        RootApp pc=new RootApp();
        pc.setMenuName("PC应用");
        pc.setMenuId(0);
        result.add(app);
        result.add(pc);
        //
        if(!CollectionUtils.isEmpty(list)){
            List<MenuTreeResVO> pcChildren=new ArrayList<>();
            List<MenuTreeResVO> appChildren=new ArrayList<>();
            for(SysMenu menu : list){
                if(menu.getParentId()==0 && menu.getAppId()==1){
                    MenuTreeResVO menuTreeReqVO=new MenuTreeResVO();
                    menuTreeReqVO.setMenuId(menu.getMenuId());
                    menuTreeReqVO.setMenuName(menu.getMenuName());
                    //查找子菜单
                    searchChildren(menuTreeReqVO,list);
                    //
                    pcChildren.add(menuTreeReqVO);
                }
            }
            for(SysMenu menu : list){
                if(menu.getParentId()==0 && menu.getAppId()==2){
                    MenuTreeResVO menuTreeReqVO=new MenuTreeResVO();
                    menuTreeReqVO.setMenuId(menu.getMenuId());
                    menuTreeReqVO.setMenuName(menu.getMenuName());
                    //查找子菜单
                    searchChildren(menuTreeReqVO,list);
                    //
                    appChildren.add(menuTreeReqVO);
                }
            }
            pc.setChildren(pcChildren);
            app.setChildren(appChildren);
        }
        return result;
    }
}
