<%@page import="dbaccess.UserMapper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer home page</title>
        <link href="stylesheetnavigation.css" rel="stylesheet" type="text/css"/>
        <link href="stylesheet.css" rel="stylesheet" type="text/css"/>
        <link href="stylesheetfooter.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"><link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </head>
    <body>

        <%@include file="../navigation/menu.jsp" %>
        <%
            int id = (int) session.getAttribute("id");
            UserMapper um = new UserMapper();
            User usr = um.getUser(id);
        %>



        <div class="center-text">
            <div class="customerpageoverskrift">
                <h1>
                    Logget ind som: <%= usr.getName()%> 
                </h1>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                    <h3>
                        Min profil
                    </h3>
                    Her ser du din profil, hvor du kan opdatere eller redigere dine personlige oplysninger.
                    <br/>
                    <h3>"Skræddersyet" ordre</h3>
                    Her kan du konstruere og "skræddersy" din caport og redskabskur efter dine egne ønskede mål og se det tegnede resultat.
                    <br/>
                    <h3>Ordre Historik</h3>
                    Her kan du se en status på din ordre samt gå tilbage og se tidligere ordre.
                </div>


                <div class="col-sm-4">
                    <ul class="list-group">
                        <li class="list-group-item">
                            <a href="FrontController?command=userprofile">Min Profil</a>
                        </li>
                        <li class="list-group-item">
                            <a href="FrontController?command=customorder">Skræddersy Ordre</a>
                        </li>
                        <li class="list-group-item">
                            <a href="FrontController?command=myorders">Ordrehistorik</a>
                        </li>
                    </ul>
                </div>
                
                <div  class="customerpagepicture">
                    <img src="Image/flattag.png"/>
                </div>
            </div>
        </div>

        <%@include file="../footer/footer.jsp" %>
    </body>
</html>


