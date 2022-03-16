package priv.rj.learning.regexp;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式的其他用法
 * 替换 分割
 */
public class RegexOther {
    Pattern pattern = Pattern.compile("[0-9]");

    public static void main(String[] args) {
        RegexOther regexOther = new RegexOther();
        Matcher matcher = regexOther.pattern.matcher("as2342**sss234**asdf2134");

        //替换
        String newStr = matcher.replaceAll("#");
        //System.out.println(newStr);

        //分割
        String string = "a,b,c";
        String[] arrs = string.split(",");
        System.out.println(Arrays.toString(arrs));

        String string2 = "a23423b1234c";
        String[] arrs2 = string2.split("\\d+");
        System.out.println(Arrays.toString(arrs2));

    }
}
