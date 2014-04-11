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
        <title>Payments</title>
    </head>
    <body>
        <jsp:useBean id="customerbean" class="model.beans.CustomerBean" scope="request"/>
        <div id="nav">
            <a href="<c:url value="loginC">   
                   <c:param name="cID" value="${customerbean.beanId}"/> 
                    </c:url>  
                ">Home</a>   
            <a href="<c:url value="account_summary">   
                        <c:param name="cID" value="${customerbean.beanId}"/> 
                    </c:url>  
                ">Account Summary</a>
            <a href="<c:url value="account_activity">   
                        <c:param name="cID" value="${customerbean.beanId}"/> 
                    </c:url>  
                ">Account Activity</a>
            <a href="<c:url value="customer_payment">   
                        <c:param name="cID" value="${customerbean.beanId}"/> 
                    </c:url>  
                ">Payments</a>
            <a href="#">Transfers</a>
            <a href="index.jsp">Logout</a>
        </div>
        
        
        <%-- This is how we access the bean  called - forwardbean passed in the request
            Note that the id parameter must be equal to the name of attribute name as 
            defined in the request. 
            class is the class name of the javaBean -- in our case it is ForwardBean which
            exists in model.beans.ForwardBean .. 
            beans has a scope of request, session, application and page .. let's ignore that 
            now. 
        --%>
        

        
        
        <jsp:useBean id="accountbean" class="model.beans.AccountBean" scope="request"/>
        <div>
            <h1>Payments</h1>
            <form action="customer_payment" name="jsp_account_id" method="get">
                <input type="hidden" value="${customerbean.beanId}" name="cID">
                # du Compte : <input type="text" name="jsp_accountid_txt">
                Destinataire : <input type="text" name="jsp_destinataire_txt">
                <br>
                Montant : <input type="text" name="jsp_montant_txt">
                Date du transfert : <input type="text" name="jsp_date_txt">
                <br>
                <input type="submit" value="Faire Payment" name="jsp_activity_btm">
            </form>
        </div>
                <br>
        <h3><%out.print(request.getAttribute("msg").toString());%></h3>

        
        <%-- As a designer, don't use any argument not included in the bean! It is the 
        job of the Controller to make sure that it include everything you need! 
        
        The controller may also include some boolean variable to control how the page 
        shall be rendered .. 
        --%>
    
    </body>
</html>

