package cn.ikyou.domain.dictionary.service;


import cn.ikyou.domain.dictionary.entity.Dictionary;
import cn.ikyou.interfaces.base.vo.dictionary.DictionaryListResVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface DictionaryDomainService  extends IService<Dictionary> {

    List<Dictionary> findDictionaryByType(String type);

    List<Dictionary> findDictionaryByTypeList(String type);

    List<DictionaryListResVO> findDictionaryByTypeList(String type, Integer orgId);

    List<Dictionary> findDictionaryBypid(Integer pid);
}
