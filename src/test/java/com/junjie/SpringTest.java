package com.junjie;

import com.junjie.util.SpringTestMessage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author jbu
 */
public class SpringTest {
  public static void main(String args[]) {
    ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
    SpringTestMessage t = (SpringTestMessage) context.getBean("springtestmessage");
  //  System.out.println(t.)
  }

}
