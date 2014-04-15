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
        <title>JSP Page</title>
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
            <a href="<c:url value="transf">   
                        <c:param name="cID" value="${customerbean.beanId}"/> 
                    </c:url>  
                ">Transfer</a>
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
        

        
        
        <%-- This is how iterate over a java bean in JSP - This is in fact is JSTL 
            You haven't learned that - but just take it as it is right now.. 
            
            Note that you have to include the <%@ taglib .. above .. 
        --%>
        
        <div>
            
            <h1>Home</h1>
            <h3><%out.print(request.getAttribute("msg").toString());%></h3>
            <form action="updateC" name="jsp_update_C" method="get">
                <input type="hidden" value="${customerbean.beanId}" name="cID">
                Prenom : <% out.print(customerbean.getBeanFname());%>
                <br>
                Nom de famille: <% out.print(customerbean.getBeanLname());%>
                <br>
                Ville: <input type="text" name="jsp_cityC_txt" value="<% out.print(customerbean.getBeanCity());%>">
                <br>
                Adresse: <input type="text" name="jsp_adresseC_txt" value="<% out.print(customerbean.getbeanAddr());%>">
                <br>
                Téléphone: <input type="text" name="jsp_phoneC_txt" value="<% out.print(customerbean.getbeanPhone());%>">
                <br>
                Email: <input type="text" name="jsp_emailC_txt" value="<% out.print(customerbean.getbeanEmail());%>">
                <br>
                Reachable: <input type="text" name="jsp_reachableC_txt" value="<% out.print(customerbean.getbeanReachable());%>">
                <br>
            <input type="submit" value="Update" name="jsp_updateC_btm">
            </form>
        </div>

        
        <%-- As a designer, don't use any argument not included in the bean! It is the 
        job of the Controller to make sure that it include everything you need! 
        
        The controller may also include some boolean variable to control how the page 
        shall be rendered .. 
        --%>
    
    </body>
</html>
