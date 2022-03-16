package priv.rj.learning.rorm.bean;

/**
 * 封装了java属性和set、get方法
 * @author rjjerry
 */
public class JavaFieldGetSet {
    /**
     * 属性的源码信息。如：private int uerId;
     */

    private String fieldInfo;
    /**
     * get方法的源码信息。如：public int getUserId(){};
     */

    private String getInfo;
    /**
     * set方法的源码信息。如：public void setUserID(int id){this.id = id;};
     */
    private String setInfo;

    public String getFieldInfo() {
        return fieldInfo;
    }

    public void setFieldInfo(String fieldInfo) {
        this.fieldInfo = fieldInfo;
    }

    public String getGetInfo() {
        return getInfo;
    }

    public void setGetInfo(String getInfo) {
        this.getInfo = getInfo;
    }

    public String getSetInfo() {
        return setInfo;
    }

    public void setSetInfo(String setInfo) {
        this.setInfo = setInfo;
    }

    public JavaFieldGetSet() {
    }

    public JavaFieldGetSet(String fieldInfo, String getInfo, String setInfo) {
        this.fieldInfo = fieldInfo;
        this.getInfo = getInfo;
        this.setInfo = setInfo;
    }

    @Override
    public String toString() {
        System.out.println(fieldInfo);
        System.out.println(getInfo);
        System.out.println(setInfo);
        return super.toString();
    }
}
