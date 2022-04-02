package cn.ikyou.domain.dictionary.service.impl;

import cn.ikyou.domain.dictionary.dao.DictionaryMapper;
import cn.ikyou.domain.dictionary.entity.Dictionary;
import cn.ikyou.domain.dictionary.service.DictionaryDomainService;
import cn.ikyou.interfaces.base.vo.dictionary.DictionaryListResVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


@Service
public class DictionaryDomainServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements DictionaryDomainService {


    @Override
    public List<Dictionary> findDictionaryByType(String type) {
        QueryWrapper query=new QueryWrapper();
        query.eq("type",type);
        return baseMapper.selectList(query);
    }

    @Override
    public List<Dictionary> findDictionaryByTypeList(String type) {
        QueryWrapper query=new QueryWrapper();
        query.eq("type",type);
        List<Dictionary> list= baseMapper.selectList(query);
        //
        List<Integer> pids=new ArrayList<>();
        if(!CollectionUtils.isEmpty(list)){
            for(Dictionary dictionary : list){
                pids.add(dictionary.getId());
            }
        }
        if(CollectionUtils.isEmpty(pids)){
            return null;
        }
        QueryWrapper<Dictionary> dictionaryQueryWrapper=new QueryWrapper();
        dictionaryQueryWrapper.in("pid",pids);
        return baseMapper.selectList(dictionaryQueryWrapper);
    }

    @Override
    public List<DictionaryListResVO> findDictionaryByTypeList(String type, Integer orgId) {
        QueryWrapper<Dictionary> dictionaryQueryWrapper=new QueryWrapper();
        dictionaryQueryWrapper.eq("type",type);
        dictionaryQueryWrapper.eq("org_id",orgId);
        List<Dictionary> list = baseMapper.selectList(dictionaryQueryWrapper);
        List<DictionaryListResVO> resVOList = new ArrayList<>();
        for (Dictionary dictionary : list) {
            DictionaryListResVO resVO = new DictionaryListResVO();
            BeanUtils.copyProperties(dictionary,resVO);
            resVOList.add(resVO);
        }
        return resVOList;
    }

    @Override
    public List<Dictionary> findDictionaryBypid(Integer pid) {
        QueryWrapper query=new QueryWrapper();
        query.eq("pid",pid);
        return baseMapper.selectList(query);
    }

}
