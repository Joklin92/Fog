<%-- 
    Document   : employeepage.jsp
    Created on : Aug 24, 2017, 6:31:57 AM
    Author     : kasper
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee home page</title>
    </head>
    <body>
    <center>

        
        <h1>Hello <%= request.getParameter("email")%> </h1>
        You are now logged in as a EMPLOYEE of our wonderful site.
        
                    <form name="employeepage" action="FrontController" method="POST">
                        orderId<input type="number" name="orderID" placeholder="Order ID" required value="1"/>
                <input type="hidden" name="command" value="employeepage" />
                <input type="submit" value="Confirm Order" />
                
                
            </form>
        
        <br>

    </center>
    </body>
</html>