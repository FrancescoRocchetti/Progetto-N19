<html>
<%@ page language="java"%>
<%@ page session="true"%>
<%@ page import="java.util.ArrayList" %>
<jsp:useBean id='shop' scope='session' class='Interface.WebInterface.Bean.Page1Bean' type="Interface.WebInterface.Bean.Page1Bean" />
<jsp:useBean id='login' scope='session' class='Interface.WebInterface.Bean.LoginBean' type="Interface.WebInterface.Bean.LoginBean" />
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>PC builder</title>

     <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    <style>
    .btn.disabled{
        pointers-events: all;
    }

    .tool-tip{
        display: inline-block;
    }

    .tool-tip [disabled]{
        pointers-events: none;
    }
    </style>

    <script>
    $(document).ready(function(){
        $('[data-toggle="tooltip"]').tooltip();
    });
    </script>
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

<div class="container">
<div class="jumbotron text-center" style= "background-image: url(https://mdbootstrap.com/img/Photos/Others/gradient1.jpg)">
    <p class="bg-primary text-white"><h1>Inizia a costruire il tuo PC</h1>
    <p class="bg-primary text-white"><h1>selezionando i prodotti tra le seguenti categorie</h1>
    </p>
        <form method="post">

        <% ArrayList<String> list = shop.tipiComponenti(); for(int i = 0; i<list.size();i++){ %>

            <button type="submit" name=buttonA class="btn btn-secondary" value= <%= list.get(i)%> > <%= list.get(i)%> </button>

         <%}%>
        </form>

        <% if(request.getParameter("buttonA") != null){ %> <% shop.setcAttivo(request.getParameter("buttonA")); %><% } %>

    </div>
</div>


<div class="container-fluid">
<center>
<div class="row">
  <div class="col-sm-1"> </div>
  <div class="col-sm-4 bg-primary text-break">

      <h1> <%=shop.getcAttivo()%> </h1>


      <%if(!shop.getcAttivo().equals("")){%>

        <form method="post">
          <% ArrayList<ArrayList<String>> list2 = shop.getByType(shop.getcAttivo()); for(ArrayList<String> ars: list2){ %>

              <button type="submit" name=buttonB class="btn btn-success" value= <%= ars.get(0)%> > Add </button>
              <h3><%= ars.get(1)%></h3> <br>

          <%}%>
        </form>
      <%}%>


      <% if(request.getParameter("buttonB") != null){ %> <% shop.select(request.getParameter("buttonB")); %><% } %>

  </div>
  <div class="col-sm-2"> </div>
  <div class="col-sm-4 bg-primary text-break">
    <h1>Compontenti selezionati</h1>


        <form method="post">
          <% ArrayList<ArrayList<String>> list3 = shop.getSelected(); for(ArrayList<String> ars: list3){ %>


            <button type="submit" name=buttonC class="btn btn-danger" value= <%= ars.get(0)%> > Remove </button>
              <h3> <%= ars.get(1)%></h3> <br>

          <%}%>
        </form>



  </div>
  <div class="col-sm-1"> </div>
</div>
</center>
</div>

<br>

<div class="container">
<div class="jumbotron text-center" style= "background-image: url(https://mdbootstrap.com/img/Photos/Others/gradient1.jpg)">
        <%if(!shop.isOkOperation()){shop.resetOk();%><h6>Warning</h6><%}%>
        <h2><%=shop.getPrice()%>$  <%=shop.getPower()%>W</h2><br>

        <form method="post">

            <button type="submit" name=buttonD class="btn btn-danger" value= "r" > Reset configurazione </button>
            <button type="submit" name=buttonD class="btn btn-success" value= "b" style="pointers-events: none;" <%if(!shop.check()){%>disabled<%}%>> Acquista </button>


        </form>
        <br>
        <form method="post">
                <div class="form-row align-items-center">
                    <div class="col-4"></div>
                    <div class="col-2">
                        <input type="number" min="0" class="form-control mb-2" id="inMoney" name="inMoney" placeholder="Money" autocomplete="off">
                    </div>

                    <div class="col-2">
                        <button type="submit" name=buttonAuto class="btn btn-primary mb-2" value=1>Autobuild</button>
                    </div>
                    <div class="col-4"></div>
                </div>
        </form>

        <h6>Autobuild function may not work</h6>

    </div>
</div>



</body>
</html>