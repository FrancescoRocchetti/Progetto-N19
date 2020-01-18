<html>
<head>
<jsp:useBean id='login' scope='session' class='Interface.WebInterface.Bean.LoginBean' type="Interface.WebInterface.Bean.LoginBean" />
  <title>Progetto N19</title>
          <!-- Required meta tags -->
          <meta charset="utf-8">
          <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

          <!-- Bootstrap CSS -->
          <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>



</head>
<body>

<nav class="navbar navbar-expand-sm bg-primary navbar-dark sticky-top" role="navigation">
    <ul class="navbar-nav">
        <li class="nav-item active">
            <a class="nav-link" href="../index.jsp">Home</a>
        </li>
        <li class="nav-item">
            <a class="nav-link disabled" href="#">Build your PC</a>
        </li>
    </ul>
    <ul class="nav navbar-nav flex-row justify-content-between ml-auto">
                    <li class="nav-item order-2 order-md-1"><a href="#" class="nav-link" title="settings"><i class="fa fa-cog fa-fw fa-lg"></i></a></li>
                    <li class="dropdown order-1">
                        <button type="button" id="dropdownMenu1" data-toggle="dropdown" class="btn btn-outline-info dropdown-toggle">Login <span class="caret"></span></button>
                        <ul class="dropdown-menu dropdown-menu-right mt-2">
                           <li class="px-3 py-2">
                               <form method="post">
                                    <div class="form-group">
                                        <input id="u" name="u" placeholder="User" class="form-control form-control-sm" type="text" required="">
                                    </div>
                                    <div class="form-group">
                                        <input id="p" name="p" placeholder="Password" class="form-control form-control-sm" type="password" required="">
                                    </div>
                                    <div class="form-group">
                                        <button type="submit" name=buttonL value=1 class="btn btn-primary btn-block">Login</button>
                                    </div>
                               </form>
                                    <% login.setLogged(request.getParameter("u"),request.getParameter("p"));%>
                                    Logged: <%=login.isLogged()%><br>
                                    <a href="reset/">Logout</a>
                            </li>
                        </ul>
                    </li>
                </ul>
</nav>


<div class="container h-100 d-flex">
    <div class="col-sm-12 my-auto">

    <div class="container">
    <div class="jumbotron text-center my-auto" style= "background-image: url(https://mdbootstrap.com/img/Photos/Others/gradient1.jpg)">
          <h1>PC CONFIGURATOR</h1>
          <p>
            Progetto N19 Ingegneria del Software UniPV
          </p>

          <h3>
            <a href="page1/">Inizia a assemblare il tuo PC</a><br>
            <a href="Pagine/page2.jsp">Lista componenti</a><br>
            <%if(login.isLogged()){%><a href="Pagine/Sales.jsp">Lista vendite effettuate</a><br><%}%>
            <a href="Pagine/pageTest.html">HELP</a><br>
          </h3>

    </div>
    </div>
    </div>
</div>

</center>
</body>
</html>