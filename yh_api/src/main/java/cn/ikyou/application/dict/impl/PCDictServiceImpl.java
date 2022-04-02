package cn.ikyou.application.dict.impl;

import cn.ikyou.application.dict.PCDictService;
import cn.ikyou.application.dto.UserInfoDTO;
import cn.ikyou.domain.business.entity.TWkOrders;
import cn.ikyou.domain.dictionary.entity.Dictionary;
import cn.ikyou.domain.dictionary.service.DictionaryDomainService;
import cn.ikyou.domain.usercenter.entity.Organization;
import cn.ikyou.domain.usercenter.service.OrganizationDomainService;
import cn.ikyou.infrastructure.util.UserInfoUtil;
import cn.ikyou.interfaces.pc.vo.dict.PCDictAddReqVO;
import cn.ikyou.interfaces.pc.vo.dict.PCDictReqVO;
import cn.ikyou.interfaces.pc.vo.dict.PCDictResVO;
import cn.ikyou.interfaces.pc.vo.dict.PCDictUpdateReqVO;
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

@Slf4j
@Service
@AllArgsConstructor
public class PCDictServiceImpl implements PCDictService {

    private final DictionaryDomainService dictionaryDomainService;
    private final OrganizationDomainService organizationDomainService;
    @Override
    public IPage<PCDictResVO> searchPage(PCDictReqVO reqVO) {
        IPage<Dictionary> page = new Page<>(reqVO.getPage(), reqVO.getNum());
        UserInfoDTO currentUser = UserInfoUtil.getCurrentUser();
        QueryWrapper<Dictionary> queryWrapper = new QueryWrapper();
        queryWrapper.eq(!StringUtils.isEmpty(reqVO.getType()),"type",reqVO.getType());
        if(!StringUtils.isEmpty(reqVO.getKeys())){
            queryWrapper.like("type",reqVO.getKeys()).or().like("name",reqVO.getKeys()).or().like("code",reqVO.getKeys());
        }
        if(null!=reqVO.getOrgId() && reqVO.getOrgId()!=0){
            queryWrapper.and(wrapper->wrapper.eq("org_id",reqVO.getOrgId()).or().inSql("org_id","select o.id from organization o where o.is_del = 0 and o.pid="+reqVO.getOrgId()));
        }
        //queryWrapper.and(wrapper->wrapper.eq("org_id",currentUser.getOrgId()).or().inSql("org_id","select o.id from organization o where o.is_del = 0 and o.pid="+currentUser.getOrgId()));
        queryWrapper.orderByAsc("id");
        List<PCDictResVO> list = new ArrayList<>();
        IPage<Dictionary> dictionaryIPage = dictionaryDomainService.getBaseMapper().selectPage(page, queryWrapper);
        for (Dictionary record : dictionaryIPage.getRecords()) {
            PCDictResVO resVO = new PCDictResVO();
            BeanUtils.copyProperties(record,resVO);
            if(null!=record.getOrgId()){
                Organization org = organizationDomainService.getById(record.getOrgId());
                if(null!=org){
                    resVO.setOrgName(org.getOrgName());
                }
            }else{
                resVO.setOrgName("公用");
            }
            list.add(resVO);
        }
        IPage<PCDictResVO> resVOIPage = new Page<>(reqVO.getPage(), reqVO.getNum());
        resVOIPage.setRecords(list);
        resVOIPage.setTotal(page.getTotal());
        return resVOIPage;
    }

    @Override
    public boolean add(PCDictAddReqVO addReqVO) {
        UserInfoDTO currentUser = UserInfoUtil.getCurrentUser();
        Dictionary dictionary = new Dictionary();
        BeanUtils.copyProperties(addReqVO,dictionary);
        dictionary.setCreateTime(new Date());
        dictionary.setCreateId(currentUser.getUserId());
        return dictionaryDomainService.save(dictionary);
    }

    @Override
    public boolean update(PCDictUpdateReqVO updateReqVO) {
        UserInfoDTO currentUser = UserInfoUtil.getCurrentUser();
        Dictionary dictionary = new Dictionary();
        BeanUtils.copyProperties(updateReqVO,dictionary);
        dictionary.setUpdateId(currentUser.getUserId());
        dictionary.setUpdateTime(new Date());
        return dictionaryDomainService.updateById(dictionary);
    }

    @Override
    public boolean del(Integer id) {
        return dictionaryDomainService.removeById(id);
    }

    @Override
    public Dictionary details(Integer id) {
        return dictionaryDomainService.getById(id);
    }
}
