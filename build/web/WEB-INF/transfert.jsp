<%-- 
    Document   : transfert
    Created on : Apr 10, 2014, 12:33:21 PM
    Author     : p1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transfer</title>
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
        
        <div>
            <h1>Transfer</h1>
            <form action="transf" name="jsp_transf" method="get">
                <input type="hidden" value="${customerbean.beanId}" name="cID">
                Your account no (ex. 1): <input type="text" name="jsp_accountno1_txt">
                Receiver's account no (ex. 2) : <input type="text" name="jsp_accountno2_txt">
                Amount (ex. 100.50) : <input type="text" name="jsp_amount_txt">
                
                <input type="submit" value="Transf" name="jsp_transf_btm">
            </form>
        </div>
        <h3><%out.print(request.getAttribute("message").toString());%></h3>        
        
        
    
    
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
