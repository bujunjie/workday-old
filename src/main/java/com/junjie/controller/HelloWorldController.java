package com.junjie.controller;


import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.io.IOException;

/**
 * @author jbu
 */
public class HelloWorldController implements Controller {
  protected final Log logger = LogFactory.getLog(getClass());
  public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    logger.info("Return View");
    return new ModelAndView("helloworld");
  }
}