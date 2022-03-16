package priv.rj.learning.net.server.demo04;

/**
 *  <servlet>
 *         <name>login</name>
 *         <servlet-class>priv.rj.learning.net.server.demo04.LoginServlet</servlet-class>
 *  </servlet>
 */
public class Entity {
    private String name;
    private String clz;

    public String getName() {
        return name;
    }

    public String getClz() {
        return clz;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClz(String clz) {
        this.clz = clz;
    }
}
