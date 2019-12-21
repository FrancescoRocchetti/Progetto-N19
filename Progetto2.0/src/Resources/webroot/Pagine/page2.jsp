<html>
<meta http-equiv="refresh" content="30">
<%@ page language="java"%>
<%@ page session="true"%>
<%@ page import="java.util.ArrayList" %>
<jsp:useBean id='inv' scope='application' class='Interface.WebInterface.Page2Bean' type="Interface.WebInterface.Page2Bean" />
<jsp:useBean id='login' scope='session' class='Interface.WebInterface.LoginBean' type="Interface.WebInterface.LoginBean" />
<head>
  <title>Progetto N19</title>
          <!-- Required meta tags -->
          <meta charset="utf-8">
          <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

          <!-- Bootstrap CSS -->
          <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>

<nav class="navbar navbar-expand-sm bg-primary navbar-dark sticky-top">
    <ul class="navbar-nav">
        <li class="nav-item active">
            <a class="nav-link" href="../index.jsp">Home</a>
        </li>
        <li class="nav-item">
            <a class="nav-link disabled" href="#">Build your PC</a>
        </li>
    </ul>
    <%if(login.isLogged()){%>
    <ul class="nav navbar-nav flex-row justify-content-between ml-auto">
        <li class="nav-link disabled" href="#" >Logged as <%=login.getUser()%>.</li>
        <li class="nav-item active">
             <a class="nav-link" href="../reset/">Log off.</a>
        </li>
    </ul>
    <%}%>
</nav>

<br>

<div class="container h-100 d-flex">

    <div class="container">
    <div class="jumbotron text-center my-auto" style= "background-image: url(https://mdbootstrap.com/img/Photos/Others/gradient1.jpg)">
        <p class="bg-primary text-white">

        <h3>ID-Name-Price-Quantity-Rating-Constrain-Type</h3><br>
        <% ArrayList<ArrayList<String>> ar = inv.getAll(); for(ArrayList<String> ars: ar){%>
            <% for(String s: ars){ %>

                <%=s%>
            <%}%>
            <%if(login.isLogged()){%>
            <div class="btn-group dropright">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Modifica
                  </button>
                  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <a class="dropdown-item" href="/change/<%=ars.get(0)%>,<%=ars.get(2)%>,<%=ars.get(3)%>,<%=ars.get(4)%>">Change Price/Quantity/Rating</a>
                    <a class="dropdown-item" href="#">See Resources</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="/remove/<%=ars.get(0)%>">Remove</a>
                  </div>
            </div>
            <%}%>
            <br><br>
        <%}%>

        <%if(login.isLogged()){%>
        <h3><a href="../Pagine/add.jsp">Add Component</a></h3>
        <h3><a href="../Pagine/type.jsp">Add Type</a></h3>
        <%}%>

        </p>
        <p id="here">
            <h6><a href="/reload/page2.jsp#here">The server recive the updated list every 30 seconds, click here to refresh this page. (this page also autorefreshes every 30 seconds)</a></h6>
        </p>
    </div>
    </div>
</div>


<!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>


</center>
</body>
</html>