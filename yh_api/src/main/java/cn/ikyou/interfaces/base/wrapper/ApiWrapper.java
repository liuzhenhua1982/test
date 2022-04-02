package cn.ikyou.interfaces.base.wrapper;

import cn.ikyou.domain.usercenter.entity.SysApi;
import cn.ikyou.interfaces.base.vo.api.ApiDetailVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 开发人：石聪辉
 * 开发时间：2021/9/9
 * 说明：
 */
@Component
public class ApiWrapper {

    public ApiDetailVO entityVO(SysApi api) {
        if(api==null){
            return null;
        }
        ApiDetailVO apiDetailVO=new ApiDetailVO();
        BeanUtils.copyProperties(api,apiDetailVO);
        return apiDetailVO;
    }

    public List<ApiDetailVO> listVO(List<SysApi> records) {
        List<ApiDetailVO> result=new ArrayList<>();
        if(!CollectionUtils.isEmpty(records)){
            for(SysApi api : records ){
                ApiDetailVO apiDetailVO=new ApiDetailVO();
                BeanUtils.copyProperties(api,apiDetailVO);
                result.add(apiDetailVO);
            }
        }
        return result;
    }
}
