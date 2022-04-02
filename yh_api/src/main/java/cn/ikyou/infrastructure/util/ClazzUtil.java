package cn.ikyou.infrastructure.util;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClazzUtil {

    private static Logger log= LoggerFactory.getLogger(ClazzUtil.class);
    private static Pattern linePattern = Pattern.compile("_(\\w)");

    public static Object getGetMethod(Object obj , String name){
        Class clazz=obj.getClass();
        try {
            Method method  = clazz.getMethod("get"+ClazzUtil.upperFirstLatter(name));
            return method.invoke(obj);
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
        }
        return null;

    }

    public static String humpToLine2(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();

    }

    public static String upperFirstLatter(String letter){
        char[] chars = letter.toCharArray();
        if(chars[0]>='a' && chars[0]<='z'){
            chars[0] = (char) (chars[0]-32);
        }
        return new String(chars);
    }

    public static void setFeild(Object obj, String strValue, String strName) throws NoSuchFieldException, IllegalAccessException {
        Class clazz=obj.getClass();
        Field num = clazz.getDeclaredField(strName);
        num.setAccessible(true);
        num.set(obj, strValue);
    }

}
