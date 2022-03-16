package priv.rj.learning.webspider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 网络爬虫取链接
 */
public class WebSpiderTest {

    //取到超链接的整个内容

    Pattern pattern = Pattern.compile("<a[\\s\\S]+?</a>");

    //取到超链接的地址

    Pattern pattern2 = Pattern.compile("href=\"(.+?)\"");

    /**
     * 获得URL对应的网页源码内容
     * @param urlStr
     * @return
     */
    public static String getURLContent(String urlStr,String charset){
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL("http://www.163.com");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), Charset.forName(charset)));
            String temp = "";
            while (null != (temp = reader.readLine())){
                sb.append(temp);
//                System.out.println(temp);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static List<String> getMatcherSubstrs(String destStr,String regexStr){
        Pattern pattern = Pattern.compile(regexStr);
        Matcher matcher = pattern.matcher(destStr);
        List<String> result = new ArrayList<>();
        while (matcher.find()){
            result.add(matcher.group(1));
        }
        return result;
    }

    public static void main(String[] args) {
        String destString = getURLContent("http://www.163.com","gbk");
//        //System.out.println(destString);
//
//        WebSpiderTest webSpiderTest = new WebSpiderTest();
//        Matcher matcher = webSpiderTest.pattern2.matcher(destString);
//
//        while (matcher.find()){
//            System.out.println(matcher.group(1));
//        }

        List<String> result = getMatcherSubstrs(destString, "href=\"(http[s]?:[\\w\\s./:].+?)\"");
        for (String temp : result){
            System.out.println(temp);
        }
    }
}