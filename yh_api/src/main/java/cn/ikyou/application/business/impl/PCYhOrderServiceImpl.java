package cn.ikyou.application.business.impl;

import cn.ikyou.application.business.PCYhOrderService;
import cn.ikyou.application.dto.UserInfoDTO;
import cn.ikyou.domain.business.entity.TMaterial;
import cn.ikyou.domain.business.entity.TWkMaterial;
import cn.ikyou.domain.business.entity.TWkMoreinfo;
import cn.ikyou.domain.business.entity.TWkOrders;
import cn.ikyou.domain.business.service.TWkMaterialDomainService;
import cn.ikyou.domain.business.service.TWkMoreinfoDomainService;
import cn.ikyou.domain.business.service.TWkOrdersDomainService;
import cn.ikyou.domain.usercenter.entity.Organization;
import cn.ikyou.domain.usercenter.entity.SysUser;
import cn.ikyou.domain.usercenter.service.OrganizationDomainService;
import cn.ikyou.domain.usercenter.service.SysUserDomainService;
import cn.ikyou.infrastructure.enums.OrderStatusEnum;
import cn.ikyou.infrastructure.execption.ServiceCheckException;
import cn.ikyou.infrastructure.util.UserInfoUtil;
import cn.ikyou.interfaces.pc.vo.meterial.PCMeterialResVO;
import cn.ikyou.interfaces.pc.vo.orders.*;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 开发人：刘振华
 * 开发时间：2022/1/23
 * 说明：
 */
@Slf4j
@Service
@AllArgsConstructor
public class PCYhOrderServiceImpl implements PCYhOrderService {

    private final TWkOrdersDomainService tWkOrdersDomainService;
    private final TWkMoreinfoDomainService tWkMoreinfoDomainService;
    private final TWkMaterialDomainService tWkMaterialDomainService;
    private final SysUserDomainService sysUserDomainService;
    private final OrganizationDomainService organizationDomainService;

    @Override
    public IPage<PCOrdersResVO> selectPage(PCOrdersReqVO reqVO) {
        //构建分页查询
        IPage<TWkOrders> page = new Page<>(reqVO.getPage(), reqVO.getNum());
        UserInfoDTO currentUser = UserInfoUtil.getCurrentUser();
        QueryWrapper<TWkOrders> queryWrapper = new QueryWrapper();
        queryWrapper.ge(!StringUtils.isEmpty(reqVO.getStartTime()),"start_time",reqVO.getStartTime());
        queryWrapper.le(!StringUtils.isEmpty(reqVO.getEndTime()),"end_time",reqVO.getEndTime());
        queryWrapper.like(!StringUtils.isEmpty(reqVO.getWorkItem()),"work_item",reqVO.getWorkItem());
        queryWrapper.eq(!StringUtils.isEmpty(reqVO.getItemOne()),"item_one",reqVO.getItemOne());
        if(!StringUtils.isEmpty(reqVO.getKeys())){
            queryWrapper.like("date",reqVO.getKeys())
            .or().like("work_item",reqVO.getKeys())
            .or().like("work_gs",reqVO.getKeys())
            .or().like("work_fx",reqVO.getKeys())
            .or().like("work_loation_start",reqVO.getKeys())
            .or().like("work_locaton_end",reqVO.getKeys())
            .or().like("work_carlist",reqVO.getKeys())
            .or().like("work_machinelist",reqVO.getKeys())
            .or().like("work_user_list",reqVO.getKeys())
            .or().like("work_user_other",reqVO.getKeys())
            .or().like("remarks",reqVO.getKeys())
            .or().like("work_leader_name",reqVO.getKeys())
            .or().like("start_time",reqVO.getKeys())
            .or().like("end_time",reqVO.getKeys())
            .or().like("weather",reqVO.getKeys())
            .or().like("temperature",reqVO.getKeys())
            .or().like("status",reqVO.getKeys())
            .or().like("item_one",reqVO.getKeys());
        }
        queryWrapper.and(wrapper->wrapper.eq("org_id",currentUser.getOrgId()).or().inSql("org_id","select o.id from organization o where o.is_del = 0 and o.pid="+currentUser.getOrgId()));
        if("DESC".equals(reqVO.getSort())){
            queryWrapper.orderByDesc("start_time");
        }else if("ASC".equals(reqVO.getSort())){
            queryWrapper.orderByAsc("start_time");
        }
        IPage<TWkOrders> tWkOrdersIPage = tWkOrdersDomainService.getBaseMapper().selectPage(page, queryWrapper);

        List<PCOrdersResVO> list = new ArrayList<>();
        for (TWkOrders record : tWkOrdersIPage.getRecords()) {
            PCOrdersResVO resVO = new PCOrdersResVO();
            BeanUtils.copyProperties(record,resVO);
            Organization org = organizationDomainService.getById(record.getOrgId());
            resVO.setOrgName(org.getOrgName());
            list.add(resVO);
        }
        IPage<PCOrdersResVO> resVOIPage = new Page<>(reqVO.getPage(), reqVO.getNum());
        resVOIPage.setRecords(list);
        resVOIPage.setTotal(page.getTotal());
        return resVOIPage;
    }

