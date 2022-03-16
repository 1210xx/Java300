package priv.rj.learning.scriptengine;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * 测试脚本引擎执行javascript代码
 * @author rjjerry
 *
 */
public class Demo01 {
    public static void main(String[] args) throws ScriptException, NoSuchMethodException, IOException {
        //获得脚本对象
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine engine = sem.getEngineByName("javascript");

        //定义变量 , 存储到引擎上下文
        engine.put("msg", "xx is good man");
        String string = "var user = {name:'eeee', age:18, schools:['THU','SWPU']};";
        string += "print(user.name);";

        //执行脚本
        engine.eval(string);
        engine.eval("msg = 'xxx is bad guy'");
        //java 代码读取js的msg
        System.out.println(engine.get("msg"));

        System.out.println("####################");
        //定义js函数
        engine.eval("function add(a, b) {var sum = a + b; return sum;}");
        //执行js函数
        //取得调用接口
        Invocable jsInvoke = (Invocable)engine;
        //执行脚本中调用的定义的方法
        Object result1 = jsInvoke.invokeFunction("add", new Object[]{13, 20});
        System.out.println(result1);

        //导入其它的java包，使用其它包中的java类
        //jdk1.6
//        String jsCode = "importPackage(java.util); var list = Arrays.asList([\"清华大学\",\"大学\",\"清华\" ]);";
        //jdk1.8
        String jsCode = "var list = java.util.Arrays.asList([\"清华大学\",\"大学\",\"清华\" ]);";
        engine.eval(jsCode);

        List<String> list2 =(List<String>)engine.get("list");
        for (String temp : list2){
            System.out.println(temp);
        }
        //执行一个js文件
        URL url = Demo01.class.getClassLoader().getResource("priv/rj/learning/scriptengine/a.js");
        System.out.println(url);
        FileReader fileReader = new FileReader(url.getPath());
        engine.eval(fileReader);
        fileReader.close();
    }
}
