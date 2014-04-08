<%-- 
    Document   : customerData
    Created on : 7-Apr-2014, 11:49:59 PM
    Author     : Gabriel Stone
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Data</title>
    </head>
    <body>
        <h1>Customer Data</h1>
        
        
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
        
        
        <h2>Information for Customer you have searched</h2>
        
        <jsp:useBean id="customerbean" class="model.beans.CustomerBean" scope="request"/>
        
        
        <p>
            First Name : <% out.print(customerbean.getBeanFname());%>
            <br>
            Last Name <% out.print(customerbean.getBeanLname());%>
            <br>
            City: <% out.print(customerbean.getBeanCity());%>
            <br>            
            Address: <% out.print(customerbean.getbeanAddr());%>
            <br>
            Customer Id: <% out.print(customerbean.getBeanId());%> 
            <br>
            Phone Number : <% out.print(customerbean.getbeanPhone());%>
            <br>
            Email : <% out.print(customerbean.getbeanEmail());%>
            <br>
            Reachable : <% out.print(customerbean.getbeanReachable());%>
            <br>
            Customer Username : <% out.print(customerbean.getBeanUsername());%>
        </p>

            
        
        
        
    </body>
</html>
