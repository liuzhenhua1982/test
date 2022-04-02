package cn.ikyou.application.usercenter;

import cn.ikyou.domain.usercenter.dto.RoleDTO;
import cn.ikyou.domain.usercenter.dto.UserDTO;
import cn.ikyou.domain.usercenter.entity.Organization;
import cn.ikyou.domain.usercenter.entity.SysUser;
import cn.ikyou.interfaces.base.vo.user.req.UserAddReqVO;
import cn.ikyou.interfaces.base.vo.user.req.UserQueryVO;
import cn.ikyou.interfaces.base.vo.user.req.UserUpdateReqVO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface UserService {

    List<RoleDTO> roleList();

    List<Organization> organiaztionTree();

    int add(UserAddReqVO userInfo);

    int update(UserUpdateReqVO userInfo);

    int delete(int userid);

    IPage<SysUser> searchPage(UserQueryVO query);

    UserDTO detail(Integer userid);

}
