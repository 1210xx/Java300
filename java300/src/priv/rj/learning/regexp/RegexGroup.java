package priv.rj.learning.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 测试表达式分组的用法
 */
public class RegexGroup {


    Pattern pattern = Pattern.compile("([a-z]+)([0-9]+)");

    public static void main(String[] args) {

        RegexGroup regex = new RegexGroup();

        Matcher matcher = regex.pattern.matcher("asfs**df23423&&sds421");
        //尝试整个序列与该模式匹配
//        boolean flag = matcher.matches();
//        System.out.println(flag);

        //扫描输入的序列，查找与该模式匹配的子序列
//        boolean flag2 = matcher.find();
//        System.out.println(flag2);
//        System.out.println(matcher.find());
//        System.out.println(matcher.group());
//
//        System.out.println(matcher.find());
//        System.out.println(matcher.group());

        while (matcher.find()) {
            //group() group() 匹配整个表达式的子字符串
            System.out.println(matcher.group());
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
        }

    }

}
