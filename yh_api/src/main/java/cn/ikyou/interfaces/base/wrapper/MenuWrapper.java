package cn.ikyou.interfaces.base.wrapper;

import cn.ikyou.domain.usercenter.entity.SysMenu;
import cn.ikyou.interfaces.base.vo.menu.MenuVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MenuWrapper {

    public List<MenuVO> listVO(List<SysMenu> list) {
        List<MenuVO> result=new ArrayList<MenuVO>();
        for(SysMenu menu : list){
            MenuVO vo=new MenuVO();
            BeanUtils.copyProperties(menu,vo);
            result.add(vo);
        }
        return result;
    }

    public MenuVO entityVO(SysMenu menu) {
        MenuVO vo=new MenuVO();
        BeanUtils.copyProperties(menu,vo);
        return vo;
    }
}
