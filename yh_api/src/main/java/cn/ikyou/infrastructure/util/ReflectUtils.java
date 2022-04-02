package cn.ikyou.infrastructure.util;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ReflectUtils {

    public static <S, T> List<T> copyProperties(List<S> source, Class<T> cla) {
        List<T> list = new ArrayList<>();
        source.stream().forEach(item -> {
            try {
                T tt = cla.newInstance();
                BeanUtils.copyProperties(item, tt);
                list.add(tt);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return list;
    }

}
