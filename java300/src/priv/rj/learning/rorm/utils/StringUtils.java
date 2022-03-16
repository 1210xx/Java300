package priv.rj.learning.rorm.utils;

/**
 * 封装常用字符串操作
 * @author rjjerry
 */
public class StringUtils {

    /**
     * 将目标首字母变为大写
     * @param str 目标字符串
     * @return 首字母大写的字符串
     */
    public static String firstChar2UpperCase(String str){
        //abcd --> Abcd
        //abcd --> ABCD ---> A
        return str.toUpperCase().substring(0, 1)+ str.substring(1);
    }

}
