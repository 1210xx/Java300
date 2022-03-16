package priv.rj.learning.rorm.test;

import priv.rj.learning.rorm.core.Query;
import priv.rj.learning.rorm.core.QueryFactory;
import priv.rj.learning.rorm.po.Emp;
import priv.rj.learning.rorm.vo.EmpVO;

import java.util.List;

/**
 * 测试连接池的效率
 * @author rjjerry
 */
public class Test2 {

    public static void test01() {
        Query query = QueryFactory.createQuery();
        String sql2 = "  SELECT e.id,e.empname,e.salary + e.bonus 'xinshui',e.age ,d.dname 'deptName',d.address 'deptAddr'FROM emp e JOIN dept d ON e.deptID = d.id;";

        List<EmpVO> list2 = query.queryRows(sql2, EmpVO.class, null);

        for (EmpVO e : list2) {
            System.out.println(e.getEmpname() + " - " + e.getDeptAddr() + " - " + e.getXinshui());
        }


    }

    public static void testConnPool(){
        long a = System.currentTimeMillis();
        for (int i = 0; i < 3000; i++) {
            test01();
        }
        long b = System.currentTimeMillis();
        //不使用连接池： 59788 , 连接池 ： 6111
        System.out.println(b-a);
    }

    public static void testSelect(){
        Query query = QueryFactory.createQuery();
        Emp emp = (Emp) query.queryById(Emp.class, 3);
        System.out.println(emp.getEmpname());

    }
    public static void main(String[] args) {
       testSelect();
    }
}
