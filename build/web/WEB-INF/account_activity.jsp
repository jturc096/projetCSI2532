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
        <title>Account Activity</title>
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
            <a href="#">Payments</a>
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
            <h1>Account Activity</h1>
            <form action="account_activity" name="jsp_account_id" method="get">
                <input type="hidden" value="${customerbean.beanId}" name="cID">
                # de compte : <input type="text" name="jsp_accountid_txt">
                <input type="submit" value="Voir Activity" name="jsp_activity_btm">
            </form>
        </div>
                <br>
        <div>
            <p>
            <c:choose>
                <c:when test="${accountbean.beanActivityList.size() > 0}">
                    <table>
                        <c:forEach items="${accountbean.beanActivityList}" var="releaseData">
                        <tr>
                            <td>${releaseData.toString()}</td>
                        </tr>
                        </c:forEach>
                    </table>
                </c:when>

                <c:otherwise>
                    Aucun ou mauvais compte sélectionné!
                </c:otherwise>
            </c:choose>
            </p>
        </div>

        
        <%-- As a designer, don't use any argument not included in the bean! It is the 
        job of the Controller to make sure that it include everything you need! 
        
        The controller may also include some boolean variable to control how the page 
        shall be rendered .. 
        --%>
    
    </body>
</html>

