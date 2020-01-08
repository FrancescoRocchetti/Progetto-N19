<html>
<meta http-equiv="refresh" content="30">
<%@ page language="java"%>
<%@ page session="true"%>
<%@ page import="java.util.ArrayList" %>
<jsp:useBean id='login' scope='session' class='Interface.WebInterface.Bean.LoginBean' type="Interface.WebInterface.Bean.LoginBean" />
<jsp:useBean id='type' scope='session' class='Interface.WebInterface.Bean.TypeBean' type="Interface.WebInterface.Bean.TypeBean" />
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
            <a class="nav-link" href="../Pagine/page2.jsp">Back to list</a>
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

<%if(request.getParameter("buttonAT") != null){  type.addType(request.getParameter("inType")); } %>
<%if(request.getParameter("buttonDT") != null){  type.deleteType(request.getParameter("buttonDT")); } %>

<div class="container h-100 d-flex">

    <div class="container">
    <div class="jumbotron text-center my-auto" style= "background-image: url(https://mdbootstrap.com/img/Photos/Others/gradient1.jpg)">
        <%if(login.isLogged()){%>
        <p class="bg-primary text-white">
            <h3>Type manager</h3><br>
            <form method="post">
            <%for(String s: type.getAllTypes()){%>
                <%=s%>
                <button type="submit" name=buttonDT class="btn btn-warning" value= <%=s%> > Remove </button>

                <br><br>
            <%}%>
            </form>

            <br>

            <form method="post">

          <div class="form-row align-items-center">
          <div class="col-4"></div>
            <div class="col-3">
              <label class="sr-only" for="inlineFormInput">Type</label>
              <input type="text" class="form-control mb-2" id="inType" name="inType" placeholder="Type">
            </div>
            <div class="col-1">
              <button type="submit" name=buttonAT class="btn btn-primary mb-2" value=1>Save</button>
            </div>
            <div class="col-4"></div>
          </div>
        </form>

        <p id="here">
        <h6><a href="/reload/type.jsp#here">The server recive the updated list every 30 seconds, click here to refresh this page. (this page also autorefreshes every 30 seconds)</a></h6>
        </p>
        <%}%>
    </div>
    </div>
</div>
<br>


<!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>


</center>
</body>
</html>