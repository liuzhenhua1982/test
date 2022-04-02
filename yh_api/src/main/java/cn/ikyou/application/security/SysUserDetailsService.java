package cn.ikyou.application.security;

import cn.ikyou.application.dto.UserInfoDTO;
import cn.ikyou.domain.usercenter.entity.Organization;
import cn.ikyou.domain.usercenter.entity.SysUser;
import cn.ikyou.domain.usercenter.service.OrganizationDomainService;
import cn.ikyou.domain.usercenter.service.SysUserDomainService;
import cn.ikyou.infrastructure.request.LoginInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
@AllArgsConstructor
public class SysUserDetailsService implements UserDetailsService {

	private final SysUserDomainService sysUserDomainService;
	private final OrganizationDomainService organizationDomainService;


	//通过用户名加载
	public UserDetails loadUserByUsername(LoginInfo loginInfo) throws Exception {
		log.info("查询用户信息");
		//根据用户名获取
		QueryWrapper<SysUser> userQueryWrapper=new QueryWrapper<>();
		userQueryWrapper.eq("username",loginInfo.getUsername());
		SysUser user = sysUserDomainService.getOne(userQueryWrapper);
		//检查是否成功获取
		if(null==user) {
			return null;
		}
		//生成权限
		Set<SimpleGrantedAuthority> authoritiesSet = new HashSet<SimpleGrantedAuthority>();
		UserInfoDTO userInfoDTO=new UserInfoDTO();
		BeanUtils.copyProperties(user,userInfoDTO);
		//获取机构名称
		Organization organization=organizationDomainService.getById(userInfoDTO.getOrgId());
		if(null!=organization){
			userInfoDTO.setOrgName(organization.getOrgName());
		}
		//返回信息
		return new UserDetails(userInfoDTO,authoritiesSet);
    }


	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		return null;
	}


}
