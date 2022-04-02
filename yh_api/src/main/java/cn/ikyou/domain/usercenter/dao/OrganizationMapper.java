package cn.ikyou.domain.usercenter.dao;

import cn.ikyou.domain.usercenter.entity.Organization;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrganizationMapper extends BaseMapper<Organization> {
    List<Organization> orgList();
}
