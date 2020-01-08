<html>
<%@ page language="java"%>
<%@ page session="true"%>
<%@ page import="java.util.ArrayList" %>
<jsp:useBean id='login' scope='session' class='Interface.WebInterface.Bean.LoginBean' type="Interface.WebInterface.Bean.LoginBean" />
<jsp:useBean id='add' scope='application' class='Interface.WebInterface.Bean.AddBean' type="Interface.WebInterface.Bean.AddBean" />
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

<%if(request.getParameter("buttonAC") != null){
    add.req(request);
}
%>


<div class="container h-100 d-flex">

    <div class="container">
    <div class="jumbotron text-center my-auto" style= "background-image: url(https://mdbootstrap.com/img/Photos/Others/gradient1.jpg)">
        <p class="bg-primary text-white">
        <%if(login.isLogged()){%>
            <h3>Add component</h3><br>

            <form method="post">

            <div class="form-row align-items-center">
                <div class="col-3">
                </div>
                <div class="col-4">
                      <label for="inName">Name</label>
                      <input type="text" min="0" class="form-control mb-2" id="inName" name="inName" placeholder="Name">
                </div>

                <%ArrayList<String> types = add.getType();%>

                <div class="col-2">
                    <label for="inType">Type</label>
                    <select id="inType" name="inType" class="form-control mb-2">
                        <option selected> <%=types.get(0)%> </option>
                        <%for(int i=1;i<types.size();i++){%>
                        <option><%=types.get(i)%></option>
                        <%}%>
                    </select>
                </div>

                <div class="col-3">
                </div>
            </div>

            <div class="form-row align-items-center">
                <div class="col-3">
                </div>
                <div class="col-2">
                      <label for="inPrice">Price</label>
                      <input type="number" min="0" class="form-control mb-2" id="inPrice" name="inPrice" placeholder="Price">
                </div>
                <div class="col-2">
                      <label for="inQt">Quantity</label>
                      <input type="number" min="0" class="form-control mb-2" id="inQt" name="inQt" placeholder="Quantity">
                </div>
                <div class="col-2">
                      <label for="inRt">Rating</label>
                      <input type="number" min="1" max="5" class="form-control mb-2" id="inRt" name="inRt" placeholder="Rating">
                </div>
                <div class="col-3">
                </div>
            </div>

            <%ArrayList<String> vincoli = add.getVincoli();%>

            <%for(String s: vincoli){%>
            <div class="form-group">
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" id=<%=s%> name=<%=s%>>
                    <label  for=<%=s%>>
                    <%=s%>
                    </label>
                </div>
            </div>
            <%}%>

            <div class="form-row align-items-center">
                <div class="col-5">
                </div>
                <div class="col-2">
                    <button type="submit" name=buttonAC class="btn btn-primary mb-2" value=1>Add</button>
                </div>
                <div class="col-5">
                </div>
            </div>


            </form>

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