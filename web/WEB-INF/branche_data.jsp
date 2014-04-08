<%-- 
    Document   : branche_data
    Created on : 6-Apr-2014, 8:35:59 PM
    Author     : Gabriel Stone
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Branche Data</title>
    </head>
    <body>
        
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
        
        <h1>Branche Data</h1>
        

        
        <jsp:useBean id="branchebean" class="model.beans.BrancheBean" scope="request"/>
         
         Branche ID : <% out.print(branchebean.getBeanId());%>
         <br>
         Branche Name : <% out.print(branchebean.getBeanName());%>
         <br>
         City Name : <% out.print(branchebean.getBeanCity());%>
         <br>
         Assets : <% out.print(branchebean.getBeanAssets());%>
        
        
    </body>
</html>
