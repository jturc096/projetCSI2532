<%-- 
    Document   : accountSupervisorData
    Created on : 7-Apr-2014, 4:20:14 PM
    Author     : Gabriel Stone
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Supervisor Data</title>
    </head>
    <body>
        <h1>Account Supervisor Data</h1>
        
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
        
        
        <jsp:useBean id="accountsupervisorbean" class="model.beans.AccountSupervisorBean" scope="request"/>
        
        <h2>Here are the accounts that you supervise</h2>
        <p>
            Employee ID : <% out.print(accountsupervisorbean.getBeanEmployeeId());%>
            <br>
            Account ID : <% out.print(accountsupervisorbean.getBeanAccount_number());%>
            <br>
            Customer ID: <% out.print(accountsupervisorbean.getBeanCustomerId());%>
         </p> 
         

         
         
<!--         WORKING ON THE FIRST STEP TO VIEWING CUSTOMER DATA FROM EMPLOYEE POINT OF VIEW-->
        <form action="customerDataE" name="jsp_customer_data_E" method="get">
            If you wish to view information on a certain customer,
             insert the Customer ID here : <input type="text" name="jsp_customerId_txt">
            <input type="submit" value="Customer Data" name="jsp_customer_dataE_btm">
        </form>
         
        
        
        
    </body>
</html>
