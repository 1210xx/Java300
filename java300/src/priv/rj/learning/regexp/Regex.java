package priv.rj.learning.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 测试表达式的用法
 */
public class Regex {
    Pattern pattern = Pattern.compile("\\w+");

    public static void main(String[] args) {

        Regex regex = new Regex();

        Matcher matcher = regex.pattern.matcher("asfsdf23423&&421");
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

        while (matcher.find()){
            //group() group() 匹配整个表达式的子字符串
            System.out.println(matcher.group());
            System.out.println(matcher.group(0));
        }

    }
}
