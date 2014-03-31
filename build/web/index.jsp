<%-- 
    Document   : jsp_login
    Created on : Mar 16, 2014, 2:07:03 PM
    Author     : ahmed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <div>
            <h1>Login Customer</h1>
            <form action="loginC" name="jsp_login_C" method="get">
                Username : <input type="text" name="jsp_usernameC_txt">
                Password : <input type="password" name="jsp_passwordC_txt">
                <input type="submit" value="LoginC" name="jsp_loginC_btm">
            </form>
        </div>
        <div>
            <h1>Login Employee</h1>
            <form action="loginE" name="jsp_login_E" method="get">
                Username : <input type="text" name="jsp_usernameE_txt">
                Password : <input type="password" name="jsp_passwordE_txt">
                <input type="submit" value="LoginE" name="jsp_loginE_btm">
            </form>
        </div>
    
        <%-- This is how we access the bean  called - forwardbean passed in the request
            Note that the id parameter must be equal to the name of attribute name as 
            defined in the request. 
            class is the class name of the javaBean -- in our case it is ForwardBean which
            exists in model.beans.ForwardBean .. 
            beans has a scope of request, session, application and page .. let's ignore that 
            now. 
        --%>
        <%--<jsp:useBean id="forwardbean" class="model.beans.ForwardBean" scope="request"/>--%>
      
        
        <%-- you see the jsp:getProperty .. well, this is how you access the bean - note 
        that beanId is actually calling getBeanId(). The same can be said about beanMessage--%>
        
        <%--<p>We received a forward bean with id = <jsp:getProperty name="forwardbean" property="beanId"/>
            and message <jsp:getProperty name="forwardbean" property="beanMessage"/></p>
        
        
        <%-- This is how iterate over a java bean in JSP - This is in fact is JSTL 
            You haven't learned that - but just take it as it is right now.. 
            
            Note that you have to include the <%@ taglib .. above .. 
        --%>
        
        <%--<ul>
            <c:forEach items="${forwardbean.beanStringList}" var="v">
                <li>${v}</li>
            </c:forEach>
                      
        </ul> --%>

        
        <%-- As a designer, don't use any argument not included in the bean! It is the 
        job of the Controller to make sure that it include everything you need! 
        
        The controller may also include some boolean variable to control how the page 
        shall be rendered .. 
        --%>
    
    </body>
</html>
