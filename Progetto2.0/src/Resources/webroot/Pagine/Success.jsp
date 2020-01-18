<html>
<%@ page language="java"%>
<%@ page session="true"%>
<%@ page import="java.util.ArrayList" %>
<jsp:useBean id='shop' scope='session' class='Interface.WebInterface.Bean.Page1Bean' type="Interface.WebInterface.Bean.Page1Bean" />
<jsp:useBean id='login' scope='session' class='Interface.WebInterface.Bean.LoginBean' type="Interface.WebInterface.Bean.LoginBean" />
<head>
  <title>Acquisto Completato</title>
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
        <h2>Il tuo ordine e' stato processato correttamente.</h2>
        <%for(ArrayList<String> s: shop.getSelected()){%>
            <h6><%=s.get(1)%></h6>
        <%}%>

        <%shop.reset();%>
        <%shop.setcAttivo("");%>
        <a href="../index.jsp"><h2>Torna alla pagina iniziale</h2></a>

        <!--img src="https://i.ibb.co/C98GF4P/2-300x300.png"-->

    </div>
    </div>
</div>

</center>
</body>
</html>