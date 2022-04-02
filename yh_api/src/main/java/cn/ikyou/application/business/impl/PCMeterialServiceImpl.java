package cn.ikyou.application.business.impl;

import cn.ikyou.application.business.PCMeterialService;
import cn.ikyou.application.dto.UserInfoDTO;
import cn.ikyou.domain.business.entity.TCars;
import cn.ikyou.domain.business.entity.TMaterial;
import cn.ikyou.domain.business.service.TMaterialDomainService;
import cn.ikyou.domain.usercenter.entity.Organization;
import cn.ikyou.domain.usercenter.service.OrganizationDomainService;
import cn.ikyou.infrastructure.util.UserInfoUtil;
import cn.ikyou.interfaces.pc.vo.car.PCCarsResVO;
import cn.ikyou.interfaces.pc.vo.meterial.PCMeterialAddReqVO;
import cn.ikyou.interfaces.pc.vo.meterial.PCMeterialReqVO;
import cn.ikyou.interfaces.pc.vo.meterial.PCMeterialResVO;
import cn.ikyou.interfaces.pc.vo.meterial.PCMeterialUpdateReqVO;
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
public class PCMeterialServiceImpl implements PCMeterialService {

    private final TMaterialDomainService tMaterialDomainService;
    private final OrganizationDomainService organizationDomainService;
    @Override
    public IPage<PCMeterialResVO> searchPage(PCMeterialReqVO reqVO) {
        IPage<TMaterial> page = new Page<>(reqVO.getPage(), reqVO.getNum());
        UserInfoDTO currentUser = UserInfoUtil.getCurrentUser();
        QueryWrapper<TMaterial> queryWrapper = new QueryWrapper();
        if(!StringUtils.isEmpty(reqVO.getKeys())){
            queryWrapper.like("name",reqVO.getKeys())
            .or().like("amount",reqVO.getKeys())
            .or().like("operation_type",reqVO.getKeys())
            .or().like("model",reqVO.getKeys())
            .or().like("unit_name",reqVO.getKeys());
        }
        if(reqVO.getOrgId()!=0){
            queryWrapper.and(wrapper->wrapper.eq(!StringUtils.isEmpty(reqVO.getOrgId()),"org_id",reqVO.getOrgId()).or().inSql(!StringUtils.isEmpty(reqVO.getOrgId()),"org_id","select o.id from organization o where o.is_del = 0 and o.pid="+reqVO.getOrgId()));
        }
        queryWrapper.and(wrapper->wrapper.eq("org_id",currentUser.getOrgId()).or().inSql("org_id","select o.id from organization o where o.is_del = 0 and o.pid="+currentUser.getOrgId()));
        queryWrapper.orderByAsc("id");
        List<PCMeterialResVO> list = new ArrayList<>();
        IPage<TMaterial> pages = tMaterialDomainService.getBaseMapper().selectPage(page, queryWrapper);
        for (TMaterial record : pages.getRecords()) {
            PCMeterialResVO resVO = new PCMeterialResVO();
            BeanUtils.copyProperties(record,resVO);
            Organization org = organizationDomainService.getById(record.getOrgId());
            resVO.setOrgName(org.getOrgName());
            list.add(resVO);
        }
        IPage<PCMeterialResVO> resVOIPage = new Page<>(reqVO.getPage(), reqVO.getNum());
        resVOIPage.setRecords(list);
        resVOIPage.setTotal(page.getTotal());
        return resVOIPage;
    }

    @Override
    public boolean add(PCMeterialAddReqVO addReqVO) {
        TMaterial material = new TMaterial();
        BeanUtils.copyProperties(addReqVO,material);
        UserInfoDTO currentUser = UserInfoUtil.getCurrentUser();
        material.setCreateTime(new Date());
        material.setCreateId(currentUser.getUserId());
        return tMaterialDomainService.save(material);
    }

    @Override
    public boolean update(PCMeterialUpdateReqVO updateReqVO) {
        TMaterial material = new TMaterial();
        BeanUtils.copyProperties(updateReqVO,material);
        UserInfoDTO currentUser = UserInfoUtil.getCurrentUser();
        material.setUpdateId(currentUser.getUserId());
        material.setUpdateTime(new Date());
        return tMaterialDomainService.updateById(material);
    }

    @Override
    public boolean del(Integer id) {

        return tMaterialDomainService.removeById(id);
    }

    @Override
    public TMaterial details(Integer id) {
        return tMaterialDomainService.getById(id);
    }
}
