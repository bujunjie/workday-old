package com.junjie.controller;

/**
 * @author jbu
 */
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import com.junjie.model.Employee;
import com.junjie.model.TimesheetManager;
import com.junjie.util.ApplicationSecurityManager;
import com.junjie.util.WorkdayJmxBean;

/**
 * Controller for the Timesheet List screen.
 * @author anil
 */
public class TimesheetListController implements Controller
{
    private TimesheetManager timesheetManager;
    private ApplicationSecurityManager applicationSecurityManager;
    public static final String MAP_KEY = "timesheets";
    private String successView;
    private WorkdayJmxBean workdayJmxBean;

    /**
     * Returns a list of Timesheet database objects in ModelAndView.
     */
    public ModelAndView handleRequest(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        Employee employee = (Employee) applicationSecurityManager
                .getEmployee(request);
        List timesheets = timesheetManager.getTimesheets(employee
                .getEmployeeId());
        workdayJmxBean.setTimesheetsFetched(workdayJmxBean.getTimesheetsFetched()
                + timesheets.size());
        return new ModelAndView(getSuccessView(), MAP_KEY, timesheets);
    }

    public TimesheetManager getTimesheetManager()
    {
        return timesheetManager;
    }

    public void setTimesheetManager(TimesheetManager timesheetManager)
    {
        this.timesheetManager = timesheetManager;
    }

    public ApplicationSecurityManager getApplicationSecurityManager()
    {
        return applicationSecurityManager;
    }

    public void setApplicationSecurityManager(
            ApplicationSecurityManager applicationSecurityManager)
    {
        this.applicationSecurityManager = applicationSecurityManager;
    }

    public String getSuccessView()
    {
        return successView;
    }

    public void setSuccessView(String successView)
    {
        this.successView = successView;
    }

    public WorkdayJmxBean getWorkdayJmxBean()
    {
        return workdayJmxBean;
    }

    public void setWorkdayJmxBean(WorkdayJmxBean workdayJmxBean)
    {
        this.workdayJmxBean = workdayJmxBean;
    }
}
