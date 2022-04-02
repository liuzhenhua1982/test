package cn.ikyou.application.business.impl;

import ch.qos.logback.classic.jmx.MBeanUtil;
import cn.ikyou.application.business.AppYhOrderService;
import cn.ikyou.application.dto.UserInfoDTO;
import cn.ikyou.domain.business.dao.TWkOrdersMapper;
import cn.ikyou.domain.business.entity.*;
import cn.ikyou.domain.business.service.*;
import cn.ikyou.domain.usercenter.dao.SysUserMapper;
import cn.ikyou.domain.usercenter.entity.Organization;
import cn.ikyou.domain.usercenter.entity.SysUser;
import cn.ikyou.domain.usercenter.service.OrganizationDomainService;
import cn.ikyou.domain.usercenter.service.SysUserDomainService;
import cn.ikyou.infrastructure.enums.OrderStatusEnum;
import cn.ikyou.infrastructure.execption.ServiceCheckException;
import cn.ikyou.infrastructure.util.UserInfoUtil;
import cn.ikyou.interfaces.app.vo.yhorder.*;

import cn.ikyou.interfaces.pc.vo.meterial.PCMeterialResVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 开发人：刘振华
 * 开发时间：2022/1/22
 * 说明：
 */
@Slf4j
@Service
@AllArgsConstructor
public class AppYhOrderServiceImpl extends ServiceImpl<TWkOrdersMapper, TWkOrders> implements AppYhOrderService {

    private final TWkOrdersDomainService tWkOrdersDomainService;
    private final TWkMoreinfoDomainService tWkMoreinfoDomainService;
    private final TWkMaterialDomainService tWkMaterialDomainService;
    private final SysUserDomainService sysUserDomainService;
    private final TCarsDomainService tCarsDomainService;
    private final OrganizationDomainService organizationDomainService;
    private final TMaterialDomainService materialDomainService;

    @Override
    public YhOrderResVO save(YhOrderReqVO reqVO) {
        UserInfoDTO currentUser = UserInfoUtil.getCurrentUser();
        TWkOrders orders = new TWkOrders();
        BeanUtils.copyProperties(reqVO,orders);
        orders.setCreateId(currentUser.getUserId());
        orders.setCreateTime(new Date());
        orders.setVersion(0);
        orders.setOrgId(currentUser.getOrgId());
        orders.setOrgName(currentUser.getOrgName());
        orders.setStatus(OrderStatusEnum.EXECUTE.getStatus());
        boolean re = tWkOrdersDomainService.save(orders);
        if(!re){
            throw new ServiceCheckException("创建失败");
        }
        YhOrderResVO res = new YhOrderResVO();
        BeanUtils.copyProperties(orders,res);
        return res;
    }

    @Override
    public Integer saveDetails(ZyDetailReqVO detailReqVO) {
        UserInfoDTO currentUser = UserInfoUtil.getCurrentUser();
        TWkMoreinfo info = new TWkMoreinfo();
        BeanUtils.copyProperties(detailReqVO,info);
        info.setCreateId(currentUser.getUserId());
        info.setCreateTime(new Date());
        info.setVersion(0);
        boolean save = tWkMoreinfoDomainService.save(info);
        if(!save){
            throw new ServiceCheckException("创建失败");
        }
        return info.getId();
    }

    @Override
    public Integer saveWL(WlReqVO wlReqVO) {
        UserInfoDTO currentUser = UserInfoUtil.getCurrentUser();
        TWkMaterial info = new TWkMaterial();
        BeanUtils.copyProperties(wlReqVO,info);
        info.setCreateId(currentUser.getUserId());
        info.setCreateTime(new Date());
        info.setVersion(0);
        boolean save = tWkMaterialDomainService.save(info);
        if(!save){
            throw new ServiceCheckException("创建失败");
        }
        return info.getId();
    }

    @Override
    public List<UserResVO> userList() {
        UserInfoDTO currentUser = UserInfoUtil.getCurrentUser();
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>();
        queryWrapper.eq("org_id",currentUser.getOrgId());
        List<SysUser> list = sysUserDomainService.list(queryWrapper);
        List<UserResVO> resList = new ArrayList<>();
        for (SysUser sysUser : list) {
            UserResVO res = new UserResVO();
            BeanUtils.copyProperties(sysUser,res);
            resList.add(res);
        }
        return resList;
    }

    @Override
    public List<CarResVO> carList() {
        UserInfoDTO currentUser = UserInfoUtil.getCurrentUser();
        QueryWrapper<TCars> queryWrapper = new QueryWrapper<TCars>();
        queryWrapper.eq("org_id",currentUser.getOrgId());
        List<TCars> list = tCarsDomainService.list(queryWrapper);
        List<CarResVO> resList = new ArrayList<>();
        for (TCars tCars : list) {
            CarResVO resVO = new CarResVO();
            BeanUtils.copyProperties(tCars,resVO);
            resList.add(resVO);
        }
        return resList;
    }

