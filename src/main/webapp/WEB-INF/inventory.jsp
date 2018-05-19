<%@page import="dbaccess.InventoryMapper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventory</title>
        <link href="stylesheetnavigation.css" rel="stylesheet" type="text/css"/>
        <link href="stylesheet.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="../navigation/menu.jsp" %>
    <center>
        <h1>Lagerstatus</h1>

        <% InventoryMapper im = new InventoryMapper();%>


        <%=im.completeInventory().toString().replace("[", "").replace("]", "").replace(",", "") + "<br>"%><br>


        <form action="FrontController" method="POST">
            <input type="hidden" name="command" value="updateinventorypage">
            <br><input type="number" name="inventoryid" placeholder="Vare id"/>
            <br><br><input type="submit" value="Opdater lagerbeholdning" /><br>
        </form>


        <a href="FrontController?command=addtoinventory">Tilføj til Lagerdatabase</a><br><br>

    </center>
    <a href="FrontController?command=adminpage">Tilbage</a><br><br>


</body>
</html>
