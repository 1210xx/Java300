package priv.rj.learning.rorm.test;

import priv.rj.learning.rorm.core.Query;
import priv.rj.learning.rorm.core.QueryFactory;
import priv.rj.learning.rorm.vo.EmpVO;

import java.util.List;

/**
 * 客户端调用测试类
 * @author rjjerry
 */
public class Test {
    public static void main(String[] args) {
        Query query = QueryFactory.createQuery();
        String sql2 = "  SELECT e.id,e.empname,e.salary + e.bonus 'xinshui',e.age ,d.dname 'deptName',d.address 'deptAddr'FROM emp e JOIN dept d ON e.deptID = d.id;";

        List<EmpVO> list2 = query.queryRows(sql2, EmpVO.class, null);

        for (EmpVO e : list2){
            System.out.println(e.getEmpname() + " - " + e.getDeptAddr() + " - " + e.getXinshui());
        }

    }
}
