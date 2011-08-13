package com.junjie.controller;

import com.junjie.util.ApplicationSecurityManager;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jbu
 */
public class SignOutController implements Controller {
  private ApplicationSecurityManager applicationSecurityManager;
  private String successView;

  @Override
  public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
    applicationSecurityManager.removeEmployee(httpServletRequest);
    return new ModelAndView(getSuccessView());
  }

  public ApplicationSecurityManager getApplicationSecurityManager() {
    return applicationSecurityManager;
  }

  public void setApplicationSecurityManager(ApplicationSecurityManager applicationSecurityManager) {
    this.applicationSecurityManager = applicationSecurityManager;
  }

  public String getSuccessView() {
    return successView;
  }

  public void setSuccessView(String successView) {
    this.successView = successView;
  }
}
