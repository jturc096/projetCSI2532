/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.beans;

import java.util.ArrayList;

/**
 *
 * @author ahmed
 */


// TA Notes:
// Well, this IS a JavaBean  .. fancy name, but in reality a silly idea. 
// A java bean is: 
// - A Java class .. 
// - All instance variables are not public
// - Instance variables can be accessed via a getXxx() and setXxx(). 
// - JSP can access the elements of a javaBean in an easy manner: 
//     -- • If class has method getTitle that returns a String, class is
//            said to have a String property named title
//         • Boolean properties may use isXxx instead of getXxx
//         • It is the name of the method, not instance var that matters! 
// 

/*

Usual rule to turn method name into property name: 

    – Drop the word “get” or “set” and change the next letter to
        lowercase. Again, instance var name is irrelevant.

• Method name: getUserFirstName
• Property name: userFirstName

• Exception 1: boolean properties
– If getter returns boolean or Boolean
• Method name: getPrime or isPrime
• Property name: prime

• Exception 2: consecutive uppercase letters
– If two uppercase letters in a row after “get” or “set”
• Method name: getURL
• Property name: URL (not uRL)

*/



// Have a look at FrontController and jsp_forward to see how the bean has been 
// passed from a Servlet to a JSP page. 
// Simple idea:
    // in the servlet .. request.setAttribute(beanInstanceName)
    // in the jsp     .. 
        // <jsp:useBean id="beanInstanceName" class="model.beans.ForwardBean" scope="request"/>


public class ForwardBean extends BeanBase {
    
    private int beanId;
    private String beanMessage; 
    private ArrayList<String> beanStringList; 
    
    public ForwardBean() {
        beanId = 0;
        beanMessage = "This is the default Forward beanMessage";
        beanStringList = new ArrayList<String>();
    }
    
    public ForwardBean(int beanId, String beanMessage) { 
        this.beanId = beanId;
        this.beanMessage = beanMessage;
        this.beanStringList = new ArrayList<String>();
    }
    
    public ForwardBean(ForwardBean b) { 
        this.beanId = b.beanId;
        this.beanMessage = b.beanMessage;
    }

    public ArrayList<String> getBeanStringList() {
        return beanStringList;
    }

    public void setBeanStringList(ArrayList<String> beanStringList) {
        return; /* Do Nothing */ 
    }
    
    public void pushBeanStringList(String str) { 
        beanStringList.add(str);
    }
    
    public void pushBeanStringList(ArrayList<String> beanStringList) { 
        for (int i = 0; i < beanStringList.size(); i++) { 
            pushBeanStringList(beanStringList.get(i));
        }
    }
    
    public int getBeanId() {
        return beanId;
    }
        
    public void setBeanId(int beanId) { 
      this.beanId = beanId;   
    } 

    public String getBeanMessage() {
        return beanMessage;
    }

    public void setBeanMessage(String beanMessage) {
        this.beanMessage = beanMessage;
    }

    
    
    
}
