package com.junjie;

import com.junjie.model.Department;
import com.junjie.model.DepartmentManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jbu
 */
public class HibernateTest {
  public static void main(String args[]) {
    ApplicationContext appContext = new ClassPathXmlApplicationContext("application-context.xml");
    DepartmentManager service = (DepartmentManager) appContext.getBean("departmentManager");

//    service.deleteAll();
    List<Department> departments = new ArrayList<Department>();
    for (int i=0;i<10;i++) {
      Department d1 = new Department("D"+i, "Department "+i);
      departments.add(d1);
    }
    service.saveAll(departments);
//    service.save(d2);
//    service.saveAll(null);
    List<Department> result = service.getDepartments();
    for (int i=0;i<10;i++) {
      System.out.println(result.get(i));
    }


  }
}
