package cn.ikyou.interfaces.base.wrapper;

import cn.ikyou.domain.dictionary.entity.Dictionary;
import cn.ikyou.interfaces.base.vo.dictionary.DictionaryListResVO;
import cn.ikyou.interfaces.base.vo.dictionary.DictionaryMapResVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Component
public class DictionaryWrapper {

    public List<DictionaryListResVO> dictionarylistToVO(List<Dictionary> list) {
        List<DictionaryListResVO> result=new ArrayList<>();
        if(!CollectionUtils.isEmpty(list)){
            for(Dictionary dictionary : list){
                DictionaryListResVO resVO=new DictionaryListResVO();
                BeanUtils.copyProperties(dictionary,resVO);
                result.add(resVO);
            }
        }
        return result;
    }

    public List<DictionaryMapResVO> dictionaryMpToVO(List<Dictionary> list) {
        List<DictionaryMapResVO> result=new ArrayList<>();
        if(CollectionUtils.isEmpty(list)){
            return result;
        }
        Map<String,List<DictionaryListResVO>> map=new HashMap<>();
        for(Dictionary dictionary : list){
            DictionaryListResVO resVO=new DictionaryListResVO();
            BeanUtils.copyProperties(dictionary,resVO);
            //
            if(map.containsKey(dictionary.getType())){
                map.get(dictionary.getType()).add(resVO);
            }else{
                List<DictionaryListResVO> temp=new ArrayList<>();
                temp.add(resVO);
                map.put(dictionary.getType(),temp);
            }
        }
        Set<Map.Entry<String, List<DictionaryListResVO>>> sets = map.entrySet();
        for(Map.Entry<String, List<DictionaryListResVO>> set : sets){
            DictionaryMapResVO dictionaryMapResVO=new DictionaryMapResVO();
            dictionaryMapResVO.setType(set.getKey());
            dictionaryMapResVO.setDict(set.getValue());
            result.add(dictionaryMapResVO);
        }
        return result;
    }


}
