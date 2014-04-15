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
        <title>Account Summary</title>
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
        
        <h1>Account Summary</h1>
        <div>
                    
        </div>
        <div>
            <h3>Checking</h3>
            <p>
                <c:choose>
                    <c:when test="${customerbean.beanCheckingAccountList.size()>0}">
                        <table>
                            <c:forEach items="${customerbean.beanCheckingAccountList}" var="releaseData">
                            <tr>
                                <td>${releaseData.toString()}</td>
                            </tr>
                            </c:forEach>
                        </table>
                    </c:when>
                    
                    <c:otherwise>
                    Aucun Checking Compte!
                    </c:otherwise>
                </c:choose>
            </p>
        </div>
        <div>
            <h3>Saving</h3>
            <p>
                <c:choose>
                    <c:when test="${customerbean.beanSavingAccountList.size()>0}">
                        <table>
                            <c:forEach items="${customerbean.beanSavingAccountList}" var="releaseData">
                            <tr>
                                <td>${releaseData.toString()}</td>
                            </tr>
                            </c:forEach>
                        </table>
                    </c:when>
                    
                    <c:otherwise>
                    Aucun Saving Compte!
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

