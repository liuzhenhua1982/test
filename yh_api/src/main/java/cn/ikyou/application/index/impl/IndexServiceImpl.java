package cn.ikyou.application.index.impl;

import cn.ikyou.application.dto.UserInfoDTO;
import cn.ikyou.application.index.IndexService;
import cn.ikyou.domain.usercenter.dto.RoleDTO;
import cn.ikyou.domain.usercenter.dto.UserDTO;
import cn.ikyou.domain.usercenter.entity.Organization;
import cn.ikyou.domain.usercenter.entity.SysUser;
import cn.ikyou.domain.usercenter.service.OrganizationDomainService;
import cn.ikyou.domain.usercenter.service.SysMenuDomainService;
import cn.ikyou.domain.usercenter.service.SysRoleDomainService;
import cn.ikyou.domain.usercenter.service.SysUserDomainService;
import cn.ikyou.infrastructure.common.HttpUrls;
import cn.ikyou.infrastructure.execption.ServiceCheckException;
import cn.ikyou.infrastructure.util.OkHttpUtil;
import cn.ikyou.infrastructure.util.UserInfoUtil;
import cn.ikyou.interfaces.app.vo.WeatherResVO;
import cn.ikyou.interfaces.base.vo.user.PasswordVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class IndexServiceImpl implements IndexService {

    private final SysUserDomainService sysUserDomainService;
    private final SysMenuDomainService sysMenuDomainService;
    private final SysRoleDomainService sysRoleDomainService;
    private final OrganizationDomainService organizationDomainService;
    private final PasswordEncoder passwordEncoder;


    @Override
    public String cityByOrgId(Integer orgId) {
        Organization organization=organizationDomainService.getById(orgId);
        if(null!=organization){
            return organization.getCity();
        }
        return null;
    }

    @Override
    public UserDTO userInfo() {
        UserInfoDTO userInfoDTO=UserInfoUtil.getCurrentUser();
        if(null==userInfoDTO){
            throw new ServiceCheckException("未发现登录用户！");
        }
        SysUser user=sysUserDomainService.getById(userInfoDTO.getUserId());
        if(null==user){
            throw new ServiceCheckException("用户不存在！");
        }
        UserDTO userDTO=new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        //获取角色
        List<RoleDTO> roles=sysRoleDomainService.selectRoleByUserId(userDTO.getUserId());
        //
        if(!CollectionUtils.isEmpty(roles)){
            userDTO.setRoles(roles);
            //角色ID
            List<Integer> roleIds=new ArrayList<>();
            for(RoleDTO roleDTO : roles){
                roleIds.add(roleDTO.getRoleId());
            }
            userDTO.setMenus(sysMenuDomainService.selectMenuByRoleIds(roleIds));
        }
        return userDTO;
    }

    @Override
    public WeatherResVO weather(String city) throws IOException {
        String respons = OkHttpUtil.get(HttpUrls.WEATHER_URL+"?city="+city);
        WeatherResVO resVO=getXmlAttribute(respons);
        return resVO;
    }


    /**
     * dom4j解析Xml
     * @param xml
     */
    public WeatherResVO getXmlAttribute(String xml) {
        WeatherResVO resVO = new WeatherResVO();
        Document doc = null;
        try {
            // 将字符串转为XML
            doc = DocumentHelper.parseText(xml);
            // 获取根节点
            Element rootElt = doc.getRootElement();
            //获取forecast节点
            Iterator forecast = rootElt.elementIterator("forecast");
                Element recordEle = (Element) forecast.next();
                //获取weather节点
                Iterator weather = recordEle.elementIterator("weather");
                    Element record = (Element) weather.next();
                    //获取温度数据
                    String high = record.elementText("high").split(" ")[1];
                    String low = record.elementText("low").split(" ")[1];
                    resVO.setTemperature(low+" ~ "+high);
                    //设置日期
                    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                    resVO.setDate(f.format(new Date()));
                    //判断白天晚上
                    Date date = new Date();
                    SimpleDateFormat df = new SimpleDateFormat("HH");
                    String str = df.format(date);
                    int a = Integer.parseInt(str);
                    String dayflag ="";
                    if (a > 18 && a <= 24) {
                        dayflag = "night";
                    }else{
                        dayflag = "day";

                    }
                    Iterator day = record.elementIterator(dayflag);
                    Element data = (Element) day.next();
                    //获取气象数据
                    String type = data.elementText("type");
                    //String fengxiang = data.elementText("fengxiang");
                    //String fengli = data.elementText("fengli");
                    resVO.setWeather(type);

        } catch (Exception e) {
           log.error(ExceptionUtils.getMessage(e));
           throw new ServiceCheckException("天气数据获取失败");
        }
        return resVO;
    }

    @Override
    public int password(PasswordVO password) {
        if(null==password){
            throw new ServiceCheckException("信息不能未空");
        }
        if(StringUtils.isEmpty(password.getOldPassword())){
            throw new ServiceCheckException("原始密码不能为空");
        }
        if(StringUtils.isEmpty(password.getPassword()) || StringUtils.isEmpty(password.getPassword1())){
            throw new ServiceCheckException("密码或确认密码不能为空");
        }
        if(!password.getPassword().equals(password.getPassword1())){
            throw new ServiceCheckException("两次密码不一致");
        }
        UserInfoDTO userDTO= UserInfoUtil.getCurrentUser();
        if(null==userDTO){
            throw new ServiceCheckException("未发现用户信息");
        }
        //检查密码是否一致
        boolean flag = passwordEncoder.matches(password.getOldPassword(),userDTO.getPassword());
        if(!flag){
            throw new ServiceCheckException("原始密码不正确");
        }
        SysUser user=sysUserDomainService.getById(userDTO.getUserId());
        if(null==user){
            throw new ServiceCheckException("用户不存在");
        }
        user.setPassword(passwordEncoder.encode(password.getPassword()));
        if(sysUserDomainService.updateById(user)){
            return 1;
        }
        return 0;
    }

}
