package com.junjie.controller;

/**
 * @author jbu
 */

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.junjie.model.Timesheet;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.junjie.model.Employee;
import com.junjie.model.TimesheetManager;
import com.junjie.util.ApplicationSecurityManager;
import com.junjie.util.WorkdayJmxBean;

/**
 * Controller for the Timesheet List screen.
 *
 * @author anil
 */
@Controller
@RequestMapping("/timesheetlist.htm")
public class TimesheetListController {
  @Autowired
  private TimesheetManager timesheetManager;

  @Autowired
  private ApplicationSecurityManager applicationSecurityManager;

  @Autowired
  private WorkdayJmxBean workdayJmxBean;

  public static final String MAP_KEY = "timesheets";
  private String successView = "timesheetlist";

  private final static Logger logger = Logger.getLogger(TimesheetListController.class);

  @ModelAttribute("timesheets")
  List<Timesheet> populateTimesheets(HttpServletRequest request) {
    Employee employee = (Employee) applicationSecurityManager.getEmployee(request);
    List<Timesheet> timesheets = timesheetManager.getTimesheets(employee
      .getEmployeeId());
    workdayJmxBean.setTimesheetsFetched(workdayJmxBean.getTimesheetsFetched()
      + timesheets.size());
    return timesheets;
  }

  @RequestMapping(method = RequestMethod.GET)
  public String setupForm(HttpServletRequest request,
                           HttpServletResponse response) throws Exception {
//       String number = ServletRequestUtils.getStringParameter(request, "number");
//       ModelAndView mav = new ModelAndView("/WEB-INF/views/accounts/show.jsp");
//       mav.addObject("account", accountRepository.findAccount(number));
//       return mav;
    return "timesheetlist";
  }

}
