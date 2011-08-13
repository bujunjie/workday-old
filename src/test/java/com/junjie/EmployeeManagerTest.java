package com.junjie;

import com.junjie.model.Employee;
import com.junjie.model.EmployeeManager;
import com.junjie.model.Timesheet;
import com.junjie.model.TimesheetManager;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author jbu
 */
public class EmployeeManagerTest extends TestCase {
  private static final EmployeeManager employeeManager;

  static {
    ApplicationContext appContext = new ClassPathXmlApplicationContext("application-context.xml");
    employeeManager = (EmployeeManager) appContext.getBean("employeeManager");
  }

  public EmployeeManagerTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(EmployeeManagerTest.class);
  }

  public void testAddEmployees() {
    List<Employee> list = new ArrayList<Employee>();
    for (int i=0;i<10;i++) {
      Employee e = new Employee(0,"EMP"+i,"H","1234567890", "emp"+i+"@gmail.com", 0);
      list.add(e);
    }
    employeeManager.addAll(list);
  }

}
