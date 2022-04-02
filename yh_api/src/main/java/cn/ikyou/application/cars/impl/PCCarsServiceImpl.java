package cn.ikyou.application.cars.impl;

import cn.ikyou.application.cars.PCCarsService;
import cn.ikyou.application.dto.UserInfoDTO;
import cn.ikyou.domain.business.entity.TCars;
import cn.ikyou.domain.business.service.TCarsDomainService;
import cn.ikyou.domain.dictionary.entity.Dictionary;
import cn.ikyou.domain.usercenter.entity.Organization;
import cn.ikyou.domain.usercenter.service.OrganizationDomainService;
import cn.ikyou.infrastructure.util.UserInfoUtil;
import cn.ikyou.interfaces.pc.vo.car.PCCarsAddReqVO;
import cn.ikyou.interfaces.pc.vo.car.PCCarsReqVO;
import cn.ikyou.interfaces.pc.vo.car.PCCarsResVO;
import cn.ikyou.interfaces.pc.vo.car.PCCarsUpdateReqVO;
import cn.ikyou.interfaces.pc.vo.dict.PCDictResVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class PCCarsServiceImpl implements PCCarsService {

    private final TCarsDomainService tCarsDomainService;
    private final OrganizationDomainService organizationDomainService;
    @Override
    public IPage<PCCarsResVO> searchPage(PCCarsReqVO reqVO) {
        IPage<TCars> page = new Page<>(reqVO.getPage(), reqVO.getNum());
        UserInfoDTO currentUser = UserInfoUtil.getCurrentUser();
        QueryWrapper<TCars> queryWrapper = new QueryWrapper();
        if(null!=reqVO.getOrgId() && reqVO.getOrgId()!=0){
            queryWrapper.and(wrapper->wrapper.eq(!StringUtils.isEmpty(reqVO.getOrgId()),"org_id",reqVO.getOrgId()).or().inSql(!StringUtils.isEmpty(reqVO.getOrgId()),"org_id","select o.id from organization o where o.is_del = 0 and o.pid="+reqVO.getOrgId()));
        }
        if(!StringUtils.isEmpty(reqVO.getKeys())){
            queryWrapper.like("business_brand",reqVO.getKeys()).or().like("license_car",reqVO.getKeys());

        }
        queryWrapper.orderByAsc("id");
        List<PCCarsResVO> list = new ArrayList<>();
        IPage<TCars> tCarsIPage = tCarsDomainService.getBaseMapper().selectPage(page, queryWrapper);
        for (TCars record : tCarsIPage.getRecords()) {
            PCCarsResVO resVO = new PCCarsResVO();
            BeanUtils.copyProperties(record,resVO);
            if(null!=record.getOrgId()){
                Organization org = organizationDomainService.getById(record.getOrgId());
                resVO.setOrgName(org.getOrgName());
            }
            list.add(resVO);
        }
        IPage<PCCarsResVO> resVOIPage = new Page<>(reqVO.getPage(), reqVO.getNum());
        resVOIPage.setRecords(list);
        resVOIPage.setTotal(page.getTotal());
        return resVOIPage;
    }

    @Override
    public boolean add(PCCarsAddReqVO addReqVO) {
        TCars cars = new TCars();
        BeanUtils.copyProperties(addReqVO,cars);
        UserInfoDTO currentUser = UserInfoUtil.getCurrentUser();
        cars.setCreateTime(new Date());
        cars.setCreateId(currentUser.getUserId());
        return tCarsDomainService.save(cars);
    }

    @Override
    public boolean update(PCCarsUpdateReqVO updateReqVO) {
        TCars cars = new TCars();
        BeanUtils.copyProperties(updateReqVO,cars);
        UserInfoDTO currentUser = UserInfoUtil.getCurrentUser();
        cars.setUpdateId(currentUser.getUserId());
        cars.setUpdateTime(new Date());
        return tCarsDomainService.updateById(cars);
    }

    @Override
    public boolean del(Integer id) {
        return tCarsDomainService.removeById(id);
    }

    @Override
    public TCars details(Integer id) {
        return tCarsDomainService.getById(id);
    }
}
