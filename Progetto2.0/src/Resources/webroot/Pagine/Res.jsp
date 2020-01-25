<html>
<%@ page language="java"%>
<%@ page session="true"%>
<%@ page import="java.util.ArrayList" %>
<jsp:useBean id='login' scope='session' class='Interface.WebInterface.Bean.LoginBean' type="Interface.WebInterface.Bean.LoginBean" />
<jsp:useBean id='res' scope='session' class='Interface.WebInterface.Bean.ResBean' type="Interface.WebInterface.Bean.ResBean" />
<head>
  <title>Risorse componente</title>

          <!-- Required meta tags -->
          <meta charset="utf-8">
          <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

          <!-- Bootstrap CSS -->
          <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">



        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="/resources/demos/style.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<%ArrayList<String> names = res.getNames();
String templist = "";
for(String s: names){
    templist = templist + "\"" + s + "\""+",";
}
templist= templist.substring(0,templist.length()-1);
%>

<script>
      $( function() {
            var availableTags = [<%=templist%>];
            $( "#inName" ).autocomplete({
              source: availableTags
            });
      } );
</script>

<script>
    $(document).ready(function(){
        $('[data-toggle="popover"]').popover();
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
             <a class="nav-link" href="../reset/">Logout</a>
        </li>
    </ul>
    <%}%>
</nav>

<br>

<% res.setId(request.getParameter("id"));%>

<%if(request.getParameter("buttonAS") != null){
    res.req(request);
}
%>

<%if(request.getParameter("buttonDR") != null){
    res.rmv(request);
}
%>

<div class="container h-100 d-flex">

    <div class="container">
    <div class="jumbotron text-center my-auto" style= "background-image: url(https://mdbootstrap.com/img/Photos/Others/gradient1.jpg)">
        <p class="bg-primary text-white">
        <%if(login.isLogged()){%>
            <h3>Modifica risorse</h3><br>
            <h4><%=res.getId()%></h4><br>
            <form method="post">
            <% int i=0; ArrayList<ArrayList<String>> ar = res.getRes(); for(ArrayList<String> ars: ar){%>
                <% for(String s: ars){ %>
                    <%=s%>
                <%}%>
                <button type="submit" name=buttonDR class="btn btn-warning" value= <%=i%> > Remove </button>
                <%i++;%>
                <br><br>
            <%}%>
            </form>
        <%}%>

        <form method="post">
        <div class="form-row align-items-center">
            <div class="col-2">
                <a href="#" data-toggle="popover" data-content="Per le risorse numeriche utilizzare il - per indicare che utilizza e il + per indicare che offre; Le risorse multiple vengon controllate solo con altre risorse multiple; Le risorse del tipo ok_componente devono essere RisorseSenzaVincolo e servono solamente per il check finale (o come commento)"> HELP </a>
            </div>

            <%ArrayList<String> types = res.getTypes();%>
            <div class="col-3">
                <label for="inlineFormInput">Tipo Vincolo</label>
                <select class="form-control mb-2" id="inType" name="inType">
                    <option selected> <%=types.get(0)%> </option>
                    <%for(int i=1;i<types.size();i++){%>
                    <option><%=types.get(i)%></option>
                    <%}%>
                </select>
            </div>

            <div class="col-2">
                <label for="inlineFormInput">Nome Vincolo</label>
                <input class="ui-autocomplete-input form-control mb-2" id="inName" name="inName" placeholder="Name">

            </div>
            <div class="col-2">
                <label for="inlineFormInput">Valore</label>
                <input type="text" class="form-control mb-2" id="inValue" name="inValue" placeholder="Value" autocomplete="off">
            </div>

            <div class="col-1">
            <button type="submit" name=buttonAS class="btn btn-primary mb-2" value=1>Add</button>
            </div>
            <div class="col-2"></div>
            </div>
        </form>
        <br>

        <h6><a href="/reload/Res.jsp?id=<%=res.getId()%>">The server recive the updated list every 30 seconds, click here to refresh this page.</a></h6>

    </div>
    </div>
</div>
<br>


<!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
       <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>


</center>
</body>
</html>