    @Override
    public IPage<YhOrderResVO> selectPage(YhOrderQueryReqVO yhOrderQueryReqVO) {

        //构建分页查询
        IPage<TWkOrders> page = new Page<>(yhOrderQueryReqVO.getPage(), yhOrderQueryReqVO.getNum());
        UserInfoDTO currentUser = UserInfoUtil.getCurrentUser();
        QueryWrapper<TWkOrders> queryWrapper = new QueryWrapper();
        queryWrapper.ge(!StringUtils.isEmpty(yhOrderQueryReqVO.getStartTime()),"start_time",yhOrderQueryReqVO.getStartTime());
        queryWrapper.le(!StringUtils.isEmpty(yhOrderQueryReqVO.getEndTime()),"end_time",yhOrderQueryReqVO.getEndTime());
        queryWrapper.eq(!StringUtils.isEmpty(yhOrderQueryReqVO.getStatus()),"status",yhOrderQueryReqVO.getStatus());
        queryWrapper.and(wrapper->wrapper.eq("org_id",currentUser.getOrgId()).or().inSql("org_id","select o.id from organization o where o.is_del = 0 and o.pid="+currentUser.getOrgId()));
        queryWrapper.orderByDesc("create_time");
        IPage<TWkOrders> tWkOrdersIPage = tWkOrdersDomainService.getBaseMapper().selectPage(page, queryWrapper);
        List<YhOrderResVO> list = new ArrayList<>();
        for (TWkOrders record : tWkOrdersIPage.getRecords()) {
            YhOrderResVO resVO = new YhOrderResVO();
            BeanUtils.copyProperties(record,resVO);
            Organization org = organizationDomainService.getById(record.getOrgId());
            resVO.setOrgName(org.getOrgName());

            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            try {
                if(!StringUtils.isEmpty(resVO.getDate())){
                    resVO.setDate(f.format(f.parse(resVO.getDate())));
                }
                if(!StringUtils.isEmpty(resVO.getStartTime())){
                    resVO.setStartTime(f.format(f.parse(resVO.getStartTime())));
                }
                if(!StringUtils.isEmpty(resVO.getEndTime())){
                    resVO.setEndTime(f.format(f.parse(resVO.getEndTime())));
                }

            } catch (ParseException e) {
                log.error(ExceptionUtils.getMessage(e));
            }
            list.add(resVO);
        }
        IPage<YhOrderResVO> resVOIPage = new Page<>(yhOrderQueryReqVO.getPage(), yhOrderQueryReqVO.getNum());
        resVOIPage.setRecords(list);
        resVOIPage.setTotal(page.getTotal());
        return resVOIPage;
    }

    @Override
    public YhOrderResVO orderDetails(Integer wkId) {
        YhOrderResVO resVO = new YhOrderResVO();
        TWkOrders order = tWkOrdersDomainService.getById(wkId);
        if(null == order){
            throw new ServiceCheckException("未发现工单信息");
        }

        SysUser user = sysUserDomainService.selectOneById(order.getCreateId());
        if(null==user){
            throw new ServiceCheckException("未发现创建工单用户信息");
        }
        BeanUtils.copyProperties(order,resVO);
        resVO.setName(user.getName());
        return resVO;
    }

    @Override
    public int zyDel(Integer id) {
        return  tWkMoreinfoDomainService.getBaseMapper().deleteById(id);
    }

    @Override
    public List<ZyDetailResVO> zyDetailList(Integer wkId,Integer zyType) {
        List<ZyDetailResVO> resVOList = new ArrayList<>();
        QueryWrapper<TWkMoreinfo> queryWrapper = new QueryWrapper();
        queryWrapper.eq("wk_id",wkId);
        queryWrapper.eq("zy_type",zyType);
        List<TWkMoreinfo> tWkMoreinfos = tWkMoreinfoDomainService.getBaseMapper().selectList(queryWrapper);
        for (TWkMoreinfo tWkMoreinfo : tWkMoreinfos) {
            ZyDetailResVO resVO = new ZyDetailResVO();
            BeanUtils.copyProperties(tWkMoreinfo,resVO);
            resVOList.add(resVO);
        }
        return resVOList;
    }

    @Override
    public int wlDel(Integer id) {

        return tWkMaterialDomainService.getBaseMapper().deleteById(id);
    }

    @Override
    public List<WlResVO> wlDetailList(Integer wkId) {
        List<WlResVO> resVOList = new ArrayList<>();
        QueryWrapper<TWkMaterial> queryWrapper = new QueryWrapper();
        queryWrapper.eq("wk_id",wkId);
        List<TWkMaterial> tWkMaterials = tWkMaterialDomainService.getBaseMapper().selectList(queryWrapper);
        for (TWkMaterial tWkMaterial : tWkMaterials) {
            WlResVO resVO = new WlResVO();
            BeanUtils.copyProperties(tWkMaterial,resVO);
            resVOList.add(resVO);
        }
        return resVOList;
    }

    @Override
    public List<MaterialResVO> meterialList(MaterialReqVO materialReqVO) {
        List<MaterialResVO> result=new ArrayList<>();
        QueryWrapper<TMaterial> materialQueryWrapper=new QueryWrapper<>();
        if(!StringUtils.isEmpty(materialReqVO.getName())){
            materialQueryWrapper.like("name",materialReqVO.getName());
        }
        materialQueryWrapper.eq("org_id",UserInfoUtil.getCurrentUser().getOrgId());
        List<TMaterial> list=materialDomainService.list(materialQueryWrapper);
        if(!CollectionUtils.isEmpty(list)){
            for(TMaterial material : list){
                MaterialResVO materialResVO=new MaterialResVO();
                BeanUtils.copyProperties(material,materialResVO);
                result.add(materialResVO);
            }
        }
        return result;
    }

    @Override
    public boolean update(YhOrderUpdateReqVO updateReqVO) {
        UserInfoDTO currentUser = UserInfoUtil.getCurrentUser();
        TWkOrders orders = new TWkOrders();
        BeanUtils.copyProperties(updateReqVO,orders);
        orders.setUpdateId(currentUser.getUserId());
        orders.setUpdateTime(new Date());

        return tWkOrdersDomainService.updateById(orders);
    }
}
