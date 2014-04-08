<%-- 
    Document   : jsp_forward
    Created on : Mar 16, 2014, 2:07:03 PM
    Author     : ahmed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
    
        <h1>Home Employee</h1>
        
        <div id="nav">
            <a href="index.jsp">Home</a>
            <a href="index.jsp">Logout</a>

        </div>
        
        <form action="employee_data" name="jsp_employee_data_E" method="get">
            Enter a Employee Username to view information on that employee : <input type="text" name="jsp_usernameE_txt">
            <input type="submit" value="Employee Data" name="jsp_employee_data_btm">
        </form>
        
        
        <form action="branche_data" name="jsp_branche_data_E" method="get">
            Enter the Branche Name to view information on that branche : <input type="text" name="jsp_brancheName_txt">
            <input type="submit" value="Branche Data" name="jsp_branche_data_btm">
        </form>
        
        
        <form action="accountSupervisor" name="jsp_account_supervisor_E" method="get">
            Enter your Employee ID to view the accounts you supervise : <input type="text" name="jsp_employeeId_txt">
            <input type="submit" value="Account Supervisor" name="jsp_account_supervisor_btm">
        </form>
        
        <%-- This is how we access the bean  called - forwardbean passed in the request
            Note that the id parameter must be equal to the name of attribute name as 
            defined in the request. 
            class is the class name of the javaBean -- in our case it is ForwardBean which
            exists in model.beans.ForwardBean .. 
            beans has a scope of request, session, application and page .. let's ignore that 
            now. 
        --%>
        <jsp:useBean id="employeebean" class="model.beans.EmployeeBean" scope="request"/>
      
        
        <%-- you see the jsp:getProperty .. well, this is how you access the bean - note 
        that beanId is actually calling getBeanId(). The same can be said about beanMessage--%>
        
        
        
        <%-- This is how iterate over a java bean in JSP - This is in fact is JSTL 
            You haven't learned that - but just take it as it is right now.. 
            
            Note that you have to include the <%@ taglib .. above .. 
        --%>
        
        <h1>Employee Information</h1>

        
        <p>
            First Name : <input type="text" name="jsp_usernameE_txt" value="<% out.print(employeebean.getBeanFname());%>">
            <br>
            Last Name <input type="text" name="jsp_nomFamille_txt" value="<% out.print(employeebean.getBeanLname());%>">
            <br>
            Employee Username: <input type="text" name="jsp_employeeUsername_txt" value="<% out.print(employeebean.getBeanUsername());%>">
            <br>            
            Start Date: <% out.print(employeebean.getbeanStartDate());%>
            <br>
            Employee Id: <% out.print(employeebean.getBeanId());%> 
            <br>
            Employee Password: <input type="text" name="jsp_employeePassword_txt" value="<% out.print(employeebean.getBeanPassword());%>">
        </p>

        
        <%-- As a designer, don't use any argument not included in the bean! It is the 
        job of the Controller to make sure that it include everything you need! 
        
        The controller may also include some boolean variable to control how the page 
        shall be rendered .. 
        --%>
    
    </body>
</html>
