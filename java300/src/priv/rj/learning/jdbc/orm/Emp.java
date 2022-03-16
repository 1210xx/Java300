package priv.rj.learning.jdbc.orm;

import java.util.Date;

/**
 * 表和类对应
 */
public class Emp {
    private Integer id;
    private String empname;
    private Integer age;
    private Double salary;
    private Date birthday;
    private Integer depID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getDepID() {
        return depID;
    }

    public void setDepID(Integer depID) {
        this.depID = depID;
    }

    public Emp() {
    }

    public Emp(Integer id, String empname, Integer age, Double salary, Date birthday, Integer depID) {
        this.id = id;
        this.empname = empname;
        this.age = age;
        this.salary = salary;
        this.birthday = birthday;
        this.depID = depID;
    }

    public Emp(String empname, Integer age, Double salary, Date birthday, Integer depID) {
        this.empname = empname;
        this.age = age;
        this.salary = salary;
        this.birthday = birthday;
        this.depID = depID;
    }

    public Emp(String empname,  Double salary,Integer age) {
        this.empname = empname;
        this.age = age;
        this.salary = salary;
    }
}