    @Override
    public PCOrderDetailsResVO details(Integer wkId) {
        PCOrderDetailsResVO resVO = new PCOrderDetailsResVO();
        List<PCTWkMaterialResVO> materialResVOList = new ArrayList<>();
        List<PCTWkMoreinfoResVO> moreinfoResVOList = new ArrayList<>();

        TWkOrders orders = tWkOrdersDomainService.getById(wkId);
        SysUser user = sysUserDomainService.selectOneById(orders.getCreateId());
        if(null == user){
            throw new ServiceCheckException("创建工单用户不存在");
        }
        QueryWrapper<TWkMaterial> queryWrapper = new QueryWrapper();
        queryWrapper.eq("wk_id",wkId);
        List<TWkMaterial> tWkMaterials = tWkMaterialDomainService.getBaseMapper().selectList(queryWrapper);
        QueryWrapper<TWkMoreinfo> query = new QueryWrapper();
        query.eq("wk_id",wkId);
        List<TWkMoreinfo> tWkMoreinfos = tWkMoreinfoDomainService.getBaseMapper().selectList(query);
        for (TWkMaterial tWkMaterial : tWkMaterials) {
            PCTWkMaterialResVO res = new PCTWkMaterialResVO();
            BeanUtils.copyProperties(tWkMaterial,res);
            materialResVOList.add(res);
        }
        for (TWkMoreinfo tWkMoreinfo : tWkMoreinfos) {
            PCTWkMoreinfoResVO moreinfoResVO = new PCTWkMoreinfoResVO();

            StringBuffer str = new StringBuffer();
            str.append("【").append(orders.getWorkGs()).append("-").append(orders.getWorkFx()).append("】")
                    .append(" ").append(orders.getWorkLoationStart()).append("->").append(orders.getWorkLocatonEnd());

            BeanUtils.copyProperties(tWkMoreinfo,moreinfoResVO);
            moreinfoResVO.setLocationGroup(str.toString());
            moreinfoResVOList.add(moreinfoResVO);
        }
        BeanUtils.copyProperties(orders,resVO);
        resVO.setName(user.getName());
        resVO.setMoreinfoResVOList(moreinfoResVOList);
        resVO.setMaterialResVOList(materialResVOList);
        return resVO;
    }

