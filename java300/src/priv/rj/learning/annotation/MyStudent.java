package priv.rj.learning.annotation;

/**
 * @author rjjerry
 */
@MyTable("tb_student")
public class MyStudent {

    @MyFiled(columnName = "id", type = "int", length = 10)
    private int id;
    @MyFiled(columnName = "sname", type = "varchar", length = 10)
    private String stuName;
    @MyFiled(columnName = "age", type = "int", length = 3)
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
