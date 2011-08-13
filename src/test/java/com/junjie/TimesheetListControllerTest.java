package com.junjie;

import com.junjie.controller.TimesheetListController;
import com.junjie.model.Employee;
import com.junjie.model.Timesheet;
import com.junjie.model.TimesheetManager;
import com.junjie.util.ApplicationSecurityManager;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * Test class for TimeListController
 */
public class TimesheetListControllerTest extends TestCase {
  private final static TimesheetManager timesheetManager;
  private final static TimesheetListController timesheetListController;
  private final static ApplicationSecurityManager applicationSecurityManager;

  static {
    ApplicationContext context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/workday-servlet.xml");
    timesheetManager = (TimesheetManager) context.getBean("timesheetManager");
    timesheetListController = (TimesheetListController) context.getBean("timesheetListController");
    applicationSecurityManager = (ApplicationSecurityManager) context.getBean("applicationSecurityManager");

  }

  private MockHttpServletRequest mockHttpServletRequest = null;
  private final int EMPLOYEE_ID = 1111;
  private static int timesheetId1 = 0;
  private static int timesheetId2 = 0;

  public static void main(String args[]) {
    junit.textui.TestRunner.run(suite());
  }

  public static Test suite() {
    return new TestSuite(TimesheetListControllerTest.class);
  }

  /**
   * Test GET of TimesheetList screen for an employee
   */
  public void testShowForm() throws Exception {
    mockHttpServletRequest = new MockHttpServletRequest("GET",
      "/timesheetlist.htm");

    Employee employee = new Employee();
    employee.setEmployeeId(EMPLOYEE_ID);
    applicationSecurityManager.setEmployee(mockHttpServletRequest,
      employee);

    ModelAndView modelAndView = timesheetListController.show(
      mockHttpServletRequest, null);

    assertNotNull(modelAndView);
    assertNotNull(modelAndView.getModel());

    List timesheets = (List) modelAndView.getModel().get(
      TimesheetListController.MAP_KEY);
    assertNotNull(timesheets);

    Timesheet timesheet;
    for (int i = 0; i < timesheets.size(); i++) {
      timesheet = (Timesheet) timesheets.get(i);
      assertEquals(EMPLOYEE_ID, timesheet.getEmployeeId());
      System.out.println(timesheet.getTimesheetId() + " passed!");
    }
  }

  /**
   * Create test Timesheet objects in DB
   */
  protected void setUp() throws Exception {
    Timesheet timesheet = null;

    timesheet = new Timesheet();
    timesheet.setEmployeeId(EMPLOYEE_ID);
    timesheet.setPeriodEndingDate(new GregorianCalendar(2006,
      Calendar.MARCH, 04).getTime());
    timesheet.setStatusCode("P");
    timesheet.setDepartmentCode("IT");
    timesheet.setMinutesMon(480);
    timesheet.setMinutesTue(480);
    timesheet.setMinutesWed(480);
    timesheet.setMinutesThu(480);
    timesheet.setMinutesFri(480);
    timesheet.setMinutesSat(0);
    timesheet.setMinutesSun(0);
    timesheetManager.saveTimesheet(timesheet);
    timesheetId1 = timesheet.getTimesheetId();

    timesheet = new Timesheet();
    timesheet.setEmployeeId(EMPLOYEE_ID);
    timesheet.setPeriodEndingDate(new GregorianCalendar(2006,
      Calendar.MARCH, 11).getTime());
    timesheet.setStatusCode("A");
    timesheet.setDepartmentCode("IT");
    timesheet.setMinutesMon(480);
    timesheet.setMinutesTue(480);
    timesheet.setMinutesWed(480);
    timesheet.setMinutesThu(480);
    timesheet.setMinutesFri(480);
    timesheet.setMinutesSat(0);
    timesheet.setMinutesSun(0);
    timesheetManager.saveTimesheet(timesheet);
    timesheetId2 = timesheet.getTimesheetId();
  }

  /**
   * Delete test Timesheet objects from DB.
   */
  protected void tearDown() throws Exception {
    timesheetManager.deleteTimesheet(timesheetId1);
    timesheetManager.deleteTimesheet(timesheetId2);
  }
}