    @Override
    public List<TWkOrders> selectList(PCOrdersReqVO reqVO) {
        //构建分页查询
        UserInfoDTO currentUser = UserInfoUtil.getCurrentUser();
        QueryWrapper<TWkOrders> queryWrapper = new QueryWrapper();
        queryWrapper.ge(!StringUtils.isEmpty(reqVO.getStartTime()),"start_time",reqVO.getStartTime());
        queryWrapper.le(!StringUtils.isEmpty(reqVO.getEndTime()),"end_time",reqVO.getEndTime());
        queryWrapper.like(!StringUtils.isEmpty(reqVO.getWorkItem()),"work_item",reqVO.getWorkItem());
        if(!StringUtils.isEmpty(reqVO.getKeys())){
            queryWrapper.like("date",reqVO.getKeys())
                    .or().like("work_item",reqVO.getKeys())
                    .or().like("work_gs",reqVO.getKeys())
                    .or().like("work_fx",reqVO.getKeys())
                    .or().like("work_loation_start",reqVO.getKeys())
                    .or().like("work_locaton_end",reqVO.getKeys())
                    .or().like("work_carlist",reqVO.getKeys())
                    .or().like("work_machinelist",reqVO.getKeys())
                    .or().like("work_user_list",reqVO.getKeys())
                    .or().like("work_user_other",reqVO.getKeys())
                    .or().like("remarks",reqVO.getKeys())
                    .or().like("work_leader_name",reqVO.getKeys())
                    .or().like("start_time",reqVO.getKeys())
                    .or().like("end_time",reqVO.getKeys())
                    .or().like("weather",reqVO.getKeys())
                    .or().like("temperature",reqVO.getKeys())
                    .or().like("status",reqVO.getKeys());
        }
        queryWrapper.and(wrapper->wrapper.eq("org_id",currentUser.getOrgId()).or().inSql("org_id","select o.id from organization o where o.is_del = 0 and o.pid="+currentUser.getOrgId()));

        if("DESC".equals(reqVO.getSort())){
            queryWrapper.orderByDesc("start_time");
        }else if("ASC".equals(reqVO.getSort())){
            queryWrapper.orderByAsc("start_time");
        }
        List<TWkOrders> tWkOrdersIPage = tWkOrdersDomainService.getBaseMapper().selectList(queryWrapper);

    return tWkOrdersIPage;
    }

    @Override
    public List<Organization> orgList() {
        List<Organization> list = organizationDomainService.orgList();
        return list;
    }

    @Override
    public boolean del(Integer id) {
        TWkOrders orders = tWkOrdersDomainService.getById(id);
        if(OrderStatusEnum.COMPLETE.getStatus().equals(orders.getStatus())){
            throw new ServiceCheckException("已经归档工单不能删除");
        }
        QueryWrapper<TWkMaterial> queryWrapper = new QueryWrapper();
        queryWrapper.eq("wk_id",id);
        tWkMaterialDomainService.getBaseMapper().delete(queryWrapper);
        QueryWrapper<TWkMoreinfo> query = new QueryWrapper();
        query.eq("wk_id",id);
        tWkMoreinfoDomainService.getBaseMapper().delete(query);

        return tWkOrdersDomainService.removeById(id);
    }

    @Override
    public boolean archive(Integer id) {
        UserInfoDTO currentUser = UserInfoUtil.getCurrentUser();
        TWkOrders orders = tWkOrdersDomainService.getById(id);
        if(null==orders){
            throw new ServiceCheckException("未发现工单信息");
        }
        if(null==orders.getStatus()){
            throw new ServiceCheckException("工单状态错误");
        }
        if(null!=orders.getStatus() && orders.getStatus().equals(OrderStatusEnum.COMPLETE.getStatus())){
            throw new ServiceCheckException("工单已归档");
        }
        orders.setStatus(OrderStatusEnum.COMPLETE.getStatus());
        orders.setUpdateId(currentUser.getUserId());
        orders.setUpdateTime(new Date());
        return tWkOrdersDomainService.updateById(orders);
}

    @Override
    public String report(Integer id) {
        PCOrderDetailsResVO details = this.details(id);
        String data = JSON.toJSONString(details);

        return "";
    }

}
