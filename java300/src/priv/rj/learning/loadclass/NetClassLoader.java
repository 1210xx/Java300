package priv.rj.learning.loadclass;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class NetClassLoader extends ClassLoader{
    // www.sxt.cn.myjava ---> /Users/rainjaneJerry/Documents/Code/Java/IdeaProjects/Java300/java300/src/priv/rj/learning/loadclass/User.class
    private String rootUrl;

    public NetClassLoader(String rootUrl) {
        this.rootUrl = rootUrl;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> c = findLoadedClass(name);
        //首先查询是否加载过此类。如果以加载，直接返回加载好的类。否则，加载新类
        if (null != c) {
            return c;
        } else {
            //委派给父类加载
            ClassLoader parent = this.getParent();
            try {
                c = parent.loadClass(name);
            }catch (Exception e){
                // e.printStackTrace();
            }
            if (null != c) {
                return c;
            } else {
                ByteArrayOutputStream baos;
                byte[] classData = getClassData(name);
                if (null == classData) {
                    throw new ClassNotFoundException();
                } else {
                    c = defineClass(name, classData, 0, classData.length);

                }
            }
        }
        return c;
    }

    /**
     * @param classname // www.sxt.cn/myjava
     * @return /Users/rainjaneJerry/Documents/Code/Java/IdeaProjects/Java300/java300/src/priv/rj/learning/loadclass/User.class
     */
    private byte[] getClassData(String classname) {
        String path = rootUrl + "/" + classname.replace('.', '/') + ".class";
        //IOUtils 可是使用它将流中的数组转成字节数组
        InputStream is = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            URL url = new URL(path);
            is = url.openStream();

            byte[] buffer = new byte[1024];

            int temp = 0;
            while (-1 != (temp = is.read(buffer))) {
                baos.write(buffer, 0, temp);
            }
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {

            try {
                if (null != is) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (null != baos) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
