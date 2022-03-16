package priv.rj.learning.loadclass;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 加载文件系统中加密后的class加载器
 *
 */
public class DecrptClassLoader extends ClassLoader{
    // priv.rj.learning.loadclass.User ---> /Users/rainjaneJerry/Documents/Code/Java/IdeaProjects/Java300/java300/src/priv/rj/learning/loadclass/User.class
    private String rootDir;

    public DecrptClassLoader(String rootDir) {
        this.rootDir = rootDir;
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
     * @param classname // priv.rj.learning.loadclass.User
     * @return /Users/rainjaneJerry/Documents/Code/Java/IdeaProjects/Java300/java300/src/priv/rj/learning/loadclass/User.class
     */
    private byte[] getClassData(String classname) {
        String path = rootDir + "/" + classname.replace('.', '/') + ".class";
        //IOUtils 可是使用它将流中的数组转成字节数组
        InputStream is = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            is = new FileInputStream(path);


            int len = -1;
            while (-1 !=(len = is.read())){
                baos.write(len^0xff);

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
