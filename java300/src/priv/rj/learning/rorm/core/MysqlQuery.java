package priv.rj.learning.rorm.core;


import priv.rj.learning.rorm.po.Emp;
import priv.rj.learning.rorm.vo.EmpVO;

import java.util.List;

/**
 * 负责针对mysql数据库的查询
 */
public class MysqlQuery extends Query {



    public static void testDML(){
        Emp emp = new Emp();
        emp.setId(1);
        emp.setEmpname("lily");
        emp.setBirthday(new java.sql.Date(System.currentTimeMillis()));
        emp.setAge(33);
        emp.setSalary(3333.3);
//        new MysqlQuery().delete(emp);
//        new MysqlQuery().insert(emp);
        new MysqlQuery().update(emp, new String[]{"empname","age","salary"});
    }

    public static void testQueryRows(){
        List<Emp> list = new MysqlQuery().queryRows("select id, empname, age from emp where age > ? and salary <?", Emp.class, new Object[]{10,5000});
        for (Emp emp: list){
            System.out.println(emp.getEmpname());
        }

        String sql2 = "  SELECT e.id,e.empname,e.salary + e.bonus 'xinshui',e.age ,d.dname 'deptName',d.address 'deptAddr'FROM emp e JOIN dept d ON e.deptID = d.id;";

        List<EmpVO> list2 = new MysqlQuery().queryRows(sql2, EmpVO.class, null);

        for (EmpVO e : list2){
            System.out.println(e.getEmpname() + " - " + e.getDeptAddr() + " - " + e.getXinshui());
        }

    }

    public static void main(String[] args) {
        Number object = (Number) new MysqlQuery().queryValue("select count(*) from emp where salary > ?", new Object[]{1000});
//        Number object = new MysqlQuery().queryNumber("select count(*) from emp where salary > ?", new Object[]{1000});
        System.out.println(object.doubleValue());
//        testQueryRows();
    }

    @Override
    public Object queryPagenate(int pageNum, int size) {
        return null;
    }
}
