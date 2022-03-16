package priv.rj.learning.dynamiccompile;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class Demo01 {
    public static void main(String[] args) throws IOException {
        //通过IO流操作，将字符串存储成一个临时文件（Hi.java），然后调用动态编译方法！
        String str = "public class Hi{public static void main(String[] args){ System.out.println(\"test!!\") }}";
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int result = compiler.run(null, null, null, "/Users/rainjaneJerry/Downloads/java/myjava/HelloWorld.java");
        System.out.println(result == 0 ? "编译成功" : "编译失败");

        //通过Runtime调用执行类
//        Runtime run = Runtime.getRuntime();
//        Process process = run.exec("java -cp /Users/rainjaneJerry/Downloads/java/myjava  HelloWorld");
//
//        InputStream inputStream = process.getInputStream();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//        try {
//            while ((str = reader.readLine()) != null) {
//                // ... do something with line
//                System.out.println(str);
//            }
//        } catch (IOException e) {
//            // ... handle IO exception
//        }

        try{
            URL[] urls = new URL[]{new URL("file:/" + "Users/rainjaneJerry/Downloads/java/myjava/")};
            URLClassLoader loader = new URLClassLoader(urls);
            Class c = loader.loadClass("HelloWorld");
            //调用加载类的main方法
            Method method = c.getMethod("main", String[].class);
            method.invoke(null, (Object)new String[] {});
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
