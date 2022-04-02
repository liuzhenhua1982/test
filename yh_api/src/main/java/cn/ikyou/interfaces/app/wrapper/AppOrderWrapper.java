package cn.ikyou.interfaces.app.wrapper;

import cn.ikyou.domain.business.entity.TWkOrders;
import cn.ikyou.interfaces.app.vo.yhorder.YhOrderResVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class AppOrderWrapper {

    public List<YhOrderResVO> ordersToVO(List<TWkOrders> pages) {
        List<YhOrderResVO> list = new ArrayList<>();

        for (TWkOrders page : pages) {
            YhOrderResVO resVO = new YhOrderResVO();
            BeanUtils.copyProperties(page,resVO);
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

        return list;
    }

}
