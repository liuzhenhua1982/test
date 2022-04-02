package cn.ikyou.domain.common.dao;

import cn.ikyou.domain.common.entity.TAppVersion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TAppVersionMapper extends BaseMapper<TAppVersion> {

    TAppVersion selectAppVersion(@Param("platform") String platform);
}